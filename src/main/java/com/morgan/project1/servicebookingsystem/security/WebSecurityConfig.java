package com.morgan.project1.servicebookingsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private JwtRequestFilter requestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        String[] permitAllEndPoints = {"/v1/users/signup", "/v1/companys/signup","/v1/authentication"} ;

        return http.csrf().disable()
                .authorizeHttpRequests(req -> req.requestMatchers(permitAllEndPoints).permitAll()
                                        .requestMatchers("/v1/users/**").hasRole("USER")
                .requestMatchers("/v1/admins/**").hasRole("ADMIN")
                .requestMatchers("/v1/company/**").hasRole("COMPANY")
                        .anyRequest().authenticated())
//                .requestMatchers("/v1/user/**").hasRole("USER")
//                .requestMatchers("/v1/admin/**").hasRole("ADMIN")
//                .requestMatchers("/v1/company/**").hasRole("COMPANY")
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
    }

}
