package com.mino.groupware.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.mino.groupware.controller.LoginController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private final String secret = "minoAFWERsbufeawFEASWfbueswfdsakj125613lFEASW";
	private final long validTime = 1000 * 60 * 60;
	
	@Autowired
	private final UserDetailsService userDetailsService;
	
	public JwtTokenProvider(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	public String generateToken(Authentication authentication) {
		logger.info("@@ Provider @@ : {}", authentication);
        return createToken(authentication, validTime, secret);
	}
	private String createToken(Authentication authentication, long validTime, String key2) {
		Key secret = Keys.hmacShaKeyFor(key2.getBytes(StandardCharsets.UTF_8));
		
		String authorities = authentication.getAuthorities().stream()
				.map(auth -> auth.getAuthority())
				.collect(Collectors.joining(","));
		long now = new Date().getTime();
		Date valid = new Date(now + validTime);
		
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim("user_admin", authorities)
				.signWith(secret, SignatureAlgorithm.HS256)
				.setExpiration(valid)
				.compact();
	}
	
	public String getUserFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		String username = getUserFromToken(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		Date expirationDate = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
		return expirationDate.before(new Date());
	}
	
	public String temptemp() {
		return "temptemp";
	}
}