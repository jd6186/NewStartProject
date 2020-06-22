package com.start.pro.models.chat;

import java.util.List;

import com.start.pro.dto.DTO_BWL;
import com.start.pro.dto.DTO_ChatList;
import com.start.pro.dto.DTO_ChatRoom;

public interface IService_Chat {


	/**
	 * 필요기능 1. 채팅방 상세 조회 
	 * @param seq
	 * @return
	 */
	public DTO_ChatRoom Chat_Detail(String chat_seq);
	
	
	
	
	/**
	 * 필요기능 2. 새로운 채팅내용 저장하기(테이블이 달라서 최초 인서트 시키는게 필요 첫 채팅 시에만 작동되게 만들면 된다.) 
	 * @param seq
	 * @return
	 */
	public boolean Chat_Content(DTO_ChatRoom dto);
	
	
	
	
	/**
	 * 필요기능 3. 채팅 내역 저장하기 (이름, 내용, 이미지FILEURL이 다 저장된다. 나머진 화면에서 알아서 처리)
	 * @param seq
	 * @return
	 */
	public boolean Chat_Update(DTO_ChatRoom dto) ;
	
	
	
	
	/**
	 * 필요기능 4. 해당 유저에게 열려있는 채팅방 목록 전체 조회
		채팅이 연결된지 14일 이상 지난 것들은 안보이게 처리하기
		채팅방 제목이 방별로 나오고 가장 마지막에 작성된 글이 있는 채팅방이 제일 위로 올라오게 정렬
		전체 채팅방 리스트 들고가서 나한테 해당되는 애들만 출력할 수 있게 json으로 되어 있는 USER_SEQ와 jstl의 fn태그를 적극 활용해봐
	 * @param seq
	 * @return
	 */
	public List<DTO_ChatList> Chat_List() ;
	
	
	
	/**
	 * 필요기능 5. 해당 유저에게 열려있는 채팅방 목록 전체 조회
	 * (채팅방 제목이 방별로 나오고 가장 마지막에 작성된 글이 있는 채팅방이 제일 위로 올라오게 정렬)
		여기서 USER_SEQ가 json형태로 저장되기 때문에 채팅방 목록을 띄울 때 json을 잘 뜯어야된다.
	 * @param seq
	 * @return
	 */
	public boolean Chat_Open(DTO_ChatList dto) ;
	
	
	
	/**
	 * 필요기능 6. 필요기능 3. LASTCHAT 업데이트 해주기 
	 * (채팅을 보내거나 상대 채팅이 오면 올 때마다 해당 시간으로 업데이트 해주기)
		이걸 항상 최신화 시켜주는 bean을 만들어서 servlet-context에 넣어두면 알아서 실행되면서 최신화 시켜주게되겠지? 추가되는게 있으면 자동으로 update가 숫자를 카운트해줄거고 카운트되는걸
		화면 아이콘 위에 띄워주면 현재 안읽은 메시지가 몇개인지 알 수 있게 되겠지?
	 * @param seq
	 * @return
	 */
	public boolean Chat_Renewal(String chat_seq) ;
	
	
	
	/**
	 * 필요기능 7. 채팅방 이름 변경해주기 
	 * (채팅을 보내거나 상대 채팅이 오면 올 때마다 해당 시간으로 업데이트 해주기) 
	 * @param seq
	 * @return
	 */
	public boolean Chat_TitleUpdate(DTO_ChatList dto) ;
	
	
	
}
