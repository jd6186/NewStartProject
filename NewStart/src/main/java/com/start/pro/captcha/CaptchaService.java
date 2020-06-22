package com.start.pro.captcha;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class CaptchaService {

	//키를 받아오는 클래스
	@Resource(name = "getKey")
	private ICaptchaKey getKey;
	
	
	public String getKey() {
		String key = getKey.get("0");
		System.out.println(key+"키 받아왔나요?");
		
		// json으로 key 값 뽑아오기
        JSONParser parser  = new JSONParser();
        Object obj = null;
		try {
			obj = parser.parse(key);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        JSONObject jsonobj = (JSONObject) obj;
        
        key = (String) jsonobj.get("key");
        System.out.println(key);
        	//model.addAttribute("key",key);
        return key;
	}
	
	
}
