package com.start.pro.models.login;

import java.util.Map;

import com.start.pro.dto.DTO_User;
import com.start.pro.security.Sc_User;

public interface IDao_Login {

	//로그인
		/**
		 * 사용자가 입력한 아이디(이메일)을 통해 유저의 회원번호, 비밀번호, 권한, 타입을 가져옵니다.
		 * 가져온 정보를 토대로 Sc_User에 담아 사용합니다.
		 * @param 사용자가 입력한 아이디(user_email)
		 * @return 유저 Dto(DTO_User) 
		 */
		public Sc_User getPW(String user_email);

		/**
		 * 로그인에 성공했을 시 세션에 담을 회원정보들을 가져옵니다.
		 * 유저의 회원번호를 통해 유저의 기본정보들을 가져옵니다.
		 * (USER_SEQ, USER_EMAIL, USER_NICKNAME, USER_NAME, 
		 *   USER_GRADE, USER_TYPE, USER_PHONE, USER_ADCHK, USER_REGDATE, USER_TCHK)
		 * @param 회원번호(user_seq)
		 * @return 유저Dto(DTO_User)
		 */
		public DTO_User getUser(String user_seq);
		
		/**
		 * 사용자 아이디(이메일)을 통해  로그인 날짜 업데이트를 해줍니다.
		 * 로그인 성공했을 시 사용합니다.
		 * @param 사용자 아이디(user_email)
		 * @return boolean(성공, 실패)
		 */
		public boolean loginUpdate(String user_email);

		/**
		 * 사용자 회원번호를 통해 휴면회원(H)를 일반 유저(N)으로 변경합니다.
		 * 휴면회원이 이메일 인증을 했을 시 사용합니다.
		 * @param 회원이메일(user_email)
		 * @return boolean(성공, 실패)
		 */
		public boolean changeSleep(String user_email);
		
		/**
		 * 사용자 아이디(이메일)을 통해 아이디에 해당하는 비밀번호 틀린 횟수를 1 추가해줍니다.
		 * @param 사용자 아이디(user_email)
		 * @return boolean(성공, 실패)
		 */
		public boolean plusPWFail(String user_email);
		
		/**
		 * 사용자 아이디(이메일)을 통해 해당 아이디에 대한 비밀번호 틀린 횟수를 가져옵니다.
		 * @param 사용자 아이디(user_email)
		 * @return 실패횟수(String)
		 */
		public String getPWFail(String user_email);
		
		//회원정보
		/**
		 * 회원가입을 할 때 아이디와 닉네임 중복검사를 실시합니다. 
		 * 파라미터의 값에 따라 아이디/닉네임 중복검사가 달라집니다.
		 * USER_EMAIL => 아이디 중복체크
		 * USER_NICKNAME => 닉네임 중복체크
		 * @param Map<String,String> 
		 * @return boolean(성공, 실패)
		 */
		public boolean MultipleChk(Map<String, String> map);
		
		
		/**
		 * 회원가입 성공 후 회원 정보와 회원 로그를 입력합니다.
		 * 트랜잭션(signUp,signUpLog)
		 * @param 유저 Dto(DTO_User)
		 * @return boolean(성공, 실패)
		 */
		public DTO_User signUp(DTO_User userDto);
		
		
		/**
		 * 회원가입 성공 후 회원번호를 통해 유저 로그 정보도 입력해줍니다.
		 * @param 회원번호(user_seq)
		 * @return boolean(성공, 실패)
		 */
		public boolean signUpLog(String user_seq);
		
		/**
		 * 회원가입 성공 후 이메일 인증 성공 시 회원 권한을 잠금(L)에서 일반(N)으로 변경해줍니다.
		 * @param 회원이메일(user_email)
		 * @return boolean(성공, 실패)
		 */
		public boolean changeNomal(String user_email);
		
		//아이디 찾기
		/**
		 * 아이디 찾기 실시할 때 사용자의 휴대폰 번호를 통해 아이디(이메일)을 가져옵니다.
		 * @param 핸드폰 번호(user_phone)
		 * @return 아이디(USER_EMAIL)
		 */
		public String findId(String user_phone);
		
		//비밀번호 변경
		/**
		 * 사용자가 비밀번호 변경했을 시 회원 아이디를 통해 변경된 비밀번호로 업데이트합니다.
		 * key : USER_EMAIL / USER_PW
		 * @param Map<String,String>
		 * @return boolean(성공, 실패)
		 */
		public boolean changePW(Map<String, String> map);
		
		/**
		 * 비밀번호가 변경 완료시 사용자 이메일을 통해 비밀번호 변경일을 최신화해줍니다.
		 * @param 사용자 아이디(user_email)
		 * @return boolean(성공, 실패)
		 */
		public boolean updatePWLog(String user_email);
		
	
	
		
}
