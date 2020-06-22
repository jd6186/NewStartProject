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

import com.start.pro.dto.DTO_BWL;
import com.start.pro.dto.DTO_Bidding;
import com.start.pro.dto.DTO_ChatList;
import com.start.pro.dto.DTO_ChatRoom;
import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.bidding.IService_Bidding;
import com.start.pro.models.bwl.IService_BWL;
import com.start.pro.models.chat.IService_Chat;
import com.start.pro.models.gonggo.IService_Gonggo;


@Controller
public class Controller_Bidding {

	@Autowired
	private IService_Bidding service;

	@Autowired
	private IService_BWL service_BWL;
	
	@Autowired
	private IService_Gonggo gonggo;
	
	@Autowired
	private IService_Chat chat_service;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	// /biddingPage.do
	@RequestMapping(value="/biddingPage.do", method = RequestMethod.GET)
	public String biddingPage(Model model, String gonggo_seq, HttpSession session) {
		log.info("※※※※※※※※※※※※※※ 입찰을 시작합니다. ※※※※※※※※※※※※");
		DTO_User this_user = (DTO_User) session.getAttribute("newstart");
		String user_seq = this_user.getUser_seq();
		model.addAttribute("gonggo_seq", gonggo_seq);
		model.addAttribute("user_seq", user_seq);
		System.out.println("gonggo_seq값은? : " + model.getAttribute("gonggo_seq"));
		System.out.println("user_seq값은? : " + model.getAttribute("user_seq"));
		return "board/gonggo/BiddingPage";
	}
	
	// /biddingInsert.do
	@RequestMapping(value="/biddingInsert.do", method = RequestMethod.POST)
	public String biddingInsert(Model model, DTO_Bidding dto, HttpSession session, String gonggo_seq) {
		log.info("※※※※※※※※※※※※※※ 입찰 등록을 시작합니다. ※※※※※※※※※※※※ ");
		log.info("dto, seq : \t {} {}", dto, gonggo_seq);
		
		boolean insert_R = service.bidding_insert(dto);
		System.out.println("insert_R결과입니다. : " + insert_R);
		boolean insert_U = service.bidding_update(gonggo_seq);
		System.out.println("insert_U결과입니다. : " + insert_U);
		return "redirect:/gonggo.do";
	}	
	
	// /biddingList.do
	@RequestMapping(value="/getBiddingList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBiddingList(String gonggo_seq) {
		log.info("※※※※※※※※※※※※※※ 입찰 리스트 출력을 시작합니다. ※※※※※※※※※※※※ ");
		log.info("dto, seq : \t {} {}", gonggo_seq);
		Map<String, String> map = new HashMap<String, String>();
		map.put("gonggo_seq", gonggo_seq);
		List<DTO_Bidding> lists = service.bidding_list(map);
		System.out.println("bidding_list결과입니다. : " + lists);
		
		Map<String, Object> map_L = new HashMap<String, Object>();
		map_L.put("lists", lists);
		System.out.println("여기서 map가지고 나갑니다~ 뿌뿌~ : " + map_L.get("lists"));
		return map_L;
	}	
	
	
	

