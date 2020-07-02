package com.start.pro.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Mounui;
import com.start.pro.dto.DTO_Paging;
import com.start.pro.dto.DTO_User;
import com.start.pro.email.AsyncTask_SendEmail;
import com.start.pro.models.email.IService_Email;
import com.start.pro.models.mounui.IService_Mounui;
import com.start.pro.util.Util_JSON;

@RestController
public class RestController_Mounui {

	@Autowired
	private IService_Mounui service;
	
	@Autowired
	private IService_Email mservice;

	@Autowired
	private AsyncTask_SendEmail emailSender;
	
	@Autowired
	private Util_JSON jsonUtil;
	
	//문의 게시판 답장 메일 보내기
	@RequestMapping(value = "/AReplySend.do", method = RequestMethod.POST)
	public boolean MReplySend(DTO_Mounui dto, String user_email) throws IOException{
		
		if(emailSender.sendReplyMail(user_email, dto.getTitle(), dto.getContent())) {
			dto.setDelchk("Y");
			mservice.SendReply(dto);
			service.replyMounui(dto.getMounui_seq());
			return true;
			
		}else {
			return false;
		}
	}
	
	//관리자 문의게시판 필터
	@RequestMapping(value = "/AdminMBoard.do", method = RequestMethod.POST)
	public List<DTO_Mounui> AdminMBoard(DTO_Filter dto){
		
			List<DTO_Mounui> dtos = service.adminBoard(dto);
		
		return dtos;
	}

	
	@RequestMapping(value = "/AdminMBoardPaging.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8;")
	public String AdminMBoardPaging(DTO_Filter fdto, DTO_Paging dto,  HttpSession session){
		
		JSONObject json = null;
		dto.setTotal(service.getAdminMounuiCnt(fdto));
		fdto.setStart(String.valueOf(dto.getStart()));
		fdto.setLast(String.valueOf(dto.getlast()));
		json = jsonUtil.MounuimakeJson(service.adminBoard(fdto), dto);
		session.removeAttribute("adminMounuiBoardrow");
		session.setAttribute("adminMounuiBoardrow", dto);
		
		return json.toString();
	}
	
	
	@RequestMapping(value = "/UserMBoardPaging.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8;")
	public String UserMBoardPaging(DTO_Paging dto,  HttpSession session){
		
		JSONObject json = null;
		Map<String, String> map = new HashMap<String, String>();
		DTO_User user = (DTO_User) session.getAttribute("newstart");

		dto.setTotal(service.getUserMounuiBoard(user.getUser_seq()));
		map.put("user_seq", user.getUser_seq());
		map.put("start", String.valueOf(dto.getStart()));
		map.put("last", String.valueOf(dto.getlast()));
		json = jsonUtil.UserMounuimakeJson(service.userBoard(map), dto);
		session.removeAttribute("userMounuiBoardrow");
		session.setAttribute("userMounuiBoardrow", dto);
		
		return json.toString();
	}
}
