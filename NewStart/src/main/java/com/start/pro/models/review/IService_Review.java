package com.start.pro.models.review;

import java.util.List;

import com.start.pro.dto.DTO_Career;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Review;

public interface IService_Review {

	/**
	 * 후기 게시판 전체 조회
	 * @return DTO_Review
	 */
	public List<DTO_Review> searchAll(DTO_Criteria cri);
	
	/**
	 * 게시글 총 갯수
	 * @return 총 갯수
	 */
	public int listCount();
	/**
	 * 후기 게시판 상세 조회
	 * @param re_seq(게시물 번호)
	 * @return 게시판 정보
	 */
	public DTO_Review searchDetail(int re_seq);
	
	/**
	 * 후기 게시판 게시물 등록
	 * @param dto(해당 강사,제목, 내용, 별점)
	 * @return 등록 성공 시 true, 실패시 false
	 */
	public boolean insertReview(DTO_Review dto);
	
	/**
	 * 후기 게시판 수정(답글 존재 시 수정 불가)
	 * @param dto
	 * @return 수정 성공 시 true, 실패시 false
	 */
	public boolean updateReview(DTO_Review dto);
	
	/**
	 * 게시물 삭제(답글 존재 시 수정 불가)
	 * @param re_seq(게시글 번호)
	 */
	public boolean delReview(int re_seq);
	
	/**
	 * 답글 입력(부모글 업데이트 후 답글 insert)
	 * @param re_seq(부모글 번호)
	 * @param dto (답글 제목, 글 ,별점)
	 * @return 등록 성공 시 true, 실패시 false
	 */
	public boolean replyInsert(int re_seq, DTO_Review dto);
	
	/**
	 * 내가 올린 공고 중 매칭 시도가 있는 게시글
	 * @param user_seq(사용자의 번호)
	 * @return 공고 글의 번호
	 */
	public List<Integer> searchMaching(String user_seq);
	
	/**
	 * 매칭이 성공한 공고의 강사 찾기
	 * @param gonggo_seq(공고글 번호 )
	 * @return 매칭이 성공한 강사가 있으면  강사 seq, 없으면 null 반환(화면에서 처리)
	 */
	public String searchTeacher(int gonggo_seq);
	
}





