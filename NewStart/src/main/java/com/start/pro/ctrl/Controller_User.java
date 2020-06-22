package com.start.pro.ctrl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.chrono.Era;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.start.pro.dto.DTO_Declaration;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.dec.IService_Dec;
import com.start.pro.models.file.IService_File;
import com.start.pro.models.review.IService_Review;
import com.start.pro.models.user.IService_User;

@Controller
public class Controller_User {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IService_User service;
	@Autowired
	private IService_File file_service;

	@Autowired
	private IService_Review re_service;
	@Autowired
	private IService_Dec dec_service;

	// ===================이동 컨트롤러=================
	@RequestMapping(value = "/userMain.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String userMain(Model model) {
		log.info("UserMain으로 이동합니다.!!@@@@@@@@@@@@@@@@@@@@@@@@@@");

		// 회원 전체 조회 list
		List<DTO_User> lists = service.searchAll();
		log.info("@@@@@@@@@@@@@@@@회원 전체 조회 페이지@@@@@@@@@@@@@@@@@,{}", lists);

		// 강사 인증 조회
		List<DTO_User> teaLists = service.searchTReqAll();
		log.info("@@@@@@@@@@@@@@@강사 요청 회원 전체 조회 페이지@@@@@@@@@@@@@@@@@,{}", teaLists);

		// 신고 전체 조회
		List<DTO_Declaration> decLists = dec_service.searchDecAll();
		log.info("@@@@@@@@@@@@@@@신고된 회원 전체 조회 페이지@@@@@@@@@@@@@@@@@,{}", decLists);
		//회원 전체 조회
		model.addAttribute("lists", lists);
		//강사 인증 회원 전체 조회
		model.addAttribute("teaLists", teaLists);
		//신고된 회원 전체 조회
		model.addAttribute("decLists", decLists);

		return "board/user/userMain";
	}

	@RequestMapping(value = "/moveDetail.do", method = RequestMethod.GET)
	public String userDetail(HttpServletRequest req, Model model) {
		String user_seq = req.getParameter("user_seq");

		DTO_User dto = service.searchDetail(user_seq);

		model.addAttribute("dto", dto);
		return "board/user/userDetail";
	}

	@RequestMapping(value = "/searchTReq.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchTReq(Model model) {
		log.info("searchTReq으로 이동합니다.!!@@@@@@@@@@@@@@@@@@@@@@@@@@");

		List<DTO_User> lists = service.searchTReqAll();
		log.info("@@@@@@@@@@@@@@@강사 요청 회원 전체 조회 페이지@@@@@@@@@@@@@@@@@,{}", lists);
		model.addAttribute("lists", lists);

		return "board/user/userTReq";
	}

	@RequestMapping(value = "/searchTReqDetail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchTReqDetail(HttpServletRequest req, Model model) {
		log.info("searchTReqDetail으로 이동합니다.!!@@@@@@@@@@@@@@@@@@@@@@@@@@");

		String user_seq = req.getParameter("user_seq");
		DTO_User dto = service.searchTReqDetail(user_seq);
		model.addAttribute("dto", dto);
		return "board/user/userTReqDetail";
	}

	// --------------------마이페이지--------------------------
	// 마이페이지 닉네임, 전화번호 수정
	@RequestMapping(value = "/updateMyPage.do", method = RequestMethod.POST)
	public String updateMyPage(DTO_User dto, HttpSession session) {
		log.info("마이페이지 업데이트! {}", new Date());
		String user_seq = dto.getUser_seq();
		String user_nickname = dto.getUser_nickname();
		String user_phone = dto.getUser_phone();
		String user_adchk = dto.getUser_adchk();
		log.info(user_seq + "    :      " + user_nickname + "   :    " + user_phone + " : " + user_adchk);
		dto = new DTO_User(user_seq, user_nickname, user_phone, user_adchk);
		service.updateMyPage(dto);
		DTO_User newDto = service.searchDetail(user_seq);
		session.setAttribute("newstart", newDto);
		System.out.println("넘어간다!");
		return "redirect:/reviewMain.do";
	}

	// ----------------강사 인증--------------
	@RequestMapping(value = "/teaRespY.do",method =  {RequestMethod.GET,RequestMethod.POST})
	public String teaRespY(String user_seq,Model model) {
		log.info("@@@@@@@@@@@@강사 승인@@@@@@@@@@@@@@{}",new Date());
		service.teaRespY(user_seq);
		return "redirect:/userMain.do";
	}
	@RequestMapping(value = "/teaRespN.do",method =  {RequestMethod.GET,RequestMethod.POST})
	public String teaRespN(String user_seq,Model model) {
		log.info("@@@@@@@@@@@@강사 미승인@@@@@@@@@@@@@@{}",new Date());
		service.teaRespN(user_seq);
		return "redirect:/userMain.do";
	}
	

	////////////////////////// ajax///////////////

//	public String teacherReq() {
//		JSONObject json = new JSONObject();
//		
//		
//	}

	private static final String charSet = "utf-8";
	private static final int fileSize = 1024 * 1024 * 100;

