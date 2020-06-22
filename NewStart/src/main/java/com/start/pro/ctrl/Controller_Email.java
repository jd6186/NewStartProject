package com.start.pro.ctrl;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Paging;
import com.start.pro.email.AsyncTask_SendEmail;
import com.start.pro.models.email.IService_Email;
import com.start.pro.models.login.IService_Login;
import com.start.pro.util.Util_JSON;

@Controller
public class Controller_Email {

	@Autowired
	private IService_Email service;

	@Autowired
	private IService_Login loginservice;

	@Autowired
	private AsyncTask_SendEmail emailSend;

	@Autowired
	private Util_JSON jsonUtil;

	// 메일 보내기
	@RequestMapping(value = "/Atestmail.do", method = RequestMethod.GET)
	public String test() {

//		emailSend.ddd();
		return "email/ManyMail";
	}

	// 메일 보내기
	@RequestMapping(value = "/ljmail.do", method = RequestMethod.GET)
	public String LJ_Email(String code, String email, HttpServletResponse resp) {
		System.out.println("숑");

		DTO_Email dto = service.SelDetailAuto(code);

		String content = dto.getEmail_content();
		content = content.replace("#{email}", email);
		content = content.replace("#{key}", "1234");
		dto.setEmail_content(content);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_email", dto.getUser_email());
		map.put("lj_key", "1234");
		map.put("lj_code", code);
		service.sendLJ(map);
//		
		emailSend.sendOneMail(dto, resp);

		return "login/SignUp3";
	}

	// 메일 인증
	@RequestMapping(value = "/ljmailchk.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String ljmailchk(String email, String key, Model model) {
		System.out.println(email);
		System.out.println(key);
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_email", email);
		map.put("lj_key", key.trim());
		String code = service.LJKey(map);
		if (code != null) {
			System.out.println("인증되었습니다.");
			switch (code) {
			case "0":
				loginservice.changeNomal(email);
				return "login/SignUpResult";
			case "1":
				model.addAttribute("email", email);
				return "login/FindPW1";
			case "2":
				loginservice.changeSleep(email);
				return "login/HumanSuccess";
			default:
				return "login/LJError";
			}
		} else {
			System.out.println("인증실패");
			return "login/LJError";
		}
	}

	// 자동 이메일 보기
	@RequestMapping(value = "/AutomailB.do", method = RequestMethod.GET)
	public String AutomailB(Model model) {

		List<DTO_Email> dtos = service.SelAuto();
		model.addAttribute("dtos", dtos);

		return "email/AutoEmailBoard";
	}

	// 자동 이메일 상세보기
	@RequestMapping(value = "/AutomailBD.do", method = RequestMethod.GET)
	public String AutomailBDetail(String seq, Model model) {

		DTO_Email dto = service.SelDetailAuto(seq);
		model.addAttribute("dto", dto);

		return "email/AutoEmailBoardD";
	}

	// 자동 이메일 상세보기
	@RequestMapping(value = "/AutomailUp.do", method = RequestMethod.POST)
	public void AutomailUp(DTO_Email dto, HttpServletResponse resp) throws IOException {

		System.out.println("??" + dto.toString());
		service.UpdateAuto(dto);

		resp.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html; charset=UTF-8");

		
		
	    PrintWriter	out = resp.getWriter();
		out.println("<script>alert('수정 완료되었습니다');  location.href='./AutomailB.do'; </script>");
		out.flush();
		
		
	}

	// 대량 메일 보내러 가기
	@RequestMapping(value = "/AmanymailForm.do", method = RequestMethod.GET)
	public String manymailForm() {

		return "email/ManyMail";
	}

	// 대량 메일 정보 받기
	@RequestMapping(value = "/Amanymailget.do", method = RequestMethod.POST)
	public String manymailget(DTO_Email dto) {

		System.out.println(dto.toString());

		return "email/ManyMail";
	}

	// ManyMailSend.do
	@RequestMapping(value = "/AManyMailSend.do", method = RequestMethod.POST)
	public String ManyMailSend(DTO_Email dto, HttpServletResponse resp) {

		System.out.println("받아옴??" + dto.toString());
		//dto.setSuccesschk("S");
		//dto.setCategory_code("1");
		//service.SendEmail(dto);
		System.out.println(dto.toString());
		
		dto.setSuccesschk("S");
		dto.setCategory_code("1");
		service.SendEmail(dto);
		emailSend.sendManyMail(dto);


		return "redirect:/AcheckMailSave.do";
	}

	// 관리자페이지 메일기록 확인
	@RequestMapping(value = "/AcheckMailSave.do", method = RequestMethod.GET)
	public String AcheckMailSave(Model model, HttpSession session) {

//		List<DTO_Email> dtos = service.SelAllMail();
		List<DTO_Email> dtos = null;
		DTO_Paging pdto = null;
		
		if(session.getAttribute("row")==null) {
			pdto = new DTO_Paging();
		}else {
			pdto = (DTO_Paging) session.getAttribute("row");
		}
		
		DTO_Filter fdto = new DTO_Filter();
		fdto.setStart(String.valueOf(pdto.getStart()));
		fdto.setLast(String.valueOf(pdto.getlast()));
		
		System.out.println("왜 1111떠??"+fdto.toString());
		
		pdto.setTotal(service.getEmailCount(null));
		dtos = service.SelAllMail(fdto);
		System.out.println(pdto.toString());
		model.addAttribute("row", pdto);
		model.addAttribute("dtos", dtos);

		return "email/MailSaveBoard";
	}

	// 이메일 기록 상세 보기
	// SelMailDetail.do
	@RequestMapping(value = "/ASelMailDetail.do", method = RequestMethod.GET)
	public String SelMailDetail(Model model, String seq) {

		DTO_Email dto = service.SelMailDetail(seq);
		List<String> emailList = jsonUtil.jsonToList(dto.getUser_email(), "user_email");
		String emails = "";
		for (int i = 0; i < emailList.size(); i++) {
			if (i == emailList.size() - 1) {
				emails += emailList.get(i);
			} else {
				emails += emailList.get(i) + "  /  ";
			}
		}
		dto.setUser_email(emails);

		model.addAttribute("dto", dto);

		return "email/SelMailDetail";
	}
	
	
	///mailSaveDel.do
	@RequestMapping(value = "/AmailSaveDel.do", method = {RequestMethod.POST,RequestMethod.GET})
	public void mailSaveDel(String[] seq, HttpServletResponse resp) throws IOException {

		Map<String,String[]> map = new HashMap<String, String[]>();
		map.put("seq", seq);
		System.out.println(map.toString());
		service.delemailsave(map);
		
		resp.setCharacterEncoding("utf-8");
	    resp.setContentType("text/html; charset=UTF-8");

		
		
	    PrintWriter	out = resp.getWriter();
		out.println("<script>alert('삭제 되었습니다.');  location.href='./AcheckMailSave.do'; </script>");
		out.flush();
		
		
	}

	// resend.do
	// 재전송
	@RequestMapping(value = "/Aresend.do", method = RequestMethod.GET)
	public String resend(Model model, String seq) {

		DTO_Email dto = service.mailresend(seq);
		System.out.println("머가 들었길래 안나와" + dto.toString());
		Map<String, String> map = new HashMap<String, String>();
		map.put("email_seq", dto.getEmail_seq());
		map.put("successchk", "S");
		service.MailSuccess(map);

		emailSend.resend(dto);

		return "redirect:/AcheckMailSave.do";
	}






}
