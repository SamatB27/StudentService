# Student Service

Этот проект — backend часть веб-приложения по добавлению, обновлению, удалению и получению информации о студентах.
Он построен с использованием Spring Boot и PostgreSQL в качестве базы данных.

## 1. Требования

- Java 8 или выше (Проект написан на Java 17)
- Maven 3.6+
- PostgreSQL 12+

## 2. Настройка базы данных

1. Установите PostgreSQL (если еще не установлен).
2. Создайте базу данных.
3. Убедитесь, что база данных доступна на порту 5432 (или настройте это в application.properties).
4. В файле application.properties укажите параметры подключения:
    - spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
    - spring.datasource.username=your_db_user
    - spring.datasource.password=your_db_password
5. Замените your_db_user и your_db_password на актуальные данные для вашего подключения к базе данных.

## 3. Установка зависимостей

в pom.xml имеются необходимые зависимости, нужно их установить - mvn clean install

## 4. Настройка OAuth 2.0

1. ## Для GitHub
    - Создайте проект на GitHub Developer Settings:
        - в поле "Application name" прописать название проекта
        - В поле "Homepage URL" можете http://localhost:8080 указать
        - В поле "Authorization callback URL" указать http://localhost:8080/api/students/oauth2/callback
    - Скопировать Client ID и Client secrets и в application.properties:
        - в поле client-id вставить Client ID.
        - в поле client-secret вставить Client secrets.
        - значения остальных полей оставить.

2. ## Для Google
    - Создайте проект на Google Cloud (console.cloud.google.com):
        - При создании Credentials выбрать "OAuth client ID" и application type указать web application
        - в поле "Name" прописать название проекта
        - В поле "Authorized redirect URIs" указать http://localhost:8080/api/students/oauth2/callback
    - Скопировать Client ID и Client secrets и в application.properties:
        - в поле client-id вставить Client ID.
        - в поле client-secret вставить Client secrets.
        - значения остальных полей оставить.

## 5. Запуск приложения

   Приложение запускается на порту 8080, если у вас свой порт, тогда необходимо поменять указанные порты 
на GitHub Developer Settings, Google Cloud (console.cloud.google.com) и в application.properties.

   После запуска приложение в браузере перейти по ссылке http://localhost:8080 и авторизоваться одним
из способов OAuth2 (GitHub или Google), вам вернется в формате JSON код авторизации, его нужно скопировать
и вставить в поля code из пунктов 6.1 и 6.2.

## 6. Примеры запросов в формате CURL для ОС Windows

1. Получить токен Google:
   curl -X POST "https://oauth2.googleapis.com/token" ^
   -d "code=YOUR_CODE" ^
   -d "client_id=YOUR_CLIENT_ID" ^
   -d "client_secret=YOUR_CLIENT_SECRET" ^
   -d "redirect_uri=http://localhost:8080/api/students/oauth2/callback" ^
   -d "grant_type=authorization_code"

2. Получить токен GitHub:
   curl -X POST "https://github.com/login/oauth/access_token" ^
   -d "code=YOUR_CODE" ^
   -d "client_id=YOUR_CLIENT_ID" ^
   -d "client_secret=YOUR_CLIENT_SECRET" ^
   -d "redirect_uri=http://localhost:8080/api/students/oauth2/callback" ^
   -H "Accept: application/json"

3. Создать студента:
   curl -X PUT "http://localhost:8080/api/students" ^
   -H "Authorization: Bearer YOUR_GOOGLE_OR_GitHub_Token" ^
   -H "Content-Type: application/json" ^
   -d "{\"lastName\": \"Ivanov\", \"firstName\": \"Maksim\", \"middleName\": \"Alekseevich\", \"studentGroup\":
   \"first\", \"averageGrade\": 4.5}"

4. Получить студента или студентов:
   /api/students/all - uri для получения всех студентов
   /api/students/1 - uri для получения студента с ID 1

   curl -X GET "http://localhost:8080/api/students/all" -H "Authorization: Bearer YOUR_GOOGLE_OR_GitHub_Token"

5. Обновить студента с ID 1:
   curl -X POST "http://localhost:8080/api/students/1" ^
   -H "Authorization: Bearer YOUR_GOOGLE_OR_GitHub_Token" ^
   -H "Content-Type: application/json" ^
   -d "{\"lastName\": \"Price\", \"firstName\": \"Jonathan\", \"middleName\": \"John\", \"studentGroup\": \"second\",
   \"averageGrade\": 5.0}"

6. Удалить студента с ID 1:
   curl -X DELETE "http://localhost:8080/api/students/1" ^
   -H "Authorization: Bearer YOUR_GOOGLE_OR_GitHub_Token"