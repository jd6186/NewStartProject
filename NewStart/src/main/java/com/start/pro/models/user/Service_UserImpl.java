package com.start.pro.models.user;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_User;

@Service
public class Service_UserImpl implements IService_User{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDao_User dao;
	
	

	@Override
	public boolean updateTeacher(String user_seq) {
		log.info("SERVICE@@@@@강사 인증 시 등급 업데이트,{}",user_seq);
		return dao.updateTeacher(user_seq);
	}

	@Override
	public boolean updateHuman() {
		log.info("SERVICE@@@@휴면회원 업데이트,{}");
		return dao.updateHuman();
	}

	@Override
	public List<DTO_User> searchAll() {
		log.info("SERVICE@@@@전체 회원 조회,{}",new Date());
		return dao.searchAll();
	}

	@Override
	public DTO_User searchDetail(String user_seq) {
		log.info("SERVICE@@@@상세 회원 조회,{}",user_seq);
		return dao.searchDetail(user_seq);
	}

	@Override
	public boolean updatePw(DTO_User dto) {
		log.info("SERVICE@@@@비밀번호 수정,{}",dto);
		return dao.updatePw(dto);
	}

	@Override
	public boolean updateNn(DTO_User dto) {
		log.info("SERVICE@@@@닉네임 수정,{}",dto);
		return dao.updateNn(dto);
	}

	@Override
	public boolean updatePhone(DTO_User dto) {
		log.info("SERVICE@@@@전화번호 수정,{}",dto);
		return dao.updatePhone(dto);
	}

	@Override
	public boolean updateAdChk(String user_seq) {
		log.info("SERVICE@@@@광고 수신 여부 변경,{}",user_seq);
		return dao.updateAdChk(user_seq);
	}

	@Override
	public boolean updateGrade(String user_seq) {
		log.info("SERVICE@@@@스위칭,{}",user_seq);
		return dao.updateGrade(user_seq);
	}

	@Override
	public boolean updateMyPage(DTO_User dto) {
		log.info("SERVICE@@@@마이페이지 업데이트,{}",dto);
		return dao.updateMyPage(dto);
	}

	@Override
	public boolean teacherReq(String user_seq) {
		log.info("SERVICE@@@@강사 요청 및 강사 요청 시간 입력,{}",user_seq);
		return dao.teacherReq(user_seq)&&dao.tReqTime(user_seq);
	}

	@Override
	public List<DTO_User> searchTReqAll() {
		log.info("SERVICE@@@@강사 요청한 유저 전체 보기,{}",new Date());
		return dao.searchTReqAll();
	}

	@Override
	public DTO_User searchTReqDetail(String user_seq) {
		log.info("SERVICE@@@@강사 요청한 유저 상세 보기,{}",user_seq);
		return dao.searchTReqDetail(user_seq);
	}

	@Override
	public boolean teaRespY(String user_seq) {
		log.info("SERVICE@@@@강사 요청 승인 및 강사 요청 승인 시간 입력,{}",user_seq);
		return dao.teaRespY(user_seq)&&dao.tRespTime(user_seq);
	}

	@Override
	public boolean teaRespN(String user_seq) {
		log.info("SERVICE@@@@강사 요청 및 강사 요청 시간 입력,{}",user_seq);
		return dao.teaRespN(user_seq);
	}

	@Override
	public String searchStarAvg(String user_seq) {
		log.info("SERVICE@@@@별점 평균 구하기,{}",user_seq);
		return dao.searchStarAvg(user_seq);
	}



}
