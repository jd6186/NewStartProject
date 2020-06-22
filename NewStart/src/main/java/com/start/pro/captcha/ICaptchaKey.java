package com.start.pro.captcha;

import java.io.InputStream;
import java.net.HttpURLConnection;

public interface ICaptchaKey {

	/**
	 * attach에 따라서 키나 사용자와의 입력값과의 비교 결과를 반환합니다.
	 * "0" => 입력하면 이미지 key 반환
	 * "1&key=받아온 key값&value=사용자가 입력한 값" => 비교 
	 * @param apiURL 뒤에 붙일 값
	 * @return JSON형태의 결과 값
	 */
	public String get(String attach);
	
	/**
	 * 네이버쪽과 통신을 해 결과를 받아옵니다.
	 * @param 완성된 apiUrl
	 * @return 서버 통신 결과
	 */
	public HttpURLConnection connect(String apiUrl);
	
	/**
	 * connect실행을 통한 서버 통신 결과를 바탕으로 key 혹은 입력비교 결과를 가져옵니다.
	 * @param body
	 * @return json형태의 결과값
	 */
	public String readBody(InputStream body);
}
