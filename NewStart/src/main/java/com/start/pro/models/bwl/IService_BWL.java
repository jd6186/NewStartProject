package com.start.pro.models.bwl;

import java.util.List;

import com.start.pro.dto.DTO_BWL;

public interface IService_BWL {
	/**
	 * 필요기능 1. 성공된 매칭의 매칭 입찰자 전체 조회하기(성공한 사람만 따로 조회하는 기능)
	 * @param seq
	 * @return
	 */
	public List<DTO_BWL> bwl_show();
	
	/**
	 * 필요기능 2. 해당 공고글 상세조회
	 * @param seq
	 * @return
	 */
	public DTO_BWL bwl_detail(String gonggo_seq);
	
	/**
	 * 필요기능 3. 필요기능 3. 성공된 매칭의 매칭 입찰자 등록하기 
	 * @param seq
	 * @return
	 */
	public boolean bwl_winner(DTO_BWL dto) ;
	
}
