package com.mino.groupware.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	public String userCheck(Map<String, String> userInfo);
	
	/* public List<Map<String, String>> getUserList(); */
	public String loginChk(Map<String, String> userInfo);
	
}
