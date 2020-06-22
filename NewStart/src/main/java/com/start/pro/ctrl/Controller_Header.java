package com.start.pro.ctrl;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.pro.dto.DTO_Career;
import com.start.pro.dto.DTO_Profile;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.profile.IService_Profile;
import com.start.pro.models.user.IService_User;

@Controller
public class Controller_Header {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService_User userService;
	@Autowired
	private IService_Profile proFileService;

	// 마이 페이지로 이동
	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public String mypage(HttpSession session, Model model) {

		log.info("@@@@마이페이지 이동!@@@@");
		DTO_User newstart = (DTO_User) session.getAttribute("newstart");
		DTO_User dto = userService.searchDetail(newstart.getUser_seq());
		model.addAttribute("newstart", dto);
		return "board/user/mypage";
	}

	// 프로필로 이동(프로필 정보 없으면 프로필 작성란으로 이동)
	@RequestMapping(value = "/proFile.do", method = RequestMethod.GET)
	public String searchProfile(HttpSession session, Model model) {
		log.info("@@@@프로필 이동!@@@@");
		DTO_User newstart = (DTO_User) session.getAttribute("newstart");
		DTO_Profile profileDto = null;
		DTO_Career careerDto = null;
		model.addAttribute("newstart", newstart);
//		log.info(newstart.getUser_seq()+" : @@@@프로124213필 이123123동213124!@@@@");
		if (proFileService.searchProfile(newstart.getUser_seq()) != null) {
			profileDto = proFileService.searchProfile(newstart.getUser_seq());
			careerDto = proFileService.searchCareer(newstart.getUser_seq());
			profileDto.setPro_star(proFileService.avgStar(newstart.getUser_seq()));
			
			model.addAttribute("profileDto", profileDto);
			model.addAttribute("careerDto", careerDto);
			return "board/user/profile";
		} else {
			return "board/profile/writeProfile";

		}

	}

///////////////////////스위칭 ////////////////////////

	@RequestMapping(value = "/switch.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateGrade(HttpSession session) {

		DTO_User dto = (DTO_User) session.getAttribute("newstart");
		userService.updateGrade(dto.getUser_seq());
		return "redirect:/reviewMain.do";
	}
}
