
package com.mino.groupware.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class JwtController {

	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final JwtInterface jwtInterface;

	@Autowired
	public JwtController(JwtInterface jwtInterface) {
		this.jwtInterface = jwtInterface;
	}

	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		logger.info("@@@ {}", authRequest);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword());
		logger.info("%%% {}", token);
		try {
			authenticationManager.authenticate(token);
		} catch (Exception e) {
			throw new Exception("JWT_CONTROLLER_EXCEPTION");
		}
		return jwtInterface.generateToken(authRequest.getUserName());
	}
}
