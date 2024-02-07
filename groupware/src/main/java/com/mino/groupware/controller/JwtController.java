package com.mino.groupware.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mino.groupware.jwt.JwtTokenProvider;
import com.mino.groupware.service.UserService;

@Controller
public class JwtController {

	private static final Logger logger = LoggerFactory.getLogger(JwtController.class);

	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	@Autowired
	private final UserService userService;

	public JwtController(JwtTokenProvider jwtTokenProvider, UserService userService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String jwtTemp(@RequestBody Map<String, String> user) throws Exception {

		logger.info("@@ JwtController @@ : {}", user);
		
		Map<String, String> updateInfo = new HashMap<String, String>();
		String loginChk = userService.loginChk(user);

		logger.info("## loginChk ## : {}", loginChk);
		if (StringUtils.hasText(loginChk)) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.get("user_id"),user.get("user_pswd"));

			String token = jwtTokenProvider.generateToken(authentication);
			logger.info("## token ## : {} ", token);
			updateInfo.put("token", token);
			updateInfo.put("user_id", user.get("user_id"));
			userService.tokenUpdate(updateInfo);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			return token;
		} else {
			return null;
		}

	}
	
}
