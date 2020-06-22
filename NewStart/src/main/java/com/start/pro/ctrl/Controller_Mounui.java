package com.start.pro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_FAQ;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Mounui;
import com.start.pro.dto.DTO_Paging;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.file.IService_File;
import com.start.pro.models.mounui.IService_Mounui;

@Controller
public class Controller_Mounui {

	@Autowired
	private IService_Mounui service;
	
	@Autowired
	private IService_File file_Service;
	
	@RequestMapping(value = "/Umounuiboard.do", method = RequestMethod.GET)
	public String mounuiboard(Model model){

		List<DTO_FAQ> Fdto = service.getCategory();
		model.addAttribute("Fdto",Fdto);
		
		return "board/mounui/InsertBoard";
	}
	
	//insertBoard
	@RequestMapping(value = "/UinsertBoard.do", method = RequestMethod.POST)
	public String insertBoard(DTO_Mounui dto, HttpSession session,Model model,
			HttpServletResponse resp, @RequestParam(value="file", required = false) List<MultipartFile> report)
					throws IOException{
		
		System.out.println("파일말고 글은 다 들어오나요?"+dto.toString());
		
		System.out.println("파일 몇개 들어왔어??"+report.size());
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		dto.setUser_seq(user.getUser_seq());
		
		if(report.size() > 0) {
			System.out.println("파일있어");
			dto.setFilechk("Y");
			model.addAttribute("file", report);
		}else {
			System.out.println("파일없어");
			dto.setFilechk("N");
		}
		
		System.out.println(dto.toString());
//		System.out.println(user.toString());
		service.insertBoard(dto);
		model.addAttribute("fileboard", "3000");
		model.addAttribute("board_seq", dto.getMounui_seq());


		return "redirect:/UserMBoard.do";
	}

	//유저 문이 게시판
	@RequestMapping(value = "/UserMBoard.do", method = RequestMethod.GET)
	public String UserMBoard(Model model, HttpSession session){
		
		Map<String, String> map = new HashMap<String, String>();
		List<DTO_Mounui> dtos = null;
		DTO_Paging pdto = null;
		
		
		
		if(session.getAttribute("userMounuiBoardrow")==null) {
			pdto = new DTO_Paging();
		}else {
			pdto = (DTO_Paging) session.getAttribute("userMounuiBoardrow");
		}
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		//user_seq : 사용자의 seq
		//start : 시작 글 번호 last : 끝 글 번호
		pdto.setTotal(service.getUserMounuiBoard(user.getUser_seq()));
		map.put("user_seq", user.getUser_seq());
		map.put("start", String.valueOf(pdto.getStart()));
		map.put("last", String.valueOf(pdto.getlast()));
		
		dtos = service.userBoard(map);
		System.out.println(dtos.toString());
		model.addAttribute("dtos", dtos);
		model.addAttribute("userMounuiBoardrow", pdto);
		
		return "board/mounui/UserMBoard";
	}

	// 유저 문의 상세보기
	@RequestMapping(value = "/UserMBoardDetail.do", method = RequestMethod.GET)
	public String UserMBoardDetail(String seq, Model model){
		
		DTO_Mounui dto = service.userBoardDetail(seq);
		
		if(dto.getFilechk().equalsIgnoreCase("Y")) {
			DTO_File fdto = new DTO_File("3000", seq);
			List<DTO_File> fdtolist = file_Service.searchFile2(fdto);
			model.addAttribute("files",fdtolist);
		}
		
		
		System.out.println("왜안돼"+dto.toString());
		
		model.addAttribute("dto", dto);
		return "board/mounui/UserMBoardD";
	}

	
	@RequestMapping(value = "/UserMBoardDel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public void UserMBoardDel(String[] seq, HttpServletResponse resp) throws IOException {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seq", seq);
		service.delBoard(map);
		
//		System.out.println(seq[0]);
		resp.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html; charset=UTF-8");
		PrintWriter	out = resp.getWriter();
		out.println("<script>alert('삭제되었습니다.');location.href='./UserMBoard.do'; </script>");
		out.flush();
		
	}

	// 관리자 문의 게시판
	@RequestMapping(value = "/AdminMBoard.do", method = RequestMethod.GET)
	public String AdminMBoard(Model model, HttpSession session){
		
		List<DTO_Mounui> dtos = null;
		DTO_Paging pdto = null;
		
		if(session.getAttribute("adminMounuiBoardrow")==null) {
			pdto = new DTO_Paging();
		}else {
			pdto = (DTO_Paging) session.getAttribute("adminMounuiBoardrow");
		}
		
		DTO_Filter fdto = new DTO_Filter();
		fdto.setStart(String.valueOf(pdto.getStart()));
		fdto.setLast(String.valueOf(pdto.getlast()));
		pdto.setTotal(service.getAdminMounuiCnt(null));
		dtos = service.adminBoard(fdto);
		System.out.println(dtos.toString());
		List<String> title = service.getTitle();
		model.addAttribute("dtos", dtos);
		model.addAttribute("title", title);
		model.addAttribute("adminMounuiBoardrow", pdto);
		
		
		return "board/mounui/AdminMainBoard";
	}


	
	// 관리자 문의 상세보기
	@RequestMapping(value = "/AdminMBoardDetail.do", method = RequestMethod.GET)
	public String AdminMBoardDetail(String seq, Model model){
		
		DTO_Mounui dto = service.adminBoardDetail(seq);
		
		if(dto.getFilechk().equalsIgnoreCase("Y")) {
			DTO_File fdto = new DTO_File("3000", seq);
			List<DTO_File> fdtolist = file_Service.searchFile2(fdto);
			model.addAttribute("files",fdtolist);
		}
		
		System.out.println("왜안돼"+dto.toString());
		
		if(dto.getReplychk().equalsIgnoreCase("Y")) {
			DTO_Email edto = service.getReply(seq);
			model.addAttribute("edto", edto);
		}
		
		
		
		model.addAttribute("dto", dto);
		return "board/mounui/AdminMBoardD";
	}
	
	
	//문의 게시판 답장하기
	@RequestMapping(value = "/AMBoardReply.do", method = RequestMethod.GET)
	public String MBoardReply(String seq, String email, Model model){
		
		model.addAttribute("seq", seq);
		model.addAttribute("email", email);

		return "board/mounui/EmailReply";
	}


	
	
//	./AdminMBoardDel.do
	//문의 게시판 삭제하기
	@RequestMapping(value = "/AdminMBoardDel.do", method = RequestMethod.GET)
	public void AdminMBoardDel(String[] seq, Model model, HttpServletResponse resp) throws IOException{
		
		Map<String, String[]> map = new HashMap<String,String[]>();
		map.put("seq", seq);
		service.adminDelBoard(map);

		resp.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html; charset=UTF-8");
		PrintWriter	out = resp.getWriter();
		out.println("<script>alert('삭제되었습니다.');location.href='./AdminMBoard.do'; </script>");
		out.flush();
		
		
	}
	
}
