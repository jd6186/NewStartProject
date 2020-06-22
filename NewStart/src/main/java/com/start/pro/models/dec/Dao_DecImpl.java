package com.start.pro.models.dec;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Declaration;

@Repository
public class Dao_DecImpl implements IDao_Dec {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.Dec.";

	@Autowired
	private SqlSessionTemplate session;
	//전체 조회하기
	@Override
	public List<DTO_Declaration> searchDecAll() {
		log.info("Dao searchDecAll");
		return session.selectList(NS+"searchDecAll");
	}
	//신고하기
	@Override
	public boolean insertDec(DTO_Declaration dto) {
		log.info("Dao insertDec");
		int isc =  session.insert(NS+"insertDec",dto);
		return isc>0?true:false;
	}
	//경고하기
	@Override
	public boolean updateDeccnt(String dec_seq) {
		log.info("Dao updateDeccnt");
		int isc =  session.insert(NS+"updateDeccnt",dec_seq);
		if (isc>0) {
			updateProDate(dec_seq);
			delCntChk(dec_seq);
		}
		return isc>0?true:false;
	}

	public String delCntChk(String dec_seq) {
		int isc =  session.insert(NS+"delCntChk",dec_seq);
		if (isc>0) {
			delCookie(dec_seq);
		}
		return isc>0?"경고 3회로 탈퇴 됨":"경고 1회 추가";
	}
	public void delCookie(String dec_seq) {
		session.delete(NS+"delCookie",dec_seq);
	}
	public void updateProDate(String dec_seq) {
		session.update(NS+"updateProDate",dec_seq);
	}
	


	
}
