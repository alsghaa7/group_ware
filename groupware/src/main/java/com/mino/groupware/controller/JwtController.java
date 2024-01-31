package com.mino.groupware.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mino.groupware.jwt.JwtTokenProvider;

@Controller
public class JwtController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtController(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String jwtTemp(@RequestBody Map<String, String> user) throws Exception {
    	
    	String test = jwtTokenProvider.temptemp();
    	
    	logger.info(" && temptemp && : {}", test);
    	logger.info("@@ JwtController @@ : {}", user);
    	
    	Authentication authentication = new UsernamePasswordAuthenticationToken(user.get("userName"), user.get("password"));
    	logger.info("$$ temp $$ : {}", authentication);
    	String token = jwtTokenProvider.generateToken(authentication);
    	logger.info("## token ## : {} ", token);
    	
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	try {
    	
    		token = jwtTokenProvider.generateToken(authentication);
    	} catch (Exception e) {
    		throw new Exception("JWT_CONTROLLER_EXCEPTION");
    	}
    	return token;
    }
}
