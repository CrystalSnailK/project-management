package com.example.demo.Security.Handler;

import com.example.demo.Security.TokenAuthenticationMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomLogoutHandle implements LogoutHandler {
    @Autowired
    TokenAuthenticationMapRepository tokenAuthenticationMapRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println(tokenAuthenticationMapRepository.getAll());
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        tokenAuthenticationMapRepository.remove(token);
        System.out.println(tokenAuthenticationMapRepository.getAll());
    }
}
