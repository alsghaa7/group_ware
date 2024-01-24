package com.mino.groupware.jwt;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
    // 토큰 검증이 필요 없는 url
    private static final String[] WHITELIST = {
    		"/", "/returnSignUp.do"
    };
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    @Autowired
    private  JwtTokenProvider jwtTokenProvider;
    
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    
   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        String path = request.getRequestURI();
        if (Arrays.stream(WHITELIST).anyMatch(pattern -> antPathMatcher.match(pattern, path))) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = resolveToken(request);
            // Access Token 유효성 검사
           // to-be jwt exception 추가 개발 필요 (현재는 500에러 내려옴)
            if(jwtTokenProvider.validateAccessToken(accessToken)) {
               
               Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
               SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
   }
   
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(BEARER.length());
        } else {

            return null;
        }
    }
}