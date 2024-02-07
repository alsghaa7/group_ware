package com.mino.groupware.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mino.groupware.jwt.JwtTokenProvider;
import com.mino.groupware.service.UserService;

import com.mino.groupware.vo.UserInfo;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final UserService userService;
	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public LoginController(UserService userService, JwtTokenProvider jwtTokenProvider) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@RequestMapping(value = "/returnMain.do")
	public String returnMain(Model model) {
		logger.info("main.jsp return");
		return "main";
	}
	
	@RequestMapping(value="/returnSignUp.do")
	public String returnSignUp(Model model) {
		logger.info("signUp.jsp return");
		return "signUp";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginProc(Model model, @RequestBody Map<String, String> userInfo) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> updateInfo = new HashMap<String, String>();
		
		result.put("result", "fail");
		
		logger.info("LoginController loginProc");
		logger.info("userInfo: {}", userInfo);

		String loginChk = userService.loginChk(userInfo);
		logger.info("result: {}", loginChk);

		if (StringUtils.hasText(loginChk)) {
			result.put("result", loginChk);
		}
		if(StringUtils.hasText(loginChk)) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo.get("user_id"),userInfo.get("user_pswd"));

			String token = jwtTokenProvider.generateToken(authentication);
			logger.info("## token ## : {} ", token);
			updateInfo.put("token", token);
			updateInfo.put("user_id", userInfo.get("user_id"));
			
			 userService.tokenUpdate(updateInfo);
			 
			boolean a = jwtTokenProvider.validateToken(token);
			logger.info(" valid @@@ {} @@@", a);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			result.put("token", token);
			return result;
		} else {
		return null;}
	}
	
	@RequestMapping(value="/sign.do", method = RequestMethod.POST)
	@ResponseBody
	public int signUpproc(@RequestBody UserInfo userSave) {

		int userKey = userService.save(userSave);
		logger.info("[ signUp temp ] ={}", userKey);
		return userKey;
	}
	
	@RequestMapping(value="/logout.do", method = RequestMethod.POST)
	public void logoutProc(HttpServletRequest request) {
		String token = jwtTokenProvider.extractToken(request);
		logger.info(" logout @@ {} @@ :", token);
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		userService.tokenDelete(map);
	}
}
