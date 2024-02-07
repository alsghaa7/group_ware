/*
 * package com.mino.groupware.jwt;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.mino.groupware.mapper.UserMapper; import
 * com.mino.groupware.vo.User;
 * 
 * @Service public class UserDetailsServiceImpl implements UserDetailsService {
 * 
 * @Autowired private final UserMapper userMapper;
 * 
 * public UserDetailsServiceImpl(UserMapper userMapper) { this.userMapper =
 * userMapper; }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { User user = userMapper.findByUsername(username);
 * if (user == null) { throw new
 * UsernameNotFoundException("@@@ User Not Found @@@" + username); } return
 * org.springframework.security.core.userdetails.User
 * .withUsername(user.getUsername()) .password(user.getPassword())
 * .roles(user.getRoles()) .build(); }
 * 
 * }
 */