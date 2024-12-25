package com.trendy.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers("/login", "/error", "/oauth2/**", "/public/**").permitAll() // 인증 없이 접근 허용
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login") // 사용자 정의 로그인 페이지
                .permitAll()
            .and()
            .logout()
                .logoutUrl("/logout")
                .permitAll()
            .and()
            .oauth2Login() // OAuth2 로그인 사용
                .loginPage("/login"); // 카카오 인증을 위한 로그인 페이지
        return http.build();
    }
}