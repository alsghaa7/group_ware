package com.mino.groupware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mino.groupware.jwt.JwtInterface;
import com.mino.groupware.jwt.JwtUtil;
import com.mino.groupware.vo.AuthRequest;

@Controller
public class JwtController{
	
	@Autowired
	private final JwtInterface jwtInterface;
	
	@Autowired
	public JwtController(JwtInterface jwtInterface) {
		this.jwtInterface = jwtInterface;
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	@ResponseBody
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("-----------");
		}
		return jwtInterface.generateToken(authRequest.getUserName());
	}
}