package com.start.pro.models.chat;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_BWL;
import com.start.pro.dto.DTO_ChatList;
import com.start.pro.dto.DTO_ChatRoom;

@Repository
public class Dao_ChatImpl implements IDao_Chat{

	@Autowired
	SqlSessionTemplate session;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.chat.";
	
	
	@Override
	public DTO_ChatRoom Chat_Detail(String chat_seq) {
		log.info("Dao_ChatImpl의 Chat_Detail : " + chat_seq);
		return session.selectOne(NS+"Chat_Detail", chat_seq);
	}
	
	
	@Override
	public boolean Chat_Content(DTO_ChatRoom dto) {
		log.info("Dao_ChatImpl의 Chat_Content : " + dto);
		int n = session.insert(NS+"Chat_Content", dto);
		return n>0?true:false;
	}
	
	
	@Override
	public boolean Chat_Update(DTO_ChatRoom dto) {
		log.info("Dao_ChatImpl의 Chat_Update : " + dto);
		int n = session.update(NS+"Chat_Update", dto);
		return n>0?true:false;
	}
	
	// 여기서  USER_SEQ를 잘뜯어야된다. 
	// 전체 채팅방 리스트를 던져줄테니까 
	// admin이면 전체 채팅방 목록이 다 뜨게 만들기
	// 멘티나 강사가 접속하면 CONTROLLER에서 지금 접속한 newstart의 유저가 속해있는 채팅방만 조회하기
	@Override
	public List<DTO_ChatList> Chat_List() {
		log.info("Dao_ChatImpl의 DTO_ChatList : ");
		List<DTO_ChatList> lists = session.selectList(NS+"Chat_List");
		return lists;
	}
	
	
	@Override
	public boolean Chat_Open(DTO_ChatList dto) {
		log.info("Dao_ChatImpl의 Chat_Open : " + dto);
		int n = session.insert(NS+"Chat_Open", dto);
		return n>0?true:false;
	}
	
	
	@Override
	public boolean Chat_Renewal(String chat_seq) {
		log.info("Dao_ChatImpl의 Chat_Renewal : " + chat_seq);
		int n = session.update(NS+"Chat_Renewal", chat_seq);
		return n>0?true:false;
	}
	
	
	@Override
	public boolean Chat_TitleUpdate(DTO_ChatList dto) {
		log.info("Dao_ChatImpl의 Chat_TitleUpdate : " + dto);
		int n = session.update(NS+"Chat_TitleUpdate", dto);
		return n>0?true:false;
	}

}
