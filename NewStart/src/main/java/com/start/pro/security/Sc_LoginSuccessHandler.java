package com.start.pro.security;

import java.io.IOException;

import javax.servlet.ServletContext;
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
	
	private String loginidname;
	private String defaultUrl;
	
	


	public String getLoginidname() {
		return loginidname;
	}

	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}



	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("로긍니성공?");
		System.out.println(((Sc_User)authentication.getPrincipal()).getUser_email());
		System.out.println(defaultUrl);
		//입력 아이디
		String id = ((Sc_User)authentication.getPrincipal()).getUser_email();
		System.out.println(id);

//		String id = (String)authentication.getPrincipal();
//		System.out.println(seq);
		DTO_User userDto = service.getUser(id);
		
		System.out.println("멀받아왔어??"+userDto.toString());
		// 에러 지우기
		clearErrorSession(req, userDto);
		// 로그인성공 업데이트
		service.loginUpdate(id);
		ServletContext app = req.getSession().getServletContext();
		app.removeAttribute("failchk");
		
		
		
		resultRedirectStrategy(req, resp, authentication, userDto);
	}
	
	protected void resultRedirectStrategy(HttpServletRequest req, HttpServletResponse resp,
			Authentication authentication,DTO_User dto) throws IOException, ServletException {

		SavedRequest savedRequest = requestCache.getRequest(req, resp);
		
		System.out.println("사용자 권한은?" + dto.getUser_type());
		System.out.println("이건되고 왜 저건 안대"+defaultUrl);
		if(dto.getUser_type().equalsIgnoreCase("L")) {
			redirectStratgy.sendRedirect(req, resp, "/logoutSuccess.do?grade="+dto.getUser_type());
		}else if(dto.getUser_type().equalsIgnoreCase("H")) {
			redirectStratgy.sendRedirect(req, resp, "/logoutSuccess.do?grade="+dto.getUser_type());
		}else if(savedRequest!=null) {
			String targetUrl = savedRequest.getRedirectUrl();
			System.out.println("어디로 가려했어??"+targetUrl);
			redirectStratgy.sendRedirect(req, resp, targetUrl);
		} else {
			redirectStratgy.sendRedirect(req, resp, defaultUrl);
		}
	}
	
	@SuppressWarnings("unused")
	private void clearErrorSession(HttpServletRequest req, DTO_User userDto) {
		System.out.println("_이게없는거지?"+req.toString());
		HttpSession session = req.getSession();
		//유저 세션 담기
		session.setAttribute("newstart", userDto);
		
		String error = (String) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		System.out.println("에러가 세션에 담겨있나요??"+error);
		
		if(session==null) return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
}
