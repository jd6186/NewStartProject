package com.start.pro.models.mounui;

import java.util.List;
import java.util.Map;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_FAQ;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Mounui;

public interface IService_Mounui {

	/**
	 * 문의글을 등록할 시 사용한다.
	 * 필요한 데이터는 다음과 같다.<br>
	 * user_seq, category, title, content, filechk
	 * @param DTO_Mounui
	 * @return boolean
	 */
	public boolean insertBoard(DTO_Mounui dto);
	
	/**
	 * 유저의 회원번호를 통해 본인이 작성한 문의글들을 가져올 때 사용한다.
	 * 필요한 key는 다음과 같습니다.<br>
	 * user_seq : 사용자의 seq<br>
	 * start : 시작 글 번호
	 * last : 끝 글 번호
	 * @param map
	 * @return List<DTO_Mounui>
	 */
	public List<DTO_Mounui> userBoard(Map<String, String> map);
	
	/**
	 * 문의글 seq를 통해 글 상세조회할 때 사용한다.
	 * 가져오는 데이터는 다음과 같다<br>
	 * MOUNUI_SEQ, TITLE, CONTENT, REGDATE, REPLYCHK, FILECHK
	 * @param mounui_seq
	 * @return DTO_Mounui
	 */
	public DTO_Mounui userBoardDetail(String seq);
	
	/**
	 * 글 seq를 통해 수정할 때 사용한다.
	 * 필요한 데이터는 다음과 같다 <br>
	 * category, title, content, filechk, mounui_seq
	 * @param DTO_Mounui
	 * @return boolean
	 */
	public boolean updateBoard(DTO_Mounui dto);
	
	/**
	 * 유저가 삭제할 때 사용한다.
	 * 필요한 데이터는 다음과 같다.<br>
	 * key : String seq<br>
	 * value : String[] 
	 * @param Map<String, String[]> map
	 * @return boolean
	 */
	public boolean delBoard(Map<String, String[]> map);
	
	/**
	 * 관리자가 삭제한 글까지 포함한 모든 글을 가져올 때 사용한다.
	 * 가져오는 데이터는 다음과 같다. <br>
	 *  MOUNUI_SEQ, USER_SEQ, CATEGORY, TITLE,REGDATE, REPLYCHK, FILECHK, DELCHK<br>
	 * 필터를 사용할 때는 DTO_Filter를 넣어 사용한다.
	 * 필터에 대한 값들은 다음과 같다.<br>
	 * USER_GRADE, REPLYCHK, DELCHK, firstDate, lastDate
	 * @return List<DTO_Mounui>
	 */
	public List<DTO_Mounui> adminBoard(DTO_Filter dto);
	 
	
	/**
	 * 관리자가 문의게시판을 전체 조회할 때 사용
	 * @param DTO_Filter
	 * @return int
	 */
	public int getAdminMounuiCnt(DTO_Filter dto);
	
	
	/**
	 * 관리자가 글을 상세조회할 때 사용한다.
	 * 가져오는 데이터는 다음과 같다.<br>
	 * MOUNUI_SEQ, USER_SEQ, CATEGORY, TITLE, CONTENT, REGDATE, REPLYCHK, FILECHK, DELCHK 
	 * @param mounui_seq
	 * @return DTO_Mounui
	 */
	public DTO_Mounui adminBoardDetail(String seq);
	
	/**
	 * 답변 이메일을 작성했을 시 답변처리여부를 Y로 바꿔준다.
	 * 
	 * @param mounui_seq
	 * @return boolean
	 */
	public boolean replyMounui(String seq);
	
	/**
	 * 관리자가 글을 직접 삭제할 때 사용한다.
	 * 필요한 키 값은 다음과 같다.<br>
	 * key : String seq<br>
	 * value : String[]
	 * 
	 * @param Map<String, String[]>
	 * @return boolean
	 */
	public boolean adminDelBoard(Map<String, String[]> map);
	
	/**
	 * 카테고리 목록을 가져올 때 사용한다.
	 * 가져오는 데이터는 다음과 같다.<BR>
	 * CATEGORY_SEQ, CATEGORY_TITLE
	 * @return List<DTO_FAQ>
	 */
	public List<DTO_FAQ> getCategory();

	/**
	 * 답변 정보를 가져옵니다.
	 * 가져오는 데이터는 다음과 같습니다.<br>
	 * TITLE, CONTENT, SUCCESSCHK, REGDATE
	 * @param seq
	 * @return DTO_Email
	 */
	public DTO_Email getReply(String seq);
	
	/**
	 * FAQ의 카테고리를 가져옵니다
	 * @return List
	 */
	public List<String> getTitle();
	
	/**
	 * 사용자의 seq를 통해 본인이 작성한 문의글의 갯수를 가져옵니다.
	 * @param String seq
	 * @return int
	 */
	public int getUserMounuiBoard(String seq);
	
	
}
