package com.mino.groupware.service;

import java.util.Map;

import com.mino.groupware.vo.User;
import com.mino.groupware.vo.UserInfo;

public interface UserService {
	
	public String userCheck(Map<String, String> userInfo);
	
	public int signUp(UserInfo userSignUp);
		
	public int save(UserInfo userSave);
	
	public String loginChk(Map<String, String> userInfo);
	
	public void tokenUpdate(Map<String, String> updateInfo);
	
	public Map<String, String> findByUsername(String user_name);
	
	public void tokenDelete(Map<String, String> deleteInfo);
}
