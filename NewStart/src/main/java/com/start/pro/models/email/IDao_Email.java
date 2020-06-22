package com.start.pro.models.email;

import java.util.List;
import java.util.Map;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Mounui;
import com.start.pro.dto.DTO_Paging;
import com.start.pro.dto.DTO_User;

public interface IDao_Email {

	//인증 이메일
	
	/**
	 * 인증 메일을 발송할 때 인증메일테이블에 데이터를 저장합니다.
	 * 필요한 키는 다음과 같습니다.
	 * user_email : 보내는 유저의 이메일을 넣습니다. 
	 * lj_key : 보내는 랜덤 인증번호를 넣습니다.
	 * LJ_CODE : 0 - 회원가입 인증 / 1 - 아이디 찾기 / 2 - 휴면회원 인증
	 * @param Map<String,String>
	 * @return boolean(true/false)
	 */
	public boolean sendLJ(Map<String, String> map);
	
	/**
	 * 이메일 인증할 때 사용합니다. 유저가 이메일의 버튼을 클릭해 들어오면
	 * 이메일과 키값을 받아 이를 통해 일치하는게 있는지 확인합니다.
	 * 필요한 키는 다음과 같습니다.
	 * user_email : 인증할 유저의 이메일을 넣습니다.
	 * lj_key : 인증할 키값을 넣습니다.
	 * @param Map<String,String>
	 * @return String code
	 */
	public String LJKey(Map<String,String> map);
	
	/**
	 * 인증으로 보내진 이메일은 1년동안 보관하게 됩니다.
	 * 하루에 한번씩 실행되며 1년이 지난 이메일은 이 쿼리를 통해
	 * 디비에서 완전히 지워지게 됩니다.
	 * @return boolean(true/false)
	 */
	public boolean DelLJ();
	
	// 자동 이메일
	/**
	 * 자동이메일 초기 설정입니다. 처음 만들때 딱 한번 실행되며
	 * 그 이후로는 사용할 일이 없습니다.
	 * 카테고리에 따라 처음만 실행됩니다. 혹은 후에 카테고리를 추가할 때
	 * 튜닝해서 사용할지도 모릅니다.
	 * 필요한 정보는 다음과 같습니다.
	 * email_title : 제목
	 * email_content : 내용
	 * filechk : 파일 존재 여부
	 * use_chk : 사용 여부
	 * @param DTO_Email
	 * @return boolean(true/false)
	 */
	public boolean SetAutoEmail(DTO_Email dto);
	
	/**
	 * 상황별 카테고리에 대한 자동 이메일을 수정할 때 사용합니다.
	 * 필요한 정보는 다음과 같습니다.
	 * email_title : 이메일 제목
	 * email_content : 이메일 내용
	 * filechk : 파일 첨부 여부
	 * use_chk : 사용 여부
	 * category_code : 변경할 카테고리 코드
	 * @param DTO_Email
	 * @return boolean(true/false)
	 */
	public boolean UpdateAuto(DTO_Email dto);
	
	/**
	 * 상황별 카테고리에 대한 자동 이메일 설정한 내용 전체를 가져옵니다.
	 * 가져오는 정보는 다음과 같습니다.
	 * CATEGORY_CODE : 카테고리 코드
	 * EMAIL_TITLE : 10자가 넘으면 ... 상태로 가져옵니다.
	 * USE_CHK : 사용 여부
	 * @return List<DTO_Email>
	 */
	public List<DTO_Email> SelAuto();
	
	/**
	 * 자동 이메일 설정 상세조회를 할 때 사용됩니다.
	 * 가져오는 정보는 다음과 같습니다.
	 * CATEGORY_CODE : 카테고리 코드
	 * EMAIL_TITLE : 이메일 제목
	 * EMAIL_CONTENT : 이메일 내용
	 * FILECHK : 파일 첨부 여부
	 * USE_CHK : 사용 여부
	 * @param String category_code
	 * @return DTO_Email
	 */
	public DTO_Email SelDetailAuto(String code);
	
	
	/**
	 * 자동 메일을 발송할 때 카테고리 코드에 의해 정보들을 가져옵니다.
	 * 가져오는 데이터는 다음과 같습니다.
	 * CATEGORY_CODE : 카테고리 코드
	 * EMAIL_TITLE : 이메일 제목
	 * EMAIL_CONTENT : 이메일 내용
	 * FILECHK : 파일 첨부 여부
	 * @param code
	 * @return DTO_Email
	 */
	public DTO_Email getEmailInfo(String code);
	
	
	// 이메일 발송 이력
	/**
	 * 이메일은 발송될때 대기상태(S)로 일단 디비에 저장이 됩니다.
	 * 후에 상태에 따라서 업데이트 해주는데 처음 대기상태로 입력할 때
	 * 이를 사용합니다. 
	 * 대량메일 발송이 실패할때는 실패상태(F)로 새롭게 저장되게 됩니다.
	 * 필요한 정보는 다음과 같습니다.
	 * category_code : 보내는 메일의 카테고리 코드
	 * email_title : 이메일 제목
	 * email_content : 이메일 내용
	 * filechk : 파일 첨부 여부
	 * user_email : 수신자 이메일
	 * 수신자 이메일은 json 배열 형태로 입력합니다.
	 * successchk : 처음은 S, 대량메일 실패는 F로 지정합니다.
	 * @param DTO_Email
	 * @return String Email_SEQ(해당 이메일의 seq)
	 */
	public DTO_Email SendEmail(DTO_Email dto);
	 
	
	/**
	 * 메일의 상태를 변겅할 때 사용됩니다.
	 * 단일/다중 메일 성공
	 * email_seq : 이메일 데이터의 seq입니다.
	 * successchk : 값은 Y로 넣어주시면 됩니다.
	 * 
	 * 단일 메일 실패
	 * email_seq : 이메일 데이터의 seq입니다.
	 * successchk : 값은 F로 넣어주시면 됩니다.
	 * fail_reason : 값은 실패이유를 넣어주시면 됩니다.
	 * 
	 * 다중 메일 부분 성공
	 * email_seq : 이메일 데이터의 seq입니다.
	 * successchk : 값은 Y로 넣어주시면 됩니다.
	 * user_email : 실패한 이메일을 제외한 성공한 이메일들을 넣어주면 됩니다.
	 * json 배열 형식입니다.
	 * 
	 * 
	 * @param Map<String,String>
	 * @return boolean
	 */
	public boolean MailSuccess(Map<String,String> map);
	
	
	
	
	/**
	 * 이메일을 재전송하려할 때 seq값을 통해 이메일 정보를 가져옵니다.
	 * 받아오는 데이터는 다음과 같습니다.
	 * CATEGORY_CODE : 카테고리 코드
	 * EMAIL_TITLE : 이메일 제목
	 * EMAIL_CONTENT : 이메일 내용
	 * FILECHK : 파일첨부 여부
	 * USER_EMAIL : 수신자 이메일
	 * 이메일은 JSON 배열 형태로 되어있습니다.
	 * @param seq
	 * @return DTO_Email
	 */
	public DTO_Email mailresend(String seq);
	
