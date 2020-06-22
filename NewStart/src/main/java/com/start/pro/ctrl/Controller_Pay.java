package com.start.pro.ctrl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Pay;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.gonggo.IService_Gonggo;
import com.start.pro.models.pay.IService_Pay;
import com.start.pro.models.user.IService_User;

@Controller
public class Controller_Pay {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IService_Pay service;
	
	@Autowired
	private IService_User us;
	
	@Autowired
	private IService_Gonggo gonggo;
	
	@RequestMapping(value = "/pay.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String payPage(HttpSession session) {
		logger.info("pay.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return "pay/pay";
	}
	
	@RequestMapping(value = "/exit.do")
	public String exit() {
		logger.info("exit.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return "redirect:/pay.do";
	}
	
	@ResponseBody
	@RequestMapping(value = "/payment.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String payPage(Model model, HttpSession session, String selCash) {
		logger.info("payment.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println(selCash);
		
		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		
		int a = service.selectMax();
		int orderNo = a + 1;
		
		try {
			
			url = new URL("https://pay.toss.im/api/v2/payments");
			connection = url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			JSONObject jsonBody = new JSONObject();
			jsonBody.put("orderNo", orderNo);
			jsonBody.put("amount", selCash);
			jsonBody.put("amountTaxFree", 0);
			jsonBody.put("productDesc", "테스트 결제");
			jsonBody.put("autoExecute", true);
			jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");
			jsonBody.put("resultCallback", "http://localhost:8093/NewStart/callback.do");
		    jsonBody.put("retUrl", "http://localhost:8093/NewStart/main.do?orderno=" + orderNo);
		    jsonBody.put("retCancelUrl", "http://localhost:8093/NewStart/exit.do");

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			
		    bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			
		    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}
		System.out.println(responseBody.toString()+"응답받은거");
		String s = responseBody.toString();
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jsonresult = (JSONObject) obj;
		String token = (String) jsonresult.get("payToken");
		String checkoutPage = (String) jsonresult.get("checkoutPage");
		
		System.out.println("잘담기나요??"+token);
		System.out.println("잘담기나요??"+checkoutPage);
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		DTO_Pay dto = new DTO_Pay(0, token, orderNo, selCash, null, user.getUser_seq(), "Y");
		
		service.createPay(dto);
		
		model.addAttribute("payed", checkoutPage);
		session.setAttribute("token", token);
		session.setAttribute("selCash", selCash);
		model.addAttribute("user", user);
		
		String u = user.getUser_seq();
		int cnt = Integer.parseInt(u);
		
		DTO_Credit dtoo = null;
		int sel = Integer.parseInt(selCash);
		
		System.out.println("&&&&&&&&&&&&&&&7"+user.getUser_pchk());
		
		if (sel == 10000) {
			int credit = 10;
			if (user.getUser_pchk().equalsIgnoreCase("Y")) {
				int cre = service.updateCredit(cnt);
				dtoo = new DTO_Credit(credit, cre + credit, cnt);
			} else {	
				service.payChk(cnt);
				dtoo = new DTO_Credit(credit, credit, cnt);
			}
			service.createCredit(dtoo);
		} else if (sel == 50000) {
			int credit = 50;
			if (user.getUser_pchk().equalsIgnoreCase("Y")) {
				int cre = service.updateCredit(cnt);
				dtoo = new DTO_Credit(credit, cre + credit, cnt);
			} else {	
				service.payChk(cnt);
				dtoo = new DTO_Credit(credit, credit, cnt);
			}
			service.createCredit(dtoo);
		} else if (sel == 100000) {
			int credit = 100;
			if (user.getUser_pchk().equalsIgnoreCase("Y")) {
				int cre = service.updateCredit(cnt);
				dtoo = new DTO_Credit(credit, cre + credit, cnt);
			} else {	
				service.payChk(cnt);
				dtoo = new DTO_Credit(credit, credit, cnt);
			}
			service.createCredit(dtoo);
		} else if (sel == 200000) {
			int credit = 200;
			if (user.getUser_pchk().equalsIgnoreCase("Y")) {
				int cre = service.updateCredit(cnt);
				dtoo = new DTO_Credit(credit, cre + credit, cnt);
			} else {
				service.payChk(cnt);
				dtoo = new DTO_Credit(credit, credit, cnt);
			}
			service.createCredit(dtoo);	
		} else {
			int credit = 500;
			if (user.getUser_pchk().equalsIgnoreCase("Y")) {
				int cre = service.updateCredit(cnt);
				dtoo = new DTO_Credit(credit, cre + credit, cnt);
			} else {	
				service.payChk(cnt);
				dtoo = new DTO_Credit(credit, credit, cnt);
			}
			service.createCredit(dtoo);
		}
		
		
		return checkoutPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/cancel.do", method = RequestMethod.POST)
	public String cancel(HttpSession session, HttpServletRequest req, String seqq) {
		logger.info("cancel.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		String isc = "false";
		
		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		
		int o = Integer.parseInt(seqq);
		System.out.println(o + "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		DTO_Pay one = service.selectOnePay(o);
		System.out.println(one + "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		String token = one.getPay_token();

		System.out.println(token + "나 환불 토큰이야 이것드라");
		
		String amount = one.getPay_amount();
		System.out.println(amount + "가격");
		try {
			url = new URL("https://pay.toss.im/api/v2/refunds");
			connection = url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			org.json.simple.JSONObject jsonBody = new JSONObject();
			jsonBody.put("payToken", token);
			jsonBody.put("amount", amount);
			jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			
		    bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			
		    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&"+responseBody.toString());
		String s = responseBody.toString();
		
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonresult = (JSONObject) obj;
		long code = (Long)jsonresult.get("code");
		System.out.println("&&&&**********&&&&&&&&&"+code);
		
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&"+seqq);
		int re = Integer.parseInt(seqq);	// pay_seq
		System.out.println(re);
		
		String c = one.getUser_seq();
		int cnt = Integer.parseInt(c);
		
		DTO_Refund_Pay ref = new DTO_Refund_Pay(cnt, re);
//		String ss = req.getParameter("seq");
//		System.out.println(ss.getClass().getName());
//		int seq = Integer.parseInt(ss);
//		System.out.println(seq);
//		service.updateRef(seq);
		
		int money = Integer.parseInt(amount);
		
		int cre = service.updateCredit(cnt);
		
		if (code == 0) {
			if (money == 10000) {
				int credit = 10;
				DTO_Credit dtoo = new DTO_Credit(credit, cre - credit, cnt);
				service.createCredit(dtoo);
			} else if (money == 50000) {
				int credit = 50;
				DTO_Credit dtoo = new DTO_Credit(credit, cre - credit, cnt);
				service.createCredit(dtoo);
			} else if (money == 100000) {
				int credit = 100;
				DTO_Credit dtoo = new DTO_Credit(credit, cre - credit, cnt);
				service.createCredit(dtoo);
			} else if (money == 200000) {
				int credit = 200;
				DTO_Credit dtoo = new DTO_Credit(credit, cre - credit, cnt);
				service.createCredit(dtoo);
			} else {
				int credit = 500;
				DTO_Credit dtoo = new DTO_Credit(credit, cre - credit, cnt);
				service.createCredit(dtoo);
			}
			service.refundPay(ref);
			service.updateRef(re);
		} 
		
		return isc;
	}
	
	@RequestMapping(value = "/payList.do", method = RequestMethod.GET)
	public String payList(Model model, DTO_Criteria cri, HttpSession session) {
		logger.info("payList.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"+all);
		String u = user.getUser_seq();
		String g = user.getUser_grade();
		int cnt = Integer.parseInt(u);
		
		int first = cri.getRowStart();
		int end = cri.getRowEnd();
		
		List<DTO_Pay> lists = null;
//		List<DTO_Refund_Pay> listss = null;
		
		// 결제내역 검색
		if (g.equalsIgnoreCase("A")) {
			lists = service.selectAdPay(first, end);
		} else {
			lists = service.selectPay(first, end, u);
		}
		
		// 환불내역 검색
//		if (g.equalsIgnoreCase("A")) {
//			listss = service.selectAdRef(first, end);
//		} else {
//			listss = service.selectRef(first, end, u);
//		}
		 
		// 결제페이징
		if (g.equalsIgnoreCase("A")) {
			DTO_PageMaker pageMaker = new DTO_PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.listAdCount());
			model.addAttribute("pageMaker", pageMaker);
		} else {
			DTO_PageMaker pageMaker = new DTO_PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.listCount(cnt));
			model.addAttribute("pageMaker", pageMaker);
		}
		
		// 환불페이징
//		if (g.equalsIgnoreCase("A")) {
//			DTO_PageMaker pageMaker1 = new DTO_PageMaker();
//			pageMaker1.setCri(cri);
//			pageMaker1.setTotalCount(service.refListAdCount());
//			model.addAttribute("pageMaker1", pageMaker1);
//		} else {
//			DTO_PageMaker pageMaker1 = new DTO_PageMaker();
//			pageMaker1.setCri(cri);
//			pageMaker1.setTotalCount(service.refListCount(cnt));
//			model.addAttribute("pageMaker1", pageMaker1);
//		}
		
		model.addAttribute("lists", lists);
//		model.addAttribute("listss", listss);
		model.addAttribute("user", user);
		
		return "pay/payList";
	}
	@RequestMapping(value = "/refList.do", method = RequestMethod.GET)
	public String refList(Model model, DTO_Criteria cri, HttpSession session) {
		logger.info("refList.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"+all);
		String u = user.getUser_seq();
		String g = user.getUser_grade();
		int cnt = Integer.parseInt(u);
		
		int first = cri.getRowStart();
		int end = cri.getRowEnd();
		
		List<DTO_Refund_Pay> listss = null;
		
		
		// 환불내역 검색
		if (g.equalsIgnoreCase("A")) {
			listss = service.selectAdRef(first, end);
		} else {
			listss = service.selectRef(first, end, u);
		}
		
		
		// 환불페이징
		if (g.equalsIgnoreCase("A")) {
			DTO_PageMaker pageMaker1 = new DTO_PageMaker();
			pageMaker1.setCri(cri);
			pageMaker1.setTotalCount(service.refListAdCount());
			model.addAttribute("pageMaker1", pageMaker1);
		} else {
			DTO_PageMaker pageMaker1 = new DTO_PageMaker();
			pageMaker1.setCri(cri);
			pageMaker1.setTotalCount(service.refListCount(cnt));
			model.addAttribute("pageMaker1", pageMaker1);
		}
		
		model.addAttribute("listss", listss);
		model.addAttribute("user", user);
		
		return "pay/refund";
	}

	
	@ResponseBody
	@RequestMapping(value = "/listChange.do", method = RequestMethod.POST)
	public String pList(String sel) {
		logger.info("listChange.do {}", new Date());
		String isc = "false";
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		if(sel.trim().equals("cash")) {
			isc = "true";
		} 
		return isc;
	}
	
	@RequestMapping(value = "/credit.do", method = RequestMethod.GET)
	public String creditList(Model model, HttpSession session, DTO_Criteria cri) {
		logger.info("credit.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		String u = user.getUser_seq();
		int cnt = Integer.parseInt(u);
		String g = user.getUser_grade();
		
		int first = cri.getRowStart();
		int end = cri.getRowEnd();
		
		List<DTO_Credit> lists = null;
		// 결제내역 검색
		if (g.equalsIgnoreCase("A")) {
			lists = service.selectAdCredit(first, end);
		} else {
			lists = service.selectCredit(first, end, u);
		}
		
		DTO_PageMaker pageMaker = new DTO_PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.creListCount(cnt));
		
		model.addAttribute("lists", lists);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("user", user);
		
		return "pay/credit";
	}
	
	@RequestMapping(value = "/userCredit.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean userCredit(HttpSession session, String credit) {
		
		DTO_User dto = (DTO_User) session.getAttribute("newstart");
		String u = dto.getUser_seq();
		int use = Integer.parseInt(u);
		
		// credit int 로 바꾸기
		int cred = Integer.parseInt(credit);
		
		// 최신 크레딧 검색
		int re = service.updateCredit(use);

		
		
		// 인서트 해주기
		if (re > cred) {
	         DTO_Credit c = new DTO_Credit(cred, re - cred, use);
	         service.createCredit(c);
	    	 return true;
	    } else if (re == cred) {
	    	 DTO_Credit c = new DTO_Credit(cred, 0, use);
	    	 service.createCredit(c);
	    	 return true;
		} else {
			System.out.println("에러다 시봉세야 " + re);
	    	 return false;
		}
		
	}
	
}
