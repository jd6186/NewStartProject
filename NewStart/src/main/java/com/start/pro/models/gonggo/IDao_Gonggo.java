package com.start.pro.models.gonggo;

import java.util.List;

import com.start.pro.dto.DTO_Bidding;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;

public interface IDao_Gonggo {

	public List<DTO_Gonggo> Gonggo_Show();
	
	public boolean gonggo_insert(DTO_Gonggo dto);
	
	public boolean gonggo_modify(DTO_Gonggo dto);
	
	public boolean gonggo_delete(String seq);
	
	public DTO_Gonggo gonggo_detail(String seq);
	
	/**
	 * 입찰자 유무 조회(1명이라도 있으면 'Y', 없으면 'N') 
	 * @param seq
	 * @return
	 */
	public String gonggo_bidding_view(String seq);
	
	/**
	 * 입찰자 목록 출력
	 * @param seq
	 * @return
	 */
	public List<DTO_Bidding> gonggo_biddinger(String seq);
	
	
	
	public String gonggo_fileox(String seq);
	
	
	/**
	 * 업로드된 파일  조회
	 * @param seq
	 * @return
	 */
	public DTO_File gonggo_upload_file_view(String seq);
	
	
	public List<DTO_Gonggo> BoardListRowM(DTO_Criteria cri);
	public List<DTO_Gonggo> BoardListRowT(DTO_Criteria cri);
	public List<DTO_Gonggo> BoardListRowA(DTO_Criteria cri);
	
	 public int BoardListTotalA();
	 public int BoardListTotalM();
	 public int BoardListTotalT();
	
	
	
}
