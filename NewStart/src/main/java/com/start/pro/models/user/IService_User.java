package com.start.pro.models.user;


import java.util.List;

import com.start.pro.dto.DTO_User;

public interface IService_User {

	/**
	 * 회원 번호를 받으면 회원 상세조회 이고 회원 번호를 받지 않으면 전체 조회 이다.
	 * @param 회원 번호user_seq
	 * @return 회원 정보
	 */
	public List<DTO_User> searchAll();
	public DTO_User searchDetail(String user_seq);

	
	/**
	 *  강사인증을 신청한 멘티를 강사로 등급을 변경해준다.
	 * @param user_seq(강사인증을 신청한 멘트 번호)
	 * @return 강사 승인시 true, 미승인 시 false
	 */
	public boolean updateTeacher(String user_seq);
	
	/**
	 * 마지막 로그인이 1년이 넘은 회원의 유형을 휴면회원으로 변경
	 * @param user_seq(마지막 로그인이 1년이 넘은 회원 번호)
	 * @return 유형 변경 성공 시 true, 아닐시 false;	
	 */
	public boolean updateHuman();
	
	/**
	 * 비밀번호 수정
	 * @param dto(수정될 비밀번호, 유저 번호)
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updatePw(DTO_User dto);
	
	/**
	 * 닉네임 수정
	 * @param dto(수정될 닉네임, 유저 번호)
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateNn(DTO_User dto);
	
	/**
	 * 전화번호 수정
	 * @param dto(수정될 전화번호, 유저 번호)
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updatePhone(DTO_User dto);
	
	/**
	 * 광고 수신 동의 여부 수정
	 * @param user_seq(유저 번호)
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateAdChk(String user_seq);
	
	/**
	 * 스위칭 기능
	 * @param user_seq(유저 번호)
	 * @return 성공시 true, 실패시 false
	 */
	public boolean updateGrade(String user_seq);
	
	/**
	 * 마이페이지 수정
	 * @param dto(닉네임, 전화번호) 
	 * @return  성공시 true, 실패시 false
	 */
	public boolean updateMyPage(DTO_User dto);
	
	/**
	 * 강사 인증 요청
	 * @param user_seq
	 * @return
	 */
	public boolean teacherReq(String user_seq);
	
	
	/**
	 * 강사 인증 요청 회원 전체 보기
	 * @return 리스트 형태 UserDto
	 */
	public List<DTO_User> searchTReqAll();
	/**
	 * 강사 인증 요청 회원 상세 보기
	 * @return UserDto
	 */
	public DTO_User searchTReqDetail(String user_seq);
	
	/**
	 * 강사 인증 승인
	 * @param user_seq
	 * @return
	 */
	public boolean teaRespY(String user_seq);
	
	
	/**
	 * 강사 인증 요청 미승인
	 * @param user_seq
	 * @return
	 */
	public boolean teaRespN(String user_seq);
	
	public String searchStarAvg(String user_seq);
}











