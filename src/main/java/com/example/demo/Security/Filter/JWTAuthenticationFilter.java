package com.example.demo.Security.Filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entities.LoginFailureResponse;
import com.example.demo.Security.TokenAuthenticationMapRepository;
import com.example.demo.Security.Unitls.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    TokenAuthenticationMapRepository tokenAuthenticationMapRepository;
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");
        if(authenticationHeader != null && authenticationHeader.startsWith("Bearer ") && checkJWTToken(authenticationHeader.substring(7))){
            String jwt = authenticationHeader.substring(7);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = tokenAuthenticationMapRepository.get(jwt);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            try {
                filterChain.doFilter(request, response);
            }catch (IOException | ServletException e){
                System.out.println("a error happen in JWTAuthenticationFilter#doFilterInternal, error:" + e.getMessage());
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }

    public boolean checkJWTToken(String jwt){
        return JwtUtils.verifyToken(jwt);
    }
    public void fail(HttpServletRequest request, HttpServletResponse response) {
        response.addHeader("Content-Type", "application/json");
        response.setCharacterEncoding("UTF-8");
        try{
            response.getWriter().print(JSON.toJSONString(new LoginFailureResponse("登录失败")));
        }catch (IOException e){
            System.out.println("a error happen in JWTAuthenticationFilter#fail, error:" + e.getMessage());
        }
    }
}
