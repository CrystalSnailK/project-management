package com.example.demo.Security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.HashMap;
import java.util.Map;

public class TokenAuthenticationMapRepository {
    Map<String, UsernamePasswordAuthenticationToken> map = new HashMap<>();

    public void save(String username, UsernamePasswordAuthenticationToken token) {
        map.put(username, token);
    }
    public UsernamePasswordAuthenticationToken get(String username) {
        return map.get(username);
    }
    public void remove(String token) {
        map.remove(token);
    }
    public Map<String, UsernamePasswordAuthenticationToken> getAll() {
        return map;
    }
}
