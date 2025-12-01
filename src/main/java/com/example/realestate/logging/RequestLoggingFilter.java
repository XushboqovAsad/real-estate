package com.example.realestate.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        long start = System.currentTimeMillis();

        String method = req.getMethod();
        String uri = req.getRequestURI();
        String userAgent = req.getHeader("User-Agent");
        String ip = request.getRemoteAddr();

        log.info("➡️ {} {} | IP: {} | User-Agent: {}", method, uri, ip, userAgent);

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - start;

        log.info("⬅️ {} {} completed in {} ms", method, uri, duration);
    }
}

