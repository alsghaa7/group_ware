package com.mino.groupware.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo implements UserDetails{

	private int user_no;
	private String user_id;
	private String user_pswd;
	private String user_name;
	private String user_email;
	private String user_tel;
	private String user_address1;
	private String user_address2;
	private String user_gender;
	private String user_admin;
	private String token;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		  List<GrantedAuthority> authorities = new ArrayList<>();
          return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user_pswd;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user_id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}