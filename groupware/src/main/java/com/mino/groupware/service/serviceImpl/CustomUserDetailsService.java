package com.mino.groupware.service.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mino.groupware.mapper.UserMapper;
import com.mino.groupware.vo.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
		User user = userMapper.loadUserByUsername(user_name);
		return new org.springframework.security.core.userdetails.User(user.getUser_name(), user.getUser_pswd(),new ArrayList<>());
	}
	
}