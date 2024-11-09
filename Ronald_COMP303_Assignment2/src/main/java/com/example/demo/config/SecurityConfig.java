package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	StudentService studentsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/login", "/register").permitAll()
            .anyRequest().authenticated()
         )
        .formLogin(form -> form
            .loginPage("/")  
            .loginProcessingUrl("/login")  
            .defaultSuccessUrl("/programs", true)  
            .permitAll()  
        )
        .logout(logout -> logout
            .logoutUrl("/logout") 
            .logoutSuccessUrl("/")  
            .permitAll()
        );
    
    return http.build();
    
	}


    @Bean
    UserDetailsService userDetailsService() {
	    return username -> {
	        Student student = studentsService.getStudentByUsername(username);
	        if (student != null) {
	            // Convert Student to UserDetails
	            return User.builder()
	                .username(student.getUsername())
	                .password("{noop}" + student.getPassword())  	
	                .roles("USER")
	                .build();
	        } else {
                throw new UsernameNotFoundException("User not found: " + username);
            }
	    };
	}
}