	/**
	 * 발송 메일 이력은 1년까지 가지고 있다가 삭제됩니다.
	 * 이는 하루에 한번 실행됩니다.
	 * @return boolean
	 */
	public boolean DelMail();
	
	/**
	 * 전체 발송 이력을 조회할 때 사용됩니다.
	 * 가져오는 데이터는 다음과 같습니다.
	 * EMAIL_SEQ : 이메일 seq
	 * CATEGORY_CODE : 카테고리 코드
	 * EMAIL_TITLE : 이메일 제목, 10글자가 넘어가면 ...으로 대체됩니다.
	 * USER_EMAIL : 수신자 이메일 / json배열로 가져옵니다
	 * SUCCESSCHK : 상태 체크
	 * REGDATE : 발송일
	 * @return List<DTO_Email>
	 */
	public List<DTO_Email> SelAllMail(DTO_Filter dto);
	
	
	 /**
	  * 발송이력을 상세보기 할 때 실행됩니다.
	  * 가져오는 데이터는 다음과 같습니다.
	  * EMAIL_SEQ : 이메일 seq
	  * CATEGORY_CODE : 카테고리 코드
	  * EMAIL_TITLE : 이메일 제목
	  * EMAIL_CONTENT : 이메일 내용
	  * FILECHK : 파일 체크
	  * USER_EMAIL : 수신자 이메일
	  * SUCCESSCHK : 상태체크
	  * FAIL_REASON : 실패이유
	  * REGDATE : 발송일
	  * 
	  * USER_EMAIL은 json배열로 가져옵니다
	  * @param seq
	  * @return DTO_Email
	  */
	public DTO_Email SelMailDetail(String seq);
	
	/**
	 * 필터를 통한 부분 검색 시 실행됩니다.
	 * 필요한 데이터는 다음과 같습니다.
	 * firstDate : 시작 날짜 YYYYMMDD
	 * lastDate : 마지막 날짜 YYYYMMDD
	 * filter : 필터 조합 String
	 * successchk : 발송 상태 Y, S, F
	 * @param DTO_Filter
	 * @return List<DTO_Email>
	 */
	public List<DTO_Email> SelMailFilter(DTO_Filter dto);
	
	
	// 수동 이메일
	/**
	 * 수동 메일 발신 시 필터에 따라 수신자 이메일들을 가져옵니다.
	 * 필요한 키는 다음과 같습니다.
	 * user_grade : 유저의 등급
	 * user_type : 유저의 타입
	 * user_adchk : 유저의 광고수신상태(Y를 권장합니다.)
	 * @param Map<String, String>
	 * @return List<String>
	 */
	public List<String> SelUserFiter(Map<String, String> map);
	
	/**
	 * 문의 답변 이메일을 보낼 때 답변 데이터를 저장합니다.
	 * 필요한 데이터는 다음과 같습니다.<br>
	 * mounui_seq, title, content, filechk
	 * @param DTO_Mounui
	 * @return boolean
	 */
	public boolean SendReply(DTO_Mounui dto);
	
	
	/**
	 * 관리자가 답변 이메일을 조회할 때 사용합니다.<br>
	 * 가져오는 데이터는 다음과 같습니다.<br>
	 * mounui_seq, title, content, filechk, successchk
	 * successchk는 Y와 N으로 DTO_Mounui의 delchk에 넣어주시면 됩니다.
	 * @param mounui_seq
	 * @return DTO_Mounui
	 */
	public DTO_Mounui selReplyAemail(String seq);
	
	/**
	 * 이메일 기록을 다중 삭제할 때 사용합니다
	 * @param map : seq
	 * @return boolean
	 */
	public boolean delemailsave(Map<String,String[]> list);
	
	/**
	 * 이메일 기록 전체 글 갯수를 가져온다.
	 * @return int
	 */
	public int getEmailCount(DTO_Filter dto);
	
	/**
	 * 이메일 내용 바꿀때 사용한다.
	 * ${name}, ${nickname}
	 * @param email
	 * @return DTO_User
	 */
	public DTO_User getinfo(String email);
}
