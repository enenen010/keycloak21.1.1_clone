package com.example.demo.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
			)
			.logout()
				.logoutSuccessHandler(logoutSuccessHandler)
				.logoutSuccessUrl("/")
			.and()
			.oauth2Login()
			.defaultSuccessUrl("/")
			;
		return http.build();
	}

}
