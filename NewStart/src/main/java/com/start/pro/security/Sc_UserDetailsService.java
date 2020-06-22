package com.start.pro.security;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.start.pro.models.login.IService_Login;

public class Sc_UserDetailsService implements UserDetailsService{

	@Autowired
	private IService_Login service;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("d??"+username);
		Sc_User user = service.getPW(username);
		
		if (user == null) {
	         throw new InternalAuthenticationServiceException(username);
		}
		
		return user;
	}

}
