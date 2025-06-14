package com.example.websiteminuman.security;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BoundaryInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        if (uri.startsWith("/admin")) {
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("/loginAdmin"); 
                return false;
            }
        }

        if (uri.startsWith("/customer")) {
            if (session == null || session.getAttribute("customerEmail") == null) {
                response.sendRedirect("/loginCust"); 
                return false;
            }
        }

        return true; // Lanjut ke controller
    }
}

