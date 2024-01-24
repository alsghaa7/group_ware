package com.mino.groupware.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.mino.groupware.jwt.NewToken;
import com.mino.groupware.vo.LoginReq;
import com.mino.groupware.vo.UserInfo;

public interface UserService {
	
	public String userCheck(Map<String, String> userInfo);
	
	public String loginChk(LoginReq loginReq);
	
	public int signUp(UserInfo userSignUp);
		
	public int save(UserInfo userSave);

	public Map<String, String> getLoginUserByLoginId(String user_id);
	
	public String loginTest(LoginReq req);
}
