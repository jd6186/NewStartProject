package com.start.pro.models.review;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Review;

@Repository
public class Dao_ReviewImpl implements IDao_Review {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.Review.";
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<DTO_Review> searchAll(DTO_Criteria cri) {
		log.info("DAO@@@@@후기 게시판 전체 조회,{}",new Date());
		return session.selectList(NS+"searchAll",cri);
	}


	@Override
	public int listCount() {
		return session.selectOne(NS+"listCount");
	}

	@Override
	public DTO_Review searchDetail(int re_seq) {
		log.info("DAO@@@@@후기 게시판 상세 조회,{}",new Date());
		return session.selectOne(NS+"searchDetail",re_seq);
	}

	@Override
	public boolean insertReview(DTO_Review dto) {
		log.info("DAO@@@@@후기 게시판 게시글 등록,{}",dto);
		return session.insert(NS+"insertReview", dto)>0?true:false;
	}

	@Override
	public boolean updateReview(DTO_Review dto) {
		log.info("DAO@@@@@후기 게시판 수정,{}",dto);
		return session.update(NS+"updateReview", dto)>0?true:false;
	}

	@Override
	public boolean delReview(int re_seq) {
		log.info("DAO@@@@@후기 게시판 삭제,{}",new Date());
		return session.update(NS+"delReview", re_seq)>0?true:false;
	}

	@Override
	public boolean updateParent(int re_seq) {
		log.info("DAO@@@@@후기 게시판 부모글 업데이트,{}",re_seq);
		return  session.update(NS+"updateParent", re_seq)>0?true:false;
	}

	@Override
	public boolean insertReply(DTO_Review dto) {
		log.info("DAO@@@@@후기 게시판 답글 입력,{}",dto);
		return  session.update(NS+"insertReply", dto)>0?true:false;
	}

	@Override
	public List<Integer> searchMaching(String user_seq) {
		log.info("DAO@@@@@후기 게시판 공고글 찾기,{}",user_seq);
		return session.selectList(NS+"searchMaching",user_seq);
	}

	@Override
	public String searchTeacher(int gonggo_seq) {
		log.info("DAO@@@@@후기 게시판 강사 찾기,{}",gonggo_seq);
		return session.selectOne(NS+"searchTeacher",gonggo_seq);
	}

	


	
}
