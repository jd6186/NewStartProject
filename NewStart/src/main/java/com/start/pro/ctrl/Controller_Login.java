package com.start.pro.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.start.pro.captcha.ICaptchaKey;
import com.start.pro.dto.DTO_User;
import com.start.pro.email.AsyncTask_SendEmail;
import com.start.pro.models.login.IService_Login;

@Controller
public class Controller_Login {

	@Autowired
	private IService_Login service;
	

	@Autowired
	private AsyncTask_SendEmail emailSend;

	//키를 받아오는 클래스
	@Resource(name = "getKey")
	private ICaptchaKey getKey;
	
	// 사용자 입력값 판단하는 클래스
	@Resource(name = "valChk")
	private ICaptchaKey valchk;
	

	
	//로그인창 
	@RequestMapping(value = "/loginForm.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String loginForm(
			HttpServletRequest req, Model model) {

		ServletContext app = req.getSession().getServletContext();
		Object cnt = app.getAttribute("failchk");

		if(cnt != null && (Integer)cnt >= 5) {
			String key = getKey.get("0");
			model.addAttribute("key", key);
		}

		return "login/LoginForm_cham";
	}

	
	
	@RequestMapping(value = "/logoutSuccess.do", method = RequestMethod.GET)
	public String logout(@RequestParam(value = "grade", required = false) String grade, HttpSession session) {

		if(grade != null) {
			if(grade.equalsIgnoreCase("H")) {
				emailSend.LJMail("2", ((DTO_User)session.getAttribute("newstart")).getUser_email());
				return "login/Human";
			
			}else {
				return "login/EmailChk";
			}
		}else {
			return "login/LoginForm_cham";
		}
		
	}
	


	//회원가입 완료시
	@RequestMapping(value = "/singUpSc.do", method = RequestMethod.POST)
	public String singUpSc(DTO_User dto) {

		service.signUp(dto);
		
		emailSend.LJMail("0", dto.getUser_email());
		
		return "redirect:/loginForm.do";
	}

	//로그인 완료시
	@RequestMapping(value = "/loginResult.do", method = RequestMethod.GET)
	public String loginResult(HttpSession session, Authentication auth, Model model) {

		DTO_User newstart = (DTO_User) session.getAttribute("user");
		model.addAttribute("dto",newstart);
		
		return "reviewMain";
	}

	//회원가입쪽으로1
	@RequestMapping(value = "/singUpform1.do", method = RequestMethod.GET)
	public String singUpform1() {
	
		return "login/SignUp1_cham";
	}
	
	@RequestMapping(value = "/singUpform.do", method = RequestMethod.GET)
	public String singUpForm(String[] user_adchk,Model model) {
		
		if(user_adchk.length == 2) {
			model.addAttribute("user_adchk","N");
		}else {
			model.addAttribute("user_adchk","Y");
		}
		
		return "login/SignUp2_cham";
	}

	//아이디찾자
	@RequestMapping(value = "/goFId.do", method = RequestMethod.GET)
	public String goFId() {

		return "login/FindId1";
	}
	
	///FindId.do
	@RequestMapping(value = "/FindId.do", method = RequestMethod.GET)
	public String FindId(Model model,String user_phone, HttpServletResponse resp) throws IOException {
		
		String id = service.findId(user_phone);

		if(id != null) {
			model.addAttribute("id", id);
			return "login/FindId2";
		}else {
			resp.setCharacterEncoding("utf-8");
		    resp.setContentType("text/html; charset=UTF-8");
			
		    PrintWriter	out = resp.getWriter();
			out.println("<script>alert('존재하지 않는 정보입니다.');</script>");
			out.flush();
			return "login/FindId1";
		}
		
		
	}

	
	//비밀번호 찾자
	@RequestMapping(value = "/goFPW.do", method = RequestMethod.GET)
	public String goFPW() {
		return "login/FindPW0";
	}

	@RequestMapping(value = "/goFPW1.do", method = RequestMethod.POST)
	public String goFPW1(String email,Model model) {
		
		model.addAttribute("email", email);
		
		emailSend.LJMail("1", email);
		
		return "login/PWLJchk";
	}



	
	@RequestMapping(value = "/ChangePW.do", method = RequestMethod.POST)
	public String ChangePW(String email, String newPW, Model model) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("user_email", email);
		map.put("user_pw", newPW);

		service.updatePW(map);
		
		return "login/LoginForm_cham";
	}
	
	
	// 중복검사
	@RequestMapping(value = "/idChk.do", method = RequestMethod.GET)
	public String idChk() {
		
		return "login/MultiChkId";
	
	}
	
	//./MultiChkId.do

	
	
	// 휴면회원을 일반회원으로 등급변경(이메일 인증시)
	@RequestMapping(value = "/changeN.do", method = RequestMethod.GET)
	public String changeN(String seq) {
	
		service.changeSleep(seq);
		return "login/LoginForm_cham";
	}
	
	// 휴면이나 잠금계정 보내기 EmailChk.do
	@RequestMapping(value = "/EmailChk.do", method = RequestMethod.GET)
	public String EmailChk(String email) {
		
		emailSend.LJMail("2", email);
		
		return "login/EmailChk";
	}
	@RequestMapping(value = "/LEmailChk.do", method = RequestMethod.GET)
	public String LEmailChk() {
		
		
		return "login/EmailChk";
	}
	
	// 잠금계정 풀기(이메일 인증시)
	@RequestMapping(value = "/UnLock.do", method = RequestMethod.GET)
	public String UnLock(String seq) {
		
		service.changeNomal(seq);
		return "login/LoginForm_cham";
	}
	
	
}
