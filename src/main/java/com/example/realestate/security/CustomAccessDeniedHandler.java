package com.example.realestate.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException{
        response.setStatus(403);
        response.setContentType("application/json");

        Map<String, Object> error = Map.of(
                "status", 403,
                "error", "Forbidden",
                "message", ex.getMessage()
        );

        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
