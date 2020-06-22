package com.start.pro.models.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.pro.dto.DTO_User;
import com.start.pro.security.Sc_User;

@Service
public class Service_LoginImpl implements IService_Login{

	@Autowired
	private IDao_Login dao;

	@Override
	public Sc_User getPW(String user_email) {
		return dao.getPW(user_email);
	}

	@Override
	public DTO_User getUser(String user_seq) {
		return dao.getUser(user_seq);
	}

	@Override
	public boolean loginUpdate(String user_email) {
		return dao.loginUpdate(user_email);
	}

	@Override
	public boolean changeSleep(String user_email) {
		return dao.changeSleep(user_email);
	}

	@Transactional
	@Override
	public String PWFail(String user_email) {
		dao.plusPWFail(user_email);
		String pwfail = dao.getPWFail(user_email);
		return pwfail;
	}

	@Override
	public boolean MultipleChk(Map<String, String> map) {
		return dao.MultipleChk(map);
	}

	@Transactional
	@Override
	public boolean signUp(DTO_User userDto) {
		dao.signUp(userDto);
		
		boolean isc2 = dao.signUpLog(userDto.getUser_seq());
		return isc2;
	}

	@Override
	public boolean changeNomal(String user_email) {
		return dao.changeNomal(user_email);
	}

	@Override
	public String findId(String user_phone) {
		return dao.findId(user_phone);
	}
	
	@Transactional
	@Override
	public boolean updatePW(Map<String, String> map) {
		boolean isc1 = dao.changePW(map);
		boolean isc2 = dao.updatePWLog(map.get("user_email"));
		return (isc1||isc2)? true : false;
	}

	@Override
	public String getPWFail(String user_email) {
		return dao.getPWFail(user_email);
	}
	
	

}
