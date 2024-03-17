package com.example.demo.Security.Handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entities.LoginSuccessfulResponse;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Security.TokenAuthenticationMapRepository;
import com.example.demo.Security.Unitls.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SuccessfulHandle implements AuthenticationSuccessHandler {
    @Autowired
    TokenAuthenticationMapRepository tokenAuthenticationMapRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // generate token and store it into tokenAuthenticationMapRepository
        String token = "";
        try {
            token = JwtUtils.generateToken();
        } catch (UnsupportedEncodingException e) {
            System.out.println("a error happen in SuccessfulHandle#onAuthenticationSuccess about generate token, error:" + e.getMessage());
        }
        tokenAuthenticationMapRepository.save(token, (UsernamePasswordAuthenticationToken) authentication);
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        UserEntity user = (UserEntity) authentication.getCredentials();
        try{
            response.getWriter().print(JSON.toJSONString(new LoginSuccessfulResponse("登录成功", "Bearer "+token, user.getRole(), user.getID())));
        }catch (IOException e){
            System.out.println("a error happen in SuccessfulHandle#onAuthenticationSuccess about print response, error:" + e.getMessage());
        }
        System.out.println(tokenAuthenticationMapRepository);
    }

}
