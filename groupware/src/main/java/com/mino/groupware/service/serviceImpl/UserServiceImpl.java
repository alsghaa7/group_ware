package com.mino.groupware.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mino.groupware.controller.LoginController;
import com.mino.groupware.mapper.UserMapper;
import com.mino.groupware.service.UserService;
import com.mino.groupware.vo.User;
import com.mino.groupware.vo.UserInfo;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private final UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {

		this.userMapper = userMapper;
	}
	
	@Override
	public String userCheck(Map<String, String> userInfo) {
		logger.info("[ UserService userCheck ]");
		
		List<Map<String, String>> userList = userMapper.getUserList();
		logger.info("[ UserService ]: {}", userList);
	
//		return userMapper.getUserList();
		return "success";
	}
	
	public String loginChk(Map<String, String> userInfo) {
		
		String temp = userMapper.loginChk(userInfo);
		logger.info("[ userService loginChk ] : {}", temp);
		
		return userMapper.loginChk(userInfo);
	}
	
	public int signUp(UserInfo userSignUp) {
		
		logger.info("[ userService signUp ]: {}", userSignUp);
		return userMapper.signUp(userSignUp);
		
	}
	
	public int save(UserInfo userSave) {
		
		return userMapper.save(userSave);
	}

	@Override
	public void tokenUpdate(Map<String, String> updateInfo) {
		
		logger.info("@@ Service tokenUpdate@@ : {}", updateInfo);
		userMapper.tokenUpdate(updateInfo);
		
	}

	@Override
	public Map<String, String> findByUsername(String user_name) {

		return userMapper.findByUsername(user_name);
	}

	@Override
	public void tokenDelete(Map<String, String> deleteInfo) {
		
		logger.info("@@ Service tokenDelete @@ : {}", deleteInfo);
		userMapper.tokenDelete(deleteInfo);
		
	}
}
