package com.mino.groupware.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.mino.groupware.vo.UserInfo;

public interface UserService {
	
	public String userCheck(Map<String, String> userInfo);
	
	public int signUp(UserInfo userSignUp);
		
	public int save(UserInfo userSave);
	
	public String loginChk(Map<String, String> userInfo);

}
