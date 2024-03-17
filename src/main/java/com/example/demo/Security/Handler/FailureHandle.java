package com.example.demo.Security.Handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entities.LoginFailureResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FailureHandle implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
        System.out.println("login failure");
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(new LoginFailureResponse("登录失败")));
        response.flushBuffer();
    }
}
