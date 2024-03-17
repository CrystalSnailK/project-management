package com.example.demo.ConfigurationCLass;

import com.example.demo.Security.AuthenticationManager.CustomAuthenticationManager;
import com.example.demo.Security.Filter.JWTAuthenticationFilter;
import com.example.demo.Security.Filter.CustomFilter;
import com.example.demo.Security.Handler.CustomLogoutHandle;
import com.example.demo.Security.Handler.CustomLogoutSuccessHandle;
import com.example.demo.Security.Handler.FailureHandle;
import com.example.demo.Security.Handler.SuccessfulHandle;
import com.example.demo.Security.TokenAuthenticationMapRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public CustomAuthenticationManager customAuthenticationManager() throws Exception{
        return new CustomAuthenticationManager();
    }
    @Bean // 注册成功和失败处理器
    public SuccessfulHandle securityHeaders() {
        return new SuccessfulHandle();
    }
    @Bean
    public FailureHandle failureHandle() {
        return new FailureHandle();
    }

    // Token和Authentication的映射关系
    @Bean
    public TokenAuthenticationMapRepository tokenAuthenticationMapRepository() {
        return new TokenAuthenticationMapRepository();
    }

    @Bean
    public CustomFilter customFilter() throws Exception{
        CustomFilter customFilter = new CustomFilter();
        customFilter.setAuthenticationManager(customAuthenticationManager());
        customFilter.setAuthenticationSuccessHandler(securityHeaders());
        customFilter.setAuthenticationFailureHandler(failureHandle());
        return customFilter;
    }
    // JWT 认证过滤器
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        return new JWTAuthenticationFilter();
    }
    // Logout 过滤器
    @Bean
    public LogoutFilter logoutFilter() throws Exception{
        return new LogoutFilter(customLogoutSuccessHandle(), customLogoutHandle());
    }
    @Bean
    public CustomLogoutHandle customLogoutHandle() throws Exception{
        return new CustomLogoutHandle();
    }
    @Bean
    public CustomLogoutSuccessHandle customLogoutSuccessHandle() throws Exception{
        return new CustomLogoutSuccessHandle();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // 允许所有域名访问
        configuration.addAllowedHeader("*"); // 允许所有请求头
        configuration.addAllowedMethod("*"); // 允许所有请求方法
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 允许所有域名访问
        config.addAllowedHeader("*"); // 允许所有请求头
        config.addAllowedMethod("*"); // 允许所有请求方法
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    // security配置
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/manager/**").permitAll()// .hasAuthority("manager")
                .antMatchers("/student/**").permitAll()// .hasAuthority("student")
                .antMatchers("/teacher/**").hasAuthority("teacher")
                .antMatchers("/static/**").permitAll()
                .antMatchers("/templates/**").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(customFilter(), UsernamePasswordAuthenticationFilter.class) // 用来授权
                .addFilterAt(logoutFilter(), LogoutFilter.class)
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
