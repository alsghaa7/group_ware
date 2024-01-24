package com.mino.groupware.jwt;

import java.io.Serializable;

import com.mino.groupware.vo.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewToken {
	
	private String user_id;
	private String token;
	
	public String getToken() {
		return token;
	}
}