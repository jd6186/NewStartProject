package com.start.pro.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.start.pro.captcha.ICaptchaKey;
import com.start.pro.models.login.IService_Login;

@RestController
public class RestController_Login {

	@Autowired
	private IService_Login service;
	
	// 사용자 입력값 판단하는 클래스
	@Resource(name = "valChk")
	private ICaptchaKey valchk;

	//키를 받아오는 클래스
	@Resource(name = "getKey")
	private ICaptchaKey getKey;
	
	//결과값을 표출하는 페이지
	@RequestMapping(value = "/valchk.do", method = RequestMethod.POST)
	public String chk(String chk, String key) {

		String attach = "1&key="+key+"&value="+chk;
		String result = valchk.get(attach);

		return result;
	}

	//캡챠가 구현되는 페이지
	@RequestMapping(value = "/getKey.do", method = RequestMethod.POST)
	public String main() {
		
		String key = getKey.get("0");
		
        return key;
         
	}
	
	@RequestMapping(value = "/{pathval}/MultiChk.do", method = RequestMethod.POST)
	public boolean MultiChk(@PathVariable String pathval, String val) {

		Map<String, String> map = new HashMap<String, String>();
		map.put(pathval, val);
		boolean isc = service.MultipleChk(map);
		return isc;
	}

}
