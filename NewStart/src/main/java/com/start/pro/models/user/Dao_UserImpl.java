package com.start.pro.models.user;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_User;

@Repository
public class Dao_UserImpl implements IDao_User {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.User.";
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private PasswordEncoder passwordencoder;
	
	
	@Override
	public boolean updateTeacher(String user_seq) {
		log.info("DAO@@@@@강사 등급 업데이트,{}",user_seq);
		int isc = session.insert(NS+"updateTeacher",user_seq);
		return isc>0?true:false;
		
	}

	@Override
	public boolean updateHuman() {
		log.info("DAO@@@@@휴면 등급 업데이트,{}",new Date());
		int isc = session.insert(NS+"updateHuman");
		return isc>0?true:false;
	}

	@Override
	public List<DTO_User> searchAll() {
		log.info("DAO@@@@@회원 전체 조회,{}",new Date());
		return session.selectList(NS+"searchAll");
	}

	@Override
	public DTO_User searchDetail(String user_seq) {
		log.info("DAO@@@@@회원 상세 조회,{}",user_seq);
		return session.selectOne(NS+"searchDetail",user_seq);
	}

	@Override
	public boolean updatePw(DTO_User dto) {
		log.info("DAO@@@@@비밀번호 수정,{}",dto);
		int isc = session.update(NS+"updatePw",dto);
		return isc>0?true:false;
	}

	@Override
	public boolean updateNn(DTO_User dto) {
		log.info("DAO@@@@@닉네임 수정,{}",dto);
		int isc = session.update(NS+"updatePw",dto);
		return isc>0?true:false;
	}

	@Override
	public boolean updatePhone(DTO_User dto) {
		log.info("DAO@@@@@전화번호 수정,{}",dto);
		int isc = session.update(NS+"updatePw",dto);
		return isc>0?true:false;
	}

	@Override
	public boolean updateAdChk(String user_seq) {
		log.info("DAO@@@@@광고 수신여부 변경,{}",user_seq);
		int isc = session.update(NS+"updatePw",user_seq);
		return isc>0?true:false;
	}

	@Override
	public boolean updateGrade(String user_seq) {
		log.info("DAO@@@@@스위칭,{}",user_seq);
		int isc = session.update(NS+"updatePw",user_seq);
		return isc>0?true:false;
	}

	@Override
	public boolean updateMyPage(DTO_User dto) {
		log.info("DAO@@@@@마이페이지,{}",dto);

		int isc = session.update(NS+"updateMyPage",dto);
		return isc>0?true:false;
	}

	@Override
	public boolean teacherReq(String user_seq) {
		log.info("DAO@@@@@강사 요청,{}",user_seq);
		int isc = session.update(NS+"teacherReq", user_seq);
		
		return isc>0?true:false;
	}

	@Override
	public boolean tReqTime(String user_seq) {
		log.info("DAO@@@@@강사 요청 시간,{}",user_seq);
		int isc = session.insert(NS+"tReqTime",user_seq);
		return isc>0?true:false;
	}

	@Override
	public List<DTO_User> searchTReqAll() {
		log.info("DAO@@@@@강사 요청 전체 조회,{}",new Date());
		return session.selectList(NS+"searchTReqAll");
	}

	@Override
	public DTO_User searchTReqDetail(String user_seq) {
		log.info("DAO@@@@@강사 요청 상세 조회,{}",user_seq);
		return session.selectOne(NS+"searchTReqDetail",user_seq);
	}

	@Override
	public boolean teaRespY(String user_seq) {
		log.info("DAO@@@@@강사 요청 허가,{}",user_seq);
		int isc = session.update(NS+"teaRespY",user_seq);
		return isc>0?true:false;
	}

	@Override
	public boolean tRespTime(String user_seq) {
		log.info("DAO@@@@@강사 요청 허가 시간,{}",user_seq);
		int isc = session.update(NS+"tRespTime",user_seq);
		return isc>0?true:false;
	}

	@Override
	public boolean teaRespN(String user_seq) {
		log.info("DAO@@@@@강사 요청 미허가,{}",user_seq);
		int isc = session.update(NS+"teaRespN",user_seq);
		return isc>0?true:false;
	}

	@Override
	public String searchStarAvg(String user_seq) {
		log.info("DAO@@@@@별점 평균 구하기,{}",user_seq);
		return session.selectOne(NS+"searchStarAvg",user_seq);
	}


	
}
