package com.start.pro.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.start.pro.captcha.ICaptchaKey;

public class Sc_LoginFailureHandler implements AuthenticationFailureHandler{
	
	@Autowired
	private MessageSourceAccessor message;

	@Resource(name = "getKey")
	private ICaptchaKey getKey;
	
	private String defaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
			AuthenticationException exception) throws IOException, ServletException {

		String id = req.getParameter("username");
		String password = req.getParameter("password");
		String errormsg = null;
		
		loginFailureCount(req);
		
		if(exception instanceof LockedException){
			errormsg = message.getMessage("error.LockedException");
		}else if(exception instanceof BadCredentialsException) {
			errormsg = message.getMessage("error.BadCredentials");
		}else if(exception instanceof InternalAuthenticationServiceException) {
			errormsg = message.getMessage("error.BadCredentials");
		}
		
		req.setAttribute("id", id);
		req.setAttribute("password", password);
		req.setAttribute("error", errormsg);
		
		req.getRequestDispatcher(defaultFailureUrl).forward(req, resp);
	}
	
	
	private void loginFailureCount(HttpServletRequest req) {
		
		ServletContext app = req.getSession().getServletContext();
		Object cnt = app.getAttribute("failchk"); 
		app.setAttribute("failchk", (cnt == null) ? 1 : (Integer)cnt+1);
	
	}
	

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	
}
