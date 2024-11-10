package com.beganov.studentservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class GithubTokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            // Создаем аутентификацию с токеном
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken("githubUser", token, Collections.emptyList());

            // Устанавливаем аутентификацию в контексте безопасности
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Передаем управление следующему фильтру в цепочке
        filterChain.doFilter(request, response);
    }
}
