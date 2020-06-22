package com.start.pro.models.login;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_User;
import com.start.pro.security.Sc_User;

@Repository
public class Dao_LoginImpl implements IDao_Login {

	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	private final String NS = "com.start.pro.login.";
	
	
	@Override
	public Sc_User getPW(String user_email) {
		return session.selectOne(NS+"getPW",user_email);
	}

	@Override
	public DTO_User getUser(String user_seq) {
		return session.selectOne(NS+"getUser", user_seq);
	}

	@Override
	public boolean loginUpdate(String user_email) {
		return (session.update(NS+"loginUpdate",user_email) > 0 ) ? true : false;
	}

	@Override
	public boolean changeSleep(String user_email) {
		return (session.update(NS+"changeSleep",user_email) > 0) ? true : false;
	}

	@Override
	public boolean plusPWFail(String user_email) {
		return (session.update(NS+"plusPWFail",user_email) > 0 ) ? true : false;
	}

	@Override
	public String getPWFail(String user_email) {
		return session.selectOne(NS+"getPWFail", user_email);
	}

	@Override
	public boolean MultipleChk(Map<String, String> map) {
		return ((Integer)session.selectOne(NS+"MultipleChk", map) > 0 ) ? true : false;
	}

	@Override
	public DTO_User signUp(DTO_User userDto) {
		String enPw = passwordencoder.encode(userDto.getUser_pw());
		userDto.setUser_pw(enPw);
		session.insert(NS+"signUp", userDto);
		return userDto;
	}
	
	@Override
	public boolean signUpLog(String user_seq) {
		return session.insert(NS+"signUpLog", user_seq) > 0 ? true : false;
	}


	@Override
	public boolean changeNomal(String user_email) {
		return session.update(NS+"changeNomal",user_email) > 0 ? true : false;
	}

	@Override
	public String findId(String user_phone) {
		return session.selectOne(NS+"findId", user_phone);
	}

	@Override
	public boolean changePW(Map<String, String> map) {
		String newPW = passwordencoder.encode(map.get("user_pw"));
		map.put("user_pw", newPW);
		return session.update(NS+"changePW", map) > 0 ? true : false;
	}

	@Override
	public boolean updatePWLog(String user_email) {
		return session.update(NS+"updatePWLog", user_email) > 0 ? true : false;
	}



	
}
