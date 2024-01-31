package com.mino.groupware.jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mino.groupware.controller.LoginController;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
    private UserDetailsServiceImpl userDetailsServiceImpl;  // CustomUserDetailsService는 실제 프로젝트에 맞게 수정

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	logger.info("@@Authenticate@@ : {}", authentication);
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        // 여기에서 사용자 정의 인증 로직을 구현 (예: 토큰 검증)

        // 인증이 성공하면 UsernamePasswordAuthenticationToken을 반환
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
