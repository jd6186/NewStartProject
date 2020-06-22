package com.start.pro.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.dto.DTO_Push;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.push.Greeting;
import com.start.pro.models.push.HelloMessage;
import com.start.pro.models.push.IService_Push;

@Controller
public class Controller_Push {
	
    @Autowired
    private SqlSessionTemplate template;
    
    @Autowired
    private IService_Push push_service;
    
    
    // 푸시알림 send기능 사용하기
    @MessageMapping("/push")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(5000); // simulated delay
        //this.template.convertAndSend("/topic/greetings", "Hello World");
        return new Greeting("안녕하세요 새출발 광고입니다~, " + message.getName() + "!");
    }
    
    
    // 푸시알림 발송 기능이 있는 페이지 접속하기
    @RequestMapping(value="/pushpage.do", method = RequestMethod.GET)
	public String pushInsert(ModelMap model,HttpSession session) {
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		String admin = user.getUser_seq();
		
		model.addAttribute("admin", admin);
    	model.addAttribute("message", "Hello world!");
		return "push/pushInsert"; //pushInsert, pushTest
	}
    
    // 푸시알림 추가하기
    // /pushInsert.do
    @RequestMapping(value="/pushInsert.do", method = RequestMethod.GET)
    @ResponseBody
	public Map<String, String> pushInsert(ModelMap model, DTO_Push dto) {
    	System.out.println("pushInsert쪽으로 접근 시작 : ");
    	System.out.println("pushInsert쪽으로 접근 시작 : " + dto);
    	boolean result = push_service.AlramInsert(dto);
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("result", result+"");
    	return map;
    }
    
    // 푸시알림 보낸 리스트 출력하기
    // /sendList.do
    @RequestMapping(value="/sendList.do", method = RequestMethod.GET)
  	public String sendList(ModelMap model) {
    	System.out.println("sendList 값은 들어옵니다.");
    	List<DTO_Push> lists = push_service.AlramList();
    	model.addAttribute("lists", lists);
    	System.out.println("나가는 lists 값은? : " + model.getAttribute("lists"));
    	return "push/PushList";
    }
    
} 