package com.start.pro.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_Review;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.file.IService_File;
import com.start.pro.models.review.IService_Review;

@Controller
public class Controller_Review {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService_Review service;
	@Autowired
	private IService_File file_Service;
	


	// 후기 전체 페이지 조회
	@RequestMapping(value = "/reviewMain.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchAll(Model model, HttpSession session,DTO_Criteria cri) {
		log.info("@@@@@@@@@후기 전체 페이지@@@@@@@@@@@@@@@@2");
		
		DTO_PageMaker pageMaker = new DTO_PageMaker();
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(service.listCount());
	      
		
		List<DTO_Review> lists = service.searchAll(cri);
		DTO_User newstart = (DTO_User) session.getAttribute("newstart");

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("lists", lists);
		model.addAttribute("newstart", newstart);

//		 System.out.println("getPage : " + cri.getPage());
//	      System.out.println("getPageStart : " + cri.getPageStart());
//	      System.out.println("getPerPageNum : " + cri.getPerPageNum());
//	      System.out.println("getRowEnd : " + cri.getRowEnd());
//	      System.out.println("getRowStart : " + cri.getRowStart());
//	      System.out.println("makeQuery : " + cri.makeQuery());
//	      System.out.println("getEndPage : " + pageMaker.getEndPage());
//	      System.out.println("getStartPage : " + pageMaker.getStartPage());
//	      System.out.println("getTotalCount : " + pageMaker.getTotalCount());
//	      System.out.println("getTotalCount : " + pageMaker.getTotalCount());
		return "board/review/reviewMain";
	}

	// 후기 상세 페이지 조회
	@RequestMapping(value = "/reviewDetail.do", method = RequestMethod.GET)
	public String searchDetail(Model model, int re_seq, HttpSession session) {
		log.info("@@@@@@@@@후기 상세 페이지@@@@@@@@@@@@@@@@2");
//		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
		DTO_User newstart = (DTO_User) session.getAttribute("newstart");
		DTO_Review dto = service.searchDetail(re_seq);
		model.addAttribute("dto", dto);
		model.addAttribute("newstart", newstart);
		log.info("들어왔니");
		return "board/review/reviewDetail";
	}

	// 후기 게시글 작성 페이지 이동
	@RequestMapping(value = "/writeReview.do", method = RequestMethod.GET)
	public String writeReview(HttpSession session, Model model) {
		log.info("@@@@@@@@@@@@@@@@후기 작성 페이지 이동@@@@@@@@@@@@@");
		DTO_User newstart = (DTO_User) session.getAttribute("newstart");

		model.addAttribute("newstart", newstart);
		return "board/review/writeReview";
	}

	private static final String charSet = "utf-8";
	private static final int fileSize = 1024 * 1024 * 100;
	
	// 후기 게시글 작성
	@RequestMapping(value = "/insertReview.do", method = RequestMethod.POST)
	public String insertReview(DTO_Review dto, HttpServletRequest req, HttpSession session, 
	          @RequestParam("reviewFile") MultipartFile report) throws IOException {
		String dir = req.getSession().getServletContext().getRealPath("/img");
		log.info("@@@@@@@@@@@@@@@@후기 작성 @@@@@@@@@@@@@,{}", new Date());
		log.info("파일존재 여부 :" + report);
		log.info("파일이름 :" + report.getOriginalFilename());
		log.info("파일크기 : " + report.getSize());
		log.info("컨텐트 타입 : " + report.getContentType());
		log.info("별점좀 구해주세요"+req.getParameter("selectBox"));
		String user_seq = dto.getUser_seq();
//		String re_title = req.getParameter("re_title");
		String re_content = dto.getRe_content();
		String re_teacher = dto.getRe_teacher();
		int re_star = dto.getRe_star();
      //command객체가 아닌 request로 submit한 값 받아오기     
	
		DTO_Review reDto = null;
		String fileboard = "4000";
		String board_seq = "0";
		String fileurl = "파일 없음";
		////////////////////////////////////////////////////////// 여기서부터는 공통 모듈 /////////////////////////////////////////////////////////////////////////////
		if (report.getSize() != 0) {
	        //파일명
	        String filerealname = report.getOriginalFilename();
	        
	        //파일명 중 확장자만 추출
	        //lastIndexOf(".") - 뒤에 있는 . 의 index번호
	        String originalFileExtension = filerealname.substring(filerealname.lastIndexOf("."));
	        
	        String realfile = UUID.randomUUID().toString().replaceAll("-", "");
	        String separator = File.separator; 
	        String filename = realfile+originalFileExtension;

	        fileurl = dir+separator+realfile+originalFileExtension;
	        //파일을 저장하기 위한 파일 객체 생성
	        File file1 = new File(fileurl);
	        
	        //파일 저장
	        report.transferTo(file1);
	        
	        System.out.println(filerealname + "  filerealname은 업로드한 파일이다.");
	        System.out.println(realfile + "  realfile은 업로드한 파일이다.");
	        System.out.println(filename + "  filename은 업로드한 파일이다.new DTO_File(");
	        System.out.println(fileurl + "  fileurl 은 업로드한 파일이다.");
	        System.out.println("파일사이즈는 " + report.getSize());
	        DTO_File fileDto = new DTO_File(fileboard, board_seq, filename,filerealname , fileurl, user_seq,re_teacher);
	        System.out.println("DTO는 " + fileDto);
	        
	        reDto = new DTO_Review(user_seq, re_content, re_teacher, re_star, filename);
	        service.insertReview(reDto);
	        file_Service.ReviewinsertFile(fileDto);
		}else {
			reDto = new DTO_Review(user_seq, re_content, re_teacher, re_star, fileurl);
			service.insertReview(reDto);
		}
		System.out.println("완료");
		return "redirect:/reviewMain.do";
	}

//	public void insertBoard(HttpServletRequest request) throws Exception {
//		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//		MultipartFile multipartFile = null;
//		while (iterator.hasNext()) {
//			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
//			if (multipartFile.isEmpty() == false) {
//				log.debug("------------- file start -------------");
//				log.debug("name : " + multipartFile.getName());
//				log.debug("filename : " + multipartFile.getOriginalFilename());
//				log.debug("size : " + multipartFile.getSize());
//				log.debug("-------------- file end --------------\n");
//			}
//		}
//	}