	// /BWL_insert.do
	@RequestMapping(value="/bwl_insert.do", method = RequestMethod.GET)
	public String bwl_insert(HttpSession session, String gonggo_seq) {
		System.out.println("입찰자 확정 단계에 접어들었습니다. 확정 글번호 : " + gonggo_seq);
		String success_person = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("gonggo_seq", gonggo_seq);
		List<DTO_Bidding> B_lists = service.bidding_list(map);
		System.out.println("bidding_list판별합니다. B_lists : " + B_lists);
		for(int i = 0; i < B_lists.size(); i++) {
			if(B_lists.get(i).getGonggo_seq().equalsIgnoreCase(gonggo_seq)) {
				success_person = B_lists.get(i).getBiddinger();
			}
		}
		System.out.println("success_person판별합니다. success_person : " + success_person);
		DTO_BWL dto = new DTO_BWL(success_person, gonggo_seq);
		boolean insert_U = service_BWL.bwl_winner(dto);
		System.out.println("service_BWL.bwl_winner판별합니다. insert_U : " + insert_U);
		System.out.println("insert_U결과입니다. : " + insert_U);
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		String user_seq = user.getUser_seq();
		
		// 채팅리스트를 받아올건데 json형태로 저장할 것이기 때문에 이런식으로 검색을 해보면 된다.
		List<DTO_ChatList> chatList = chat_service.Chat_List();
		if(chatList.size() > 0) {
			for (int i = 0; i < chatList.size(); i++) {
				// 혹시 chatList에 이미 올라간 채팅방 리스트인지 확인하기 (상대방이 먼저 나를 등록했을 수 있으므로 반대편도 확인한다.)
				if(chatList.get(i).getUser_seq().equalsIgnoreCase(("{"+user_seq+":"+success_person+"}")) || chatList.get(i).getUser_seq().equalsIgnoreCase(("{"+success_person+":"+user_seq+"}"))) {
					System.out.println("chatList를 검색해본 결과 이미 한번 열린적이 있는 채팅리스트이자 채팅방입니다.");
				} else {
					
					// 처음 열리는 채팅방이므로 채팅리스트와 채팅방을 동시에 등록해준다.
					String chatUser_seq = "{"+user_seq+":"+success_person+"}";
					DTO_ChatList dto_Chk1 = new DTO_ChatList(chatUser_seq);
					boolean dto_chatR = chat_service.Chat_Open(dto_Chk1);
					System.out.println("채팅리스트 등록 성공 여부 조회입니다. : " + dto_chatR);
					if(dto_chatR) {
						if(chatList.get(i).getUser_seq().equalsIgnoreCase(chatUser_seq)) {
							System.out.println("chatUser_seq 통과했습니다. : " + chatUser_seq);
							String chat_seq = chatList.get(i).getChat_seq();
							DTO_ChatRoom resultR = new DTO_ChatRoom(chat_seq, chatUser_seq);
							boolean result = chat_service.Chat_Content(resultR);
							if(result) { 
								return "redirect:/gonggo.do";
							}
						}
					}
				}
			}
		} else {
			System.out.println("채팅방이 하나도 존재하지 않아서 여기로 접소옥~");
			String chatUser_seq = "{"+user_seq+":"+success_person+"}";
			DTO_ChatList dto_Chk1 = new DTO_ChatList(chatUser_seq);
			boolean dto_chatR = chat_service.Chat_Open(dto_Chk1);
			System.out.println("채팅리스트 등록 성공 여부 조회입니다. : " + dto_chatR);
			List<DTO_ChatList> c_list = chat_service.Chat_List();
			System.out.println("채팅리스트에 첫번째로 등록된 리스트의 seq를 받아봐봐 : " + c_list.get(0));
			String chat_seq = c_list.get(0).getChat_seq();
			System.out.println("등록된 리스트의 chat_seq : " + chat_seq);
			DTO_ChatRoom resultR = new DTO_ChatRoom(chat_seq, chatUser_seq);
			boolean result = chat_service.Chat_Content(resultR);
			System.out.println(result);
		}
		
		
		return "redirect:/gonggo.do";
	}
	
	// ./getBWLList.do
	@RequestMapping(value="/getBWLList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> getBWLList(String gonggo_seq) {
		log.info("※※※※※※※※※※※※※※ BWL판별을 시작합니다. ※※※※※※※※※※※※ ");
		log.info("dto, seq : \t {} {}", gonggo_seq);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		List<DTO_BWL> lists = service_BWL.bwl_show();
		System.out.println("bwl_show결과입니다. : " + lists);
		boolean bwl_B = false;
		for (int i = 0; i < lists.size(); i++) {
			if(lists.get(i).getSuccess_person() != null) {
				bwl_B = true;
			}
		}
		if(bwl_B) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		System.out.println("여기서 map가지고 나갑니다~ 뿌뿌~ : " + map.get("result"));
		return map;
	}	
	
}
