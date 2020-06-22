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
	
	
	
	
	// /chatRoom.do
	@RequestMapping(value="/chatRoom.do", method = RequestMethod.GET)
	public String chatRoom() {
		System.out.println("※※※※※※※※※※※※※※chatRoom.do 값은 들어옶니다. ※※※※※※※※※※※※");
		return "chat/ChatRoom";
	}
	
	
	// /chatRoom_A.do
	@RequestMapping(value="/chatRoom_A.do", method = RequestMethod.GET)
	public String chatRoom_A() {
		System.out.println("※※※※※※※※※※※※※※chatRoom_A.do 값은 들어옶니다.※※※※※※※※※※※※");
		return "chat/ChatRoom_A";
	}
	
	
	
	
	// /chatInsert.do
	@RequestMapping(value="/chatConnect.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> chatConnect(HttpSession session) {
		log.info("채팅 리스트 출력을 시작합니다. ");
		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		String user = userD.getUser_seq();
		System.out.println("user 정보 가져오기 : " + user);
		List<DTO_ChatList> lists_chat = service.Chat_List();
		System.out.println("전체 채팅방 목록을 불러옵니다. : " + lists_chat);

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
		System.out.println("채팅방에 접속합니다. 채팅방 번호 : " + chat_seq);
		
		// 채팅방 정보 불러오기
		DTO_ChatRoom dto = service.Chat_Detail(chat_seq);
		System.out.println("채팅방 정보를 불러옵니다.  : " + dto);
		
		// 채팅방에 연결된 유저 찾기
		String user_seq = dto.getUser_seq();
		System.out.println("채팅방에 연결된 유저들을 불러옵니다.  : " + user_seq);
		
		// 채팅방 내역 조회 시작
		String content = "";
		
		// 해당 채팅방의 글 조회하여 없으면 새로운 채팅방 열기
		content = dto.getChatcontent();
		System.out.println("채팅방 내역조회를 종료합니다. 내역 : " + content);
		
		model.addAttribute("user_seq", user_seq);
		model.addAttribute("content", content);
		model.addAttribute("chat_seq", chat_seq);
		return "chat/Broadsocket";
	}
	
	
	// /chatUpdate.do
	@RequestMapping(value="/chatUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> chatUpdate(String messageTextArea_val, String chat_seq) {
		System.out.println("채팅방 내용 업데이트를 시작합니다. : messageTextArea :" + messageTextArea_val + "\n chat_seq : " + chat_seq);
		DTO_ChatRoom RDto = service.Chat_Detail(chat_seq);
		RDto.setChatcontent(messageTextArea_val);
		boolean result = service.Chat_Update(RDto);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", result+"");
		return map;
	}
	
}
