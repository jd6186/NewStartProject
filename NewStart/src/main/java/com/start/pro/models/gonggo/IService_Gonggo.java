package com.start.pro.models.gonggo;

import java.util.List;

import com.start.pro.dto.DTO_Bidding;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;

public interface IService_Gonggo {

	/**
	 *  공고글 전체 조회 
	 *  USER_GRADE = grade_seq
	 * @param dto
	 * @return
	 */
	public List<DTO_Gonggo> gonggo_View();
	
	
	/**
	 * 공고글 등록
	 * @param dto
	 * @return
	 */
	public boolean gonggo_insert(DTO_Gonggo dto);
	
	
	/**
	 * 공고글 수정
	 * @param dto
	 * @return
	 */
	public boolean gonggo_modify(DTO_Gonggo dto);
	
	
	/**
	 * 공고글 삭제
	 * 파일이 무조건 등록되어 있어야 되는데 파일 OX가 X라는건 더이상 없는 게시글이라는 뜻 
	 * 이렇게 되면 해당 게시글에 연관되어 있던 파일이 삭제되게 만들면 되겠다.
	 * @param seq
	 * @return
	 */
	public boolean gonggo_delete(String seq);
	
	/**
	 * 공고글 상세조회
	 * @param seq
	 * @return
	 */
	public DTO_Gonggo gonggo_detail(String seq);
	
	/**
	 * 입찰한 사람 목록조회 (gonggo_bidding_view + gonggo_biddinger)
	 * @param seq
	 * @return
	 */
	public List<DTO_Bidding> gonggo_biddingerList(String seq);
	
	
	/**
	 * 파일 유무 조회 (있으면 해당 파일을 가져오게 할거임)
	 * @param seq
	 * @return
	 */
	public String gonggo_fileox(String seq);
	
	/**
	 * 위에서 y인 것들의 seq를 파일 보드로 보내서 업로드된 파일  조회
	 * (있으면 파일 첨부되어있다고 표시하고 파일 테이블로부터 데이터 받아오게 만들기)
	 * @param seq
	 * @return
	 */
	public List<DTO_File> gonggo_fileList(String seq);


	/**
	 * 페이징을 하기 위해 필요한 DTO_Criteria cri를 활용하기
	 * @param dto
	 * @return
	 */
	public List<DTO_Gonggo> BoardListRowM(DTO_Criteria cri);
	public List<DTO_Gonggo> BoardListRowT(DTO_Criteria cri);
	public List<DTO_Gonggo> BoardListRowA(DTO_Criteria cri);
	
	 
	 /**
	  * 전체 글 리스트의 개수를 추출해내는 것
	  * @return
	  */
	 public int BoardListTotalA();
	 public int BoardListTotalM();
	 public int BoardListTotalT();

}