	// 후기 수정 페이지 이동
	@RequestMapping(value = "/moveModify.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String moveModify(HttpSession session, Model model, HttpServletRequest req) {
		log.info("@@@@@@@@@@@@@@@@후기 수정 페이지 이동@@@@@@@@@@@@@");
		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
		DTO_Review dto = service.searchDetail(re_seq);

		model.addAttribute("dto", dto);

		return "board/review/reviewModify";
	}

	// 후기 수정
	@RequestMapping(value = "/modifyReview.do", method = RequestMethod.GET)
	public String modifyReview(HttpServletRequest req) {
		log.info("@@@@@@@@@@@@@@@@후기 작성 @@@@@@@@@@@@@,{}", new Date());
		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
//		String re_title = req.getParameter("re_title");
		String re_content = req.getParameter("re_content");
		int re_star = Integer.parseInt(req.getParameter("re_star"));

		DTO_Review dto = new DTO_Review(re_seq,re_content, re_star);

		service.updateReview(dto);
		return "redirect:/reviewMain.do";
	}

	// 답글 작성페이지 이동
	@RequestMapping(value = "/moveReply.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeReply(Model model, HttpSession session, HttpServletRequest req) {
		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
		DTO_User newstart = (DTO_User) session.getAttribute("newstart");
		DTO_Review uDto = service.searchDetail(re_seq);

		model.addAttribute("uDto", uDto);
		model.addAttribute("newstart", newstart);
		return "board/review/writeReply";
	}

	// 답글 작성
	@RequestMapping(value = "/insertReply.do", method = RequestMethod.GET)
	public String insertReply(HttpServletRequest req) {

		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
//		String re_title = req.getParameter("re_title");
		String re_content = req.getParameter("re_content");
		int re_star = Integer.parseInt(req.getParameter("re_star"));

		DTO_Review dto = new DTO_Review(re_seq,  re_content, re_star);

		service.replyInsert(re_seq, dto);

		return "redirect:/reviewMain.do";
	}

	// 게시글 삭제
	@RequestMapping(value = "/reviewDelete.do", method = RequestMethod.GET)
	public String reviewDelete(HttpServletRequest req) {
		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
		log.info(re_seq + "@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		service.delReview(re_seq);
		return "redirect:/reviewMain.do";
	}
	

//	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
//	public void download(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		String saveFileName = req.getParameter("saveFileName");
//		System.out.println("파일 이름 : "+saveFileName);
//		File file = new File(
//				"C:\\nobrand\\workspace_spring\\fileSpring\\src\\main\\webapp\\resources\\" + saveFileName);
//
//		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//		String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
//
//		// 파일의 종류가 없다면 기본으로 설정
//		if (mimeType == null) {
//			mimeType = "application/octec-stream";
//		}
//		resp.setContentType(mimeType);
//		resp.setContentLength((int) file.length());
//		//헤더에 해당 파일이 첨부 파일임을 명시
//		resp.setHeader("Content-Disposition", String.format("attachment; fileName=%s", saveFileName));
//		log.info(file.getName() + "@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		//파일 자체를 웹브라우저에서 읽어들인다. 
//		FileCopyUtils.copy(inputStream, resp.getOutputStream());
//
//	}
	
//	// 파일명 랜덤생성 메서드
//		private String uploadFile(String originalName, byte[] fileData) throws Exception {
//			// uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
//			UUID uuid = UUID.randomUUID();
//			// 랜덤생성+파일이름 저장
//			String savedName = uuid.toString() + "_" + originalName;
//			File target = new File(uploadPath, savedName);
//			// 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
//			// FileCopyUtils.copy(바이트배열, 파일객체)
//			FileCopyUtils.copy(fileData, target);
//			return savedName;
//		}
}
