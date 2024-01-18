package com.mino.groupware.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.mino.groupware.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
	
	private final UserService userService;
	private static String secretKey = "aaaa";
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		return httpSecurity
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
		/*		.addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePassworkAuthenticationFilter.class)*/
				.authorizeRequests()
				.antMatchers("").authenticated()
				.antMatchers("").hasAuthority(UserRole.ADMIN.name())
				.and().build();
	}
}