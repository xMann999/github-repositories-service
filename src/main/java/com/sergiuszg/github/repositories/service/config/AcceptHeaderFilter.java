package com.sergiuszg.github.repositories.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiuszg.github.repositories.service.exception.ErrorMessage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AcceptHeaderFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String acceptHeader = request.getHeader("Accept");
        if ("application/xml".equalsIgnoreCase(acceptHeader)) {
            sendErrorResponse(response, HttpStatus.NOT_ACCEPTABLE, "Not suppported format");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        ErrorMessage errorMessage = new ErrorMessage(status, message);
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorMessage));
    }
}