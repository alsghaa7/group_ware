package com.mino.groupware.jwt;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtInterface {
	
	public String extractUsername(String token);
	
	public Date extractExpiration(String token);
	
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver);

	public String generateToken(String username);
	
	public Boolean validateToken(String token, UserDetails userDetails);
}
