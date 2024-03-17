package com.example.demo.Security.Exception;


import org.springframework.security.core.AuthenticationException;

public class UsernameOrPasswordException extends AuthenticationException {
    public UsernameOrPasswordException(String msg) {
        super(msg);
    }
}
