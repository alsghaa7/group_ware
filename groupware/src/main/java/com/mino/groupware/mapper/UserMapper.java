package com.mino.groupware.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
	
//	@Select("select * from user") 
	public List<Map<String, String>> getUserList();
}
