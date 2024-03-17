package com.example.demo.Security.Handler;

import com.alibaba.fastjson.JSON;
import com.example.demo.Entities.LogoutSuccessEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLogoutSuccessHandle implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(new LogoutSuccessEntity("退出成功")));
    }
}
