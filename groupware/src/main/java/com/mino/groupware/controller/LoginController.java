package com.mino.groupware.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mino.groupware.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final UserService userServie;
	
	@Autowired
	public LoginController(UserService userService) {
		// TODO Auto-generated constructor stub
		this.userServie = userService;
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public void loginProc(@RequestParam Map<String, String> userInfo) {
		logger.info("LoginController loginProc");
		logger.info("userInfo: {}", userInfo);
		
		String userCheck = userServie.userCheck(userInfo);
		logger.info("result: {}", userCheck);
	}
}
