package com.mino.groupware.util;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
	
	public static String createToken(String user_id, String key, long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("user_id", user_id);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}
	
	public static String getUserId(String token, String secretKey) {
		return extractClaims(token, secretKey).get("user_id").toString();
	}
	
	public static boolean isExpired(String token, String secretKey) {
		Date expiredDate = extractClaims(token, secretKey).getExpiration();
		
		return expiredDate.before(new Date());
	}
	
	private static Claims extractClaims(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}