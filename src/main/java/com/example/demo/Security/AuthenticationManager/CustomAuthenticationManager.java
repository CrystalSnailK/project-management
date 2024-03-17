package com.example.demo.Security.AuthenticationManager;

import com.example.demo.Security.Exception.UsernameOrPasswordException;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Service.Cus_UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;


// 自定义一个用户名密码的AuthenticationManager
//@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    Cus_UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication){
        int ID = (int) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserEntity user = userDetailsService.loadUserByUsername(ID);

        if (ID == user.getID() && password.equals(user.getPassword())) {

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(user.getID(), user, authorities);
        }else {
            throw new UsernameOrPasswordException("登录异常");
        }
    }
}
