package com.example.demo.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserService;

import jakarta.persistence.Entity;

/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@Entity
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserService userService;
	
	public SecurityConfig(UserService userService) {
		this.userService = userService;
	}
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
	        .authorizeHttpRequests(authz -> authz
	        	.requestMatchers(HttpMethod.POST, "/user").permitAll()
	            .anyRequest().authenticated()
	         )
	        .httpBasic(Customizer.withDefaults())
	        .csrf(AbstractHttpConfigurer::disable);
    
		return http.build();
	}


    @Bean
    UserDetailsService userDetailsService() {
	    return username -> {
	        Optional<com.example.demo.domain.User> currentUser = this.userService.getUserByUsername(username);
	        if (currentUser.isPresent()) {
	            return User.builder()
	                .username(currentUser.get().getUsername())
	                .password(currentUser.get().getPassword())
	                .roles(currentUser.get().getRole().toString())
	                .build();
	        } else {
                throw new UsernameNotFoundException("User not found: " + username);
            }
	    };
	}
    


}