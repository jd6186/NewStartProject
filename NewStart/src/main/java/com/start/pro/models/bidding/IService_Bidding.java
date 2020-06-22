package com.start.pro.models.bidding;

import java.util.List;
import java.util.Map;

import com.start.pro.dto.DTO_Bidding;

public interface IService_Bidding {
	
	/**
	 * 필요기능 1. 입찰하기(GONGO테이블 업데이트 필요 입찰자 유무 조회에서 Y로 바꾸기)
	 * @param seq
	 * @return
	 */
	public boolean bidding_insert(DTO_Bidding dto);
	
	/**
	 * 필요기능 1_1. GONGO테이블의 BIDDING Y로 업데이트(GONGO테이블 업데이트 필요 입찰자 유무 조회에서 Y로 바꾸기)
	 * @param seq
	 * @return
	 */
	public boolean bidding_update(String gonggo_seq);
	
	/**
	 * 필요기능 2. 해당 공고의 전체 입찰 내역(매칭 성공하기 전 까지)
	 * @param seq
	 * @return
	 */
	public List<DTO_Bidding> bidding_list(Map<String, String> map);
	
	
	
}
