package com.start.pro.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.login.IService_Login;

public class Sc_LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private IService_Login service;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	private String defaultUrl;

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
			Authentication authentication) throws IOException, ServletException {

		HttpSession session = req.getSession();
		String id = ((Sc_User)authentication.getPrincipal()).getUser_email();
		DTO_User userDto = service.getUser(id);
		
		// 로그인성공 업데이트
		service.loginUpdate(id);
		session.setAttribute("newstart", userDto);
		
		req.getSession().getServletContext().removeAttribute("failchk");

		resultRedirectStrategy(req, resp, authentication, userDto);
	}
	
	protected void resultRedirectStrategy(HttpServletRequest req, HttpServletResponse resp,
			Authentication authentication,DTO_User dto) throws IOException, ServletException {

		SavedRequest savedRequest = requestCache.getRequest(req, resp);
		
		if(dto.getUser_type().equalsIgnoreCase("L")) {
			redirectStratgy.sendRedirect(req, resp, "/logoutSuccess.do?grade="+dto.getUser_type());
		}else if(dto.getUser_type().equalsIgnoreCase("H")) {
			redirectStratgy.sendRedirect(req, resp, "/logoutSuccess.do?grade="+dto.getUser_type());
		}else if(savedRequest!=null) {
			redirectStratgy.sendRedirect(req, resp, savedRequest.getRedirectUrl());
		} else {
			redirectStratgy.sendRedirect(req, resp, defaultUrl);
		}
	}
	
	
	
	
	
	@SuppressWarnings("unused")
	private void clearErrorSession(HttpServletRequest req, DTO_User userDto) {
		
		HttpSession session = req.getSession();
		
		//유저 세션 담기
		session.setAttribute("newstart", userDto);
		
		String error = (String) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		System.out.println("에러가 세션에 담겨있나요??"+error);
		
		if(session==null) return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
}
