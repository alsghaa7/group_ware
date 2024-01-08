package com.mino.groupware.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mino.groupware.service.UserService;
import com.mino.groupware.vo.UserInfo;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	public LoginController(UserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
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
	public Map<String, String> loginProc(Model model, @RequestBody Map<String, String> userInfo) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		result.put("result", "fail");
		
		logger.info("LoginController loginProc");
		logger.info("userInfo: {}", userInfo);
		
//		String userCheck = userService.userCheck(userInfo);
//		logger.info("result: {}", userCheck);
		
		String loginChk = userService.loginChk(userInfo);
		logger.info("result: {}", loginChk);
		
		if (StringUtils.hasText(loginChk)) {
			result.put("result", loginChk);
		}
		
		return result;
	}
	
	@RequestMapping(value="/signup.do", method = RequestMethod.POST)
	@ResponseBody
	public int signUpProc(@RequestBody UserInfo userSave) {
		
		int userKey = userService.save(userSave);
		logger.info("[ signUp temp ] ={}", userKey);
		return userKey;
	}

}
