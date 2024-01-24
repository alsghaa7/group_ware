package com.mino.groupware.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mino.groupware.controller.LoginController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JwtTokenProvider {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private int ACCESS_TOKEN_EXPIRE_TIME = 1800000;
    private static final String KEY_ROLE = "USER";

   private String key = "kimminho123412341234123412341234";
    
    
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    
   
    public String createToken(Authentication authentication) {
        return createNewToken(authentication, ACCESS_TOKEN_EXPIRE_TIME, key);
    }
   
    private String createNewToken(Authentication authentication, long expireTime, String key2) {
    	expireTime = 1800000;
    	key2 = "kimminho123412341234123412341234";
       Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
       logger.info("provider: {}", secretKey);
        String authorities = authentication.getAuthorities().stream()
                 .map(auth -> auth.getAuthority())
                 .collect(Collectors.joining(","));
        logger.info("provider: {}", authorities);
         long now = new Date().getTime();
         Date validity = new Date(now + expireTime);

         return Jwts.builder()
        		 .setSubject(authentication.getName())
        		 .claim(KEY_ROLE, authorities)
        		 .signWith(secretKey, SignatureAlgorithm.HS256)
        		 .setExpiration(validity)
        		 .compact();
    }
    
    public String generateToken(String id) {
    	Claims claim = Jwts.claims();
    	claim.put("username", id);
    	
    	return Jwts.builder().setClaims(claim)
    			.setExpiration(new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRE_TIME))
    			.signWith(SignatureAlgorithm.HS256, key).compact();
    }
    
   public Authentication getAuthentication(String token) {
       Key secretKey = Keys.hmacShaKeyFor(key.getBytes());
       Claims claims = Jwts.parserBuilder()
                               .setSigningKey(secretKey)
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            User principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, token, authorities);
        
    }
  
   public boolean validateAccessToken(String accessToken) {
      
      Key secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
      System.out.println("accessToken::::"+accessToken);
        try {
           Jwts.parserBuilder()
                   .setSigningKey(secretKey)  // your secret key
                   .build()
                   .parseClaimsJws(accessToken);
           
           //to-be jwt exception처리 추가
           
           return true;
        } catch (SecurityException | MalformedJwtException e) {
           log.error(e.getMessage());
            //throw new ConflictException("Invalid JWT token: {}", ErrorCode.CONFLICT_MEMBER_EXCEPTION);
        } catch (ExpiredJwtException e) {
            //throw new TokenException("토큰이 만료되었습니다.", ErrorCode.TOKEN_EXPIRED_EXCEPTION);
           log.error(e.getMessage());
        } catch (IllegalArgumentException e) {
            //throw new ConflictException(String.format(e.getMessage(), ErrorCode.CONFLICT_MEMBER_EXCEPTION));
           log.error(e.getMessage());
        }
        return false;
    }
 
   public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(BEARER.length());
        }
        return null;
    }
}