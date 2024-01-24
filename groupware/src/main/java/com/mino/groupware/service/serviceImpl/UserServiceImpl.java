package com.mino.groupware.service.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mino.groupware.controller.LoginController;
import com.mino.groupware.jwt.JwtTokenProvider;
import com.mino.groupware.jwt.NewToken;
import com.mino.groupware.mapper.UserMapper;
import com.mino.groupware.service.UserService;
import com.mino.groupware.vo.LoginReq;
import com.mino.groupware.vo.UserInfo;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final UserMapper userMapper;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private AuthenticationManager authenticationManager; // authenticate 메서드 : username, password 기반 인증 수행
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		// TODO Auto-generated constructor stub
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
	
//	@Override
//	public String loginChk(LoginReq loginreq) {
//		logger.info("222222222222", loginreq);
//		Map<> userDetails = userMapper.getLoginUserByLoginId(loginreq.getUser_id());
//		
//		logger.info("44555555555555", userDetails.getUser_pswd());
//		logger.info("NewToken Service: {}", passwordEncoder().matches(loginreq.getUser_pswd(), userDetails.getUser_pswd()));
//		if(passwordEncoder().matches(loginreq.getUser_pswd(), userDetails.getUser_pswd())){
//			logger.info("NewToken Service: {}", loginreq.getUser_pswd());
//			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUser_id(), userDetails.getUser_pswd());
//			String token = jwtTokenProvider.createToken(authentication);
//			
//			NewToken newToken = new NewToken(authentication.getName(), token);
//			userMapper.updateToken(token, userDetails.getUser_id());
//			
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.add("Authorization", "Bearer " + newToken.getToken());
//			
//			logger.info("{}", newToken.getToken());
//			
//			return newToken.getToken();
//		} else {
//			return null;
//		}
//	}
	
	public String loginTest(LoginReq req) {
		
		logger.info("test : {}", req.getUser_id());
		Map<String, String> userDetails = userMapper.getLoginUserByLoginId(req.getUser_id());
		
		if(passwordEncoder().matches(req.getUser_pswd(), userDetails.get("user_pswd"))){
			logger.info("NewToken Service: {}", userDetails);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.get("user_id"), userDetails.get("user_pswd"));
			String token = jwtTokenProvider.createToken(authentication);
			
			NewToken newToken = new NewToken(userDetails.get("user_id"), token);
			logger.info("{}", newToken);
			userMapper.updateToken(token, userDetails.get("user_id"));
			logger.info("{}", newToken.getToken());
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Bearer " + newToken.getToken());
			
			logger.info("{}", newToken.getToken());
			
			return newToken.getToken();
		} else {
			return null;
		}
	}
	
	public int signUp(UserInfo userSignUp) {
		
		logger.info("[ userService signUp ]: {}", userSignUp);
		return userMapper.signUp(userSignUp);
		
	}
	
	public int save(UserInfo userSave) {
		
		return userMapper.save(userSave);
	}

	@Override
	public String loginChk(LoginReq loginReq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getLoginUserByLoginId(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
