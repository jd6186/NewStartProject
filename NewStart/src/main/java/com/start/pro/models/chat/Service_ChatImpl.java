package com.start.pro.models.chat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_BWL;
import com.start.pro.dto.DTO_ChatList;
import com.start.pro.dto.DTO_ChatRoom;

@Service
public class Service_ChatImpl implements IService_Chat{

	@Autowired
	IDao_Chat dao;
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public DTO_ChatRoom Chat_Detail(String chat_seq) {
		log.info("ServiceImpl Chat_Detail 진입 : \t{}", chat_seq);
		return dao.Chat_Detail(chat_seq);
	}

	@Override
	public boolean Chat_Content(DTO_ChatRoom dtoR) {
		log.info("ServiceImpl Chat_Content 진입 : \t{}", dtoR);
		return dao.Chat_Content(dtoR);
	}

	@Override
	public boolean Chat_Update(DTO_ChatRoom dtoR) {
		log.info("ServiceImpl Chat_Update 진입 : \t{}", dtoR);
		return dao.Chat_Update(dtoR);
	}

	@Override
	public List<DTO_ChatList> Chat_List() {
		log.info("ServiceImpl DTO_ChatList 진입 : \t{}");
		return dao.Chat_List();
	}

	@Override
	public boolean Chat_Open(DTO_ChatList dtoL) {
		log.info("ServiceImpl Chat_Open 진입 : \t{}", dtoL);
		return dao.Chat_Open(dtoL);
	}

	@Override
	public boolean Chat_Renewal(String chat_seq) {
		log.info("ServiceImpl Chat_Renewal 진입 : \t{}", chat_seq);
		return dao.Chat_Renewal(chat_seq);
	}

	@Override
	public boolean Chat_TitleUpdate(DTO_ChatList dtoL) {
		log.info("ServiceImpl Chat_TitleUpdate 진입 : \t{}", dtoL);
		return dao.Chat_TitleUpdate(dtoL);
	}


}