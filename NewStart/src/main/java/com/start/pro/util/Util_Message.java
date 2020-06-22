package com.start.pro.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

public class Util_Message {

	@Autowired
	private MessageSourceAccessor msAcc;
	
	
	
	public String getMessage(String code) {
		System.out.println("이게모야??"+Locale.getDefault());
		return msAcc.getMessage(code,Locale.getDefault());
	}
}
