package com.start.pro.comm;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Filter implements javax.servlet.Filter {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터에 들어온건 ServletRequest객체인데 서블렛에서 사용하는 객체는 HttpServletRequest객체이므로 하위객체로 캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		// Client의 요청주소(IP) : remoteAddr
		String remoteAddr = StringUtils.defaultString(req.getRemoteAddr(), "-");
		// Server의 주소 : URL URI
		String uri = StringUtils.defaultString(req.getRequestURI(), "");
		// query String
		String queryString = StringUtils.defaultString(req.getQueryString(), "");
		// Header 정보에 있음 : 기타정보 referer, agent
		String agent = StringUtils.defaultString(req.getHeader("User-Agent"), "");
		
		String fullURL = uri;
		fullURL += StringUtils.isNotEmpty(queryString)?"?"+queryString:queryString;
		
		StringBuffer sb = new StringBuffer();
		sb.append(" : ")
		.append(remoteAddr)
		.append(" : ")
		.append(fullURL)
		.append(" : ")
		.append(agent);
		
		log.info("===============필터 통과================");
		log.info("LOG FILTER : "+sb.toString());
		// 잡아서 미안하다 그대로 가던길 가라~
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("===============필터 시작================");
	}
	
	@Override
	public void destroy() {
		log.info("===============필터 종료================");
	}

}
