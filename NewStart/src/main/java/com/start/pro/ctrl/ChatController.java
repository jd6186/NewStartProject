package com.start.pro.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.dto.DTO_ChatList;
import com.start.pro.dto.DTO_ChatRoom;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.chat.IService_Chat;
import com.start.pro.models.user.IService_User;


@Controller
public class ChatController {

	@Autowired
	IService_Chat service;
	@Autowired
	IService_User service_user;

	Logger log = LoggerFactory.getLogger(getClass());
	
	// /chatInsert.do
	@RequestMapping(value="/chatConnect.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> chatConnect(HttpSession session) {
		log.info("채팅 리스트 출력을 시작합니다. ");
		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		String user = userD.getUser_seq();
		List<DTO_ChatList> lists_chat = service.Chat_List();

		// 채팅방 전체 목록 중에 접속한 유저가 속해있는 채팅방리스트를 추립니다.
		for (int i = 0; i < lists_chat.size(); i++) {
			String users = lists_chat.get(i).getUser_seq();
			if(users.compareTo(user) == -1) {
				lists_chat.remove(i);
				--i;
			}
		}
		
		// 정제된 리스트목록을 map에 담아 뿌려줍니다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chatRoom_list", lists_chat);
		return map;
	}
	
	
	// /chattingRoom.do
	@RequestMapping(value="/broadsocket.do", method = RequestMethod.GET)
	public String chattingRoom(String chat_seq, Model model, HttpSession session) {
		// 채팅방 정보 불러오기
		DTO_ChatRoom dto = service.Chat_Detail(chat_seq);
		String user_seq = dto.getUser_seq();
		String content = "";
		
		// 해당 채팅방의 글 조회하여 없으면 새로운 채팅방 열기
		content = dto.getChatcontent();
		model.addAttribute("user_seq", user_seq);
		model.addAttribute("content", content);
		model.addAttribute("chat_seq", chat_seq);
		return "chat/Broadsocket";
	}
	
	
	// /chatUpdate.do
	@RequestMapping(value="/chatUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> chatUpdate(String messageTextArea_val, String chat_seq) {
		log.info("채팅방 내용 업데이트를 시작합니다.");
		DTO_ChatRoom RDto = service.Chat_Detail(chat_seq);
		RDto.setChatcontent(messageTextArea_val);
		boolean result = service.Chat_Update(RDto);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", result+"");
		return map;
	}
	
}
