package com.start.pro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.start.pro.models.login.IService_Login;

public class Sc_AuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private IService_Login service;
	
	@Autowired
	private Sc_UserDetailsService userDetails;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String id = (String) authentication.getPrincipal();
		String pw = (String)authentication.getCredentials();
		
		System.out.println("CustomAuthenticationProvider동작하거든?"+id+":"+pw+":"+"user.getPassword()");
		
		Sc_User user = (Sc_User) userDetails.loadUserByUsername(id);
//		System.out.println((Integer.parseInt(service.getPWFail(id)) >= 5));
//		if(Integer.parseInt(service.getPWFail(id)) >= 5) {
//			throw new LockedException(id);
//		}
		if(!matchPassword(user.getPassword(),pw)) {
			throw new BadCredentialsException(id);
		}
		if(!user.isEnabled() || !user.isCredentialsNonExpired()) {
            throw new BadCredentialsException(id);
        }
		
		user.setUser_pw(null);
		
		return new UsernamePasswordAuthenticationToken(user, pw, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	private boolean matchPassword(String loginPwd, String password) {
		System.out.println("비번 체크하자~");
		if(passwordEncoder.matches(password, loginPwd)) {
			System.out.println("비밀번호 일치 참");
			return true;
		}
		System.out.println("비밀번호 불일치 참");
		return false;
	}
	
	
	
}
