package com.start.pro.models.push;

import java.util.List;

import com.start.pro.dto.DTO_Push;


public interface IService_Push {

	/**
	 * 필요기능 1. 새로운 알림 등록 기능
	 * @param DTO_Push dto
	 * @return boolean
	 */
	public boolean AlramInsert(DTO_Push dto);
	
	
	/**
	 * 필요기능 2. 전체 알림 조회 기능
	 * @param X
	 * @return List<DTO_Push>
	 */
	public List<DTO_Push> AlramList();
	
	
	/**
	 * 필요기능 3. 발생사건별 조회(조회 카테고리 선택할 수 있게 만들고 싶어서)
	 * @param String push_accident
	 * @return List<DTO_Push>
	 */
	public List<DTO_Push> AccidentSearch(String push_accident);
	
	
	/**
	 * 필요기능 4. 알림 상세조회 기능(PUSH_SEQ로 조회 하기) 
	 * @param String push_seq
	 * @return DTO_Push dto
	 */
	public DTO_Push AlramDetail(String push_seq);
	
	
	/**
	 * 필요기능 5. 알림 삭제기능 (상태를 발송완료 상태로 전환시키기) 
	 * @param String push_seq
	 * @return DTO_Push dto
	 */
	public boolean AlramDelete(String push_seq);
	
	
	/**
	 * 필요기능 6. 알림 일시정지 기능 (상태를 발송완료 상태로 전환시키기)
	 * @param String push_seq
	 * @return boolean
	 */
	public boolean AlramStop(String push_seq);
	
	/**
	 * 필요기능 7. 알림 다시 시작 기능 (상태를 발송완료 상태로 전환시키기)
	 * @param String push_seq
	 * @return boolean
	 */
	public boolean AlramRestart(String push_seq);
	
	/**
	 * 필요기능 8. PUSH_STATE(알림 상태(예약 발송 중, 일시정지, 발송완료))에 따른 조회 (수동)
	 * @param String push_state
	 * @return List<DTO_Push>
	 */
	public List<DTO_Push> PushState_select (String push_state);
	

	/**
	 * 필요기능 9. PUSH_AM(자동 수동)에 따른 조회
	 * @param String push_am
	 * @return List<DTO_Push>
	 */
	public List<DTO_Push> AutoMenual_select(String push_am);
	
	
	/**
	 * 필요기능 10. 자동알림 수정 기능
	 * @param DTO_Push
	 * @return boolean
	 */
	public boolean AutoAlram_Update (DTO_Push dto);
}
