package com.mino.groupware.util;

import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mino.groupware.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
	
	private final UserService userService;
	private final String secretKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorizationHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(!authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authorizationHeader.split(" ")[1];
		
		if(JwtTokenUtil.isExpired(token, secretKey)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String user_id = JwtTokenUtil.getUserId(token, secretKey);
		
		
		/* 서비스에 아이디로 유저정보 받아오는 인스턴스 작성 필요 */
		  User loginUser = userService.getLoginUserByLoginId(user_id);
		  
		  UsernamePasswordAuthenticationToken authenticationToken = new
		  UsernamePasswordAuthenticationToken( loginUser.getLoginId(), null,
		  List.of(new SimpleGrantedAuthority(loginUser.getRole().name())));
		  authenticationToken.setDetails(new
		  WebAuthenticationDetailsSource().buildDetails(request));
		 
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
		
	}
}