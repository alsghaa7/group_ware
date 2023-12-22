package com.mino.groupware.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mino.groupware.vo.UserInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
	
	//@Select("select * from user") 
	public List<Map<String, String>> getUserList();
	
	//로그인 정보에 맞는 유저정보 select
	public String loginChk(Map<String, String> userInfo);
	
	public int signUp(UserInfo userSignUp);
	
	public int save(UserInfo userSave);
}
