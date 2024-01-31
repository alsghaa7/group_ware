package com.mino.groupware.service;

import java.util.Map;
import com.mino.groupware.vo.UserInfo;

public interface UserService {
	
	public String userCheck(Map<String, String> userInfo);
	
	public int signUp(UserInfo userSignUp);
		
	public int save(UserInfo userSave);
	
	public String loginChk(Map<String, String> userInfo);

}
