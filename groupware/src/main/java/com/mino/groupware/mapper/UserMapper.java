package com.mino.groupware.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mino.groupware.vo.User;
import com.mino.groupware.vo.UserInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
	
	//@Select("select * from user") 
	public List<Map<String, String>> getUserList();
	//¾È¾¸
	public int signUp(UserInfo userSignUp);
	
	public int save(UserInfo userSave);
	
	public String loginChk(Map<String, String> userInfo);
	
	public User findByUsername(String user_name);
}