	// 강사 인증 요청하기
	@RequestMapping(value = "/teacherReq.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String teacherReq(HttpSession session, @RequestParam("file") MultipartFile report,HttpServletRequest req) throws IOException {
		log.info("강사 인증 페이지 이동하기! {}", new Date());
		String dir = req.getSession().getServletContext().getRealPath("/img");

		DTO_User dto = (DTO_User) session.getAttribute("newstart");
		String user_seq = dto.getUser_seq();

		String fileboard = "5000";
		////////////////////////////////////////////////////////// 여기서부터는 공통 모듈
		////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////

		// 파일명
		String filerealname = report.getOriginalFilename();

		// 파일명 중 확장자만 추출
		// lastIndexOf(".") - 뒤에 있는 . 의 index번호
		String originalFileExtension = filerealname.substring(filerealname.lastIndexOf("."));

		String realfile = UUID.randomUUID().toString().replaceAll("-", "");
		String separator = File.separator;
		String filename = realfile + originalFileExtension;
		String extension = filerealname.substring(filerealname.lastIndexOf("."), filerealname.length());
		String fileurl = dir + separator + realfile + originalFileExtension;

		// 파일을 저장하기 위한 파일 객체 생성
		File file1 = new File(fileurl);

		// 파일 저장
		report.transferTo(file1);

		System.out.println("업로드한 파일은");
		System.out.println(filerealname + "파일명+확장자 은 업로드한 파일이다.");
		System.out.println(originalFileExtension + "은 업로드한 파일이다.");
		System.out.println(realfile + " realfile 업로드될 파일 이름라는 이름으로 업로드 됐다.");
		System.out.println(filename + " filename 업로드될 파일 이름라는 이름으로 업로드 됐다.");
		System.out.println(fileurl + " fileurl 업로드될 파일 이름라는 이름으로 업로드 됐다.");
		System.out.println("파일사이즈는 " + report.getSize());
		DTO_File fileDto = new DTO_File(fileboard, filename, filerealname, fileurl, user_seq);
		System.out.println("DTO는 " + fileDto);

		file_service.TeacherinsertFile(fileDto);
		service.teacherReq(user_seq);

		DTO_User newDto = service.searchDetail(user_seq);
		session.setAttribute("newstart", newDto);
//		return "redirect:/.do";
		return "board/user/mypage";

	}
	
	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public void download(HttpServletRequest req, HttpServletResponse resp,String user_seq) throws IOException {
		DTO_File fDto = file_service.downFile(user_seq);

		//저장된 파일 이름
		String saveFileName = fDto.getFilename();
		// 원본 파일 이름
		String fileName = fDto.getFilerealname();
		//저장된 상대 경로
		String dir = fDto.getFileurl();
		// 파일명 중 확장자만 추출
		// lastIndexOf(".") - 뒤에 있는 . 의 index번호
		String originalFileExtension = dir.replace(saveFileName, "");
		
		System.out.println("경로!!!  "+dir);
		System.out.println("경로만!!!  "+originalFileExtension);
		System.out.println(user_seq);
		System.out.println("파일 이름 : "+saveFileName);
		System.out.println("파일 이름 : "+fileName);
		
		File file = new File(
				dir);

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		String mimeType = URLConnection.guessContentTypeFromStream(inputStream);

		// 파일의 종류가 없다면 기본으로 설정
		if (mimeType == null) {
			//pdf 파일 전용 application/pdf
			// 텍스트 파일 text/plain
			// 기본 설정 application/octet-stream
			mimeType = "application/pdf";
		}
		
		resp.setContentType(mimeType);
		resp.setContentLength((int) file.length());
		String encordedFilename = URLEncoder.encode(fileName,"UTF-8").replace("+", "%20");
		System.out.println(encordedFilename);
		//헤더에 해당 파일이 첨부 파일임을 명시
		resp.setHeader("Content-Disposition",
		  "attachment;filename=" + encordedFilename + "; filename*= UTF-8''" + encordedFilename);

		log.info(file.getName() + "@@@@@@@@@@@@@@@@@@@@@@@@@@");
		//파일 자체를 웹브라우저에서 읽어들인다. 
		FileCopyUtils.copy(inputStream, resp.getOutputStream());

	}

	////////////////// 신고하기/////////////////
	@RequestMapping(value = "/insertDec.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertDec(String board_seq, String dec_suspect, String dec_victim, Model model) {
		log.info("@@@신고됐다!!{}", new Date());

		log.info(board_seq + " : " + dec_suspect + " : " + dec_victim);
		DTO_Declaration dto = new DTO_Declaration(dec_victim, dec_suspect, board_seq);
		dec_service.insertDec(dto);

		return "redirect:/reviewMain.do";
	}

	/////////////// 경고하기///////////////
	@RequestMapping(value = "/waringDec.do", method = RequestMethod.GET)
	public String waringDec(String dec_seq) {
		log.info("@@@경고했다!!{}번 게시글", dec_seq);

		dec_service.updateDeccnt(dec_seq);

		return "redirect:/userMain.do";
	}

//	@RequestMapping(value = "/download.do",method = {RequestMethod.GET,RequestMethod.POST})
//	public void download(String user_seq,HttpServletResponse response) { // 직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다. 
//		
//		DTO_File fDto = file_service.downFile(user_seq);
//		String filerealname = fDto.getFilerealname();
//		String filename = fDto.getFilename();
//		String filetype = fDto.getFiletype();
//		int fileLength = 123179008; 
//		response.setHeader("Content-Disposition", "attachment; fileName=\"" + filerealname + "\";"); 
//		response.setHeader("Content-Transfer-Encoding", "binary"); 
//		response.setHeader("Content-Type", filetype); 
//		response.setHeader("Content-Length", "" + fileLength); 
//		response.setHeader("Pragma", "no-cache;"); 
//		response.setHeader("Expires", "-1;"); 
//		try(FileInputStream fis = new FileInputStream(filename); 
//			OutputStream out = response.getOutputStream(); ){ 
//				int readCount = 0; 
//				byte[] buffer = new byte[1024]; 
//				while((readCount = fis.read(buffer)) != -1){ 
//					out.write(buffer,0,readCount);
//				} 
//		}catch(Exception ex){ 
//			throw new RuntimeException("file Save Error"); 
//		} 
//		
//	}
	


}
