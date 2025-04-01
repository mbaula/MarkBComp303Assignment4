//package com.spring.boot.micro.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // Optional: disables CSRF for form simplicity
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/signup", "/login", "/css/**").permitAll() 
//                .anyRequest().authenticated() // everything else requires login
//            )
//            .formLogin(form -> form
//                .loginPage("/login") // your custom login form
//                .permitAll()
//            )
//            .logout(logout -> logout.permitAll());
//
//        return http.build();
//    }
//}
