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

import org.springframework.stereotype.Component;

@Component(value = "valChk")
public class CaptchaValChk implements ICaptchaKey{


	@Resource(name = "captchaMap")
	private Map<String, String> requestHeaders;

	@Resource(name = "apiURL")
	private String apiURL;
	
	
	@Override
	public String get(String attach) {
		System.out.println(apiURL+attach); 
		//서버 통신
		// 0이면 이미지 key 받아오기 1이면 유저가 입력한 값과 비교하기
		// "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value;
		HttpURLConnection con = connect(apiURL+attach);
		 
		 try {
				con.setRequestMethod("GET");
				for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
					con.setRequestProperty(header.getKey(), header.getValue());
				}

				int responseCode = con.getResponseCode();
				//서버 통신 결과 
				if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
					return readBody(con.getInputStream());
				} else { // 에러 발생
					return readBody(con.getErrorStream());
				}
			} catch (IOException e) {
				throw new RuntimeException("API 요청과 응답 실패", e);
			} finally {
				con.disconnect();
			}
		}
	
	
	// url 통신결과
	@Override
	 public HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }
	
	// 결과값 json형태로 받아옴
	@Override
	 public String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }
	            System.out.println("인풋 스트림 결과"+responseBody.toString());
	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
	
}
