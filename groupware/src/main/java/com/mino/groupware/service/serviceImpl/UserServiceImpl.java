package com.mino.groupware.service.serviceImpl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mino.groupware.controller.LoginController;
import com.mino.groupware.mapper.UserMapper;
import com.mino.groupware.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		// TODO Auto-generated constructor stub
		this.userMapper = userMapper;
	}
	
	@Override
	public String userCheck(Map<String, String> userInfo) {
		logger.info("[ UserService userCheck ]");
		
//		return userMapper.getUserList();
		return "success";
	}
}
