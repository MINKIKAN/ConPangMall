package com.console.mall.session;

import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        /* 중간 생략 */
//
//        http
//                .sessionManagement()
//                .sessionFixation().changeSessionId()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(false)
//        ;
//
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
//                .invalidateHttpSession(true)
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID", "remember-me");
//    }
//}