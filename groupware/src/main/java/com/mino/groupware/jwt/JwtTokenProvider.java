package com.mino.groupware.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.mino.groupware.controller.LoginController;
import com.mino.groupware.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	private final String secret = "minoAFWERsbufeawFEASWfbueswfdsakj125613lFEASW";
	private final long validTime = 1000 * 60 * 60;
	
	@Autowired
	private final UserService userService;
	
	public JwtTokenProvider(UserService userService) {
		this.userService = userService;
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
		Claims claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		String username = getUserFromToken(token);
		Map<String, String> userDetails = userService.findByUsername(username);
		return username.equals(userDetails.get("user_id")) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		Date expirationDate = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getExpiration();
		Map<String, String> map = new HashMap<String, String>();
		logger.info(" expirate @@ {} @@ :", expirationDate.before(new Date()));
		if(!expirationDate.before(new Date())) {
			return true;
		} else {
			map.put("token", token);
			userService.tokenDelete(map); 
			return false;
		}
	}
	
	public String extractToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		logger.info("@@ provider @@ {}:", bearerToken);
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			
			return bearerToken.substring(7);
		} else {
		return null;}
	}

}
