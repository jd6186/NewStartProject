package com.start.pro.captcha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component(value = "getKey")
public class CaptchaGetKey implements ICaptchaKey{
	
	//관리자 키와 비밀번호가 들어있는 map
	@Resource(name = "captchaMap")
	private Map<String, String> requestHeaders;
	
	// apiURL의 String
	@Resource(name = "apiURL")
	private String apiURL;
	
	
	//캡챠 이미지 Key를 받아오기
	@Override
	public String get(String attach) {
		// 0이면 이미지 key 받아오기 1이면 유저가 입력한 값과 비교하기
		// https://openapi.naver.com/v1/captcha/nkey?code= + "0"
		HttpURLConnection con = connect(apiURL+attach);
		
		// 키 받아오기
		try {
			for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				String key = readBody(con.getInputStream());
				return getkey(key);
				
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		}catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		}finally {
			con.disconnect();
		}
	}
	
	
	//url 넘겨서 서버 연동 결과 받기
	@Override
	public HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		}catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}
	
	//결과 통해서 key 받아오기
	@Override
	public String readBody(InputStream body){
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
	
	private String getkey(String jsonkey) {
		
		// json으로 key 값 뽑아오기
        JSONParser parser  = new JSONParser();
        Object obj = null;
		try {
			obj = parser.parse(jsonkey);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        JSONObject jsonobj = (JSONObject) obj;
        
        String key = (String) jsonobj.get("key");
        System.out.println(key);
        	//model.addAttribute("key",key);
        return key;
		
	}
}
