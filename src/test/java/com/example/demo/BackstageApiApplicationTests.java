package com.example.demo;

import com.example.demo.Security.Unitls.JwtUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

class BackstageApiApplicationTests {

	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
		String token = JwtUtils.generateToken();
		System.out.println(token);
		System.out.println(JwtUtils.verifyToken(token));
	}

}
