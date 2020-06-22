package com.start.pro.models.dec;

import java.util.List;

import com.start.pro.dto.DTO_Declaration;

public interface IDao_Dec {

	
	//전체 조회
	public List<DTO_Declaration> searchDecAll();

	//신고하기
	public boolean insertDec(DTO_Declaration dto);
	
	//경고 횟수 증가
	public boolean updateDeccnt(String dec_seq);
	

		
}
