package com.start.pro.ctrl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import com.start.pro.dto.DTO_BWL;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.bwl.IService_BWL;
import com.start.pro.models.file.IService_File;
import com.start.pro.models.file.Service_FileImpl;
import com.start.pro.models.gonggo.IService_Gonggo;
import com.start.pro.models.user.IService_User;

@Controller
public class Controller_Gonggo {

	@Autowired
	IService_Gonggo gonggo;
	
	@Autowired
	IService_User user;
	
	@Autowired
	IService_BWL BWL_service;
	
	@Autowired
	IService_File file_service;
	
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	// /gonggo.do
	@RequestMapping(value="/gonggo.do", method = RequestMethod.GET)
	public String Gonggo(HttpSession session) {
		log.info("gonggo.do 접속 완료");
		return "board/gonggo/GonggoDiv";
	}
	
//////////////////////////////////////////////// 멘티 관련 페이지    ///////////////////////////////////////////////////////////	
	
	
	@RequestMapping(value="/m_main.do", method = RequestMethod.GET)
	public String boardList_M(HttpSession session, Model model, DTO_Criteria cri) {
		log.info("t_main.do 접속 완료");

		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		System.out.println("uDto의 값은? : " + userD);
		
		// 페이징 처리된 리스트
		List<DTO_Gonggo> glists = null;
		List<DTO_File> flists = new ArrayList<DTO_File>();
		Integer listTotal = gonggo.BoardListTotalM();
		
		System.out.println("강사페이지  페이지 정리 끝");
		
		// 1) 1)의 객체를 사용자 List로 가져오는 service!
		glists = gonggo.BoardListRowM(cri);
		System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기" + glists + "리스트의 전체 길이 : " + glists.size());
		
		System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기" + glists);
		System.out.println("getPage : " + cri.getPage());
		System.out.println("getPageStart : " + cri.getPageStart());
		System.out.println("getPerPageNum : " + cri.getPerPageNum());
		System.out.println("getRowEnd : " + cri.getRowEnd());
		System.out.println("getRowStart : " + cri.getRowStart());
		System.out.println("makeQuery : " + cri.makeQuery());

		System.out.println();

		// 4) BWL에서 이미 매칭이 된 공고는 지우고 출력되게 만들기
		List<DTO_BWL> BWL_lists = BWL_service.bwl_show();
		for (int i = 0; i < BWL_lists.size(); i++) {
			if(BWL_lists.get(i).getSuccess_person() != null) {
				String BWL_Seq = BWL_lists.get(i).getGonggo_seq();
				for (int j = 0; j < glists.size(); j++) {
					String gong_seq = glists.get(j).getGonggo_seq();
					if(BWL_Seq.equalsIgnoreCase(gong_seq)) {
						glists.remove(j);
						--listTotal;
						--j;
					}
				}
			}
		}

		System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기2222 : " + glists);
		for (int i = 0; i < glists.size(); i++) {
			DTO_Gonggo gongDto = glists.get(i);
			String seq = gongDto.getGonggo_seq();
			String code = gongDto.getBoard_code();
			try {
				DTO_File fDto = new DTO_File(code, seq);
				DTO_File resultFDto;
				resultFDto = file_service.searchFile(fDto);
				flists.add(resultFDto);
			} catch (Exception e) {
				
			}
		}
		model.addAttribute("listTotal", listTotal);
		System.out.println("listTotal의 값은? " + model.getAttribute("listTotal"));
		
		model.addAttribute("flists", flists);
		System.out.println("flists의 값은? " + model.getAttribute("flists"));

		
		DTO_PageMaker pageMaker = new DTO_PageMaker();
	      pageMaker.setCri(cri);
	      pageMaker.setTotalCount(listTotal);
		    System.out.println("glists.size()의 값은? : " + glists.size());
	      model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("lists", glists);
		model.addAttribute("users", userD);
		System.out.println("getTotalCount의 값은? " + pageMaker.getTotalCount());
		System.out.println("getDisplayPageNum의 값은? " + pageMaker.getDisplayPageNum());
		System.out.println("getEndPage의 값은? " + pageMaker.getEndPage());
		System.out.println("getStartPage의 값은? " + pageMaker.getStartPage());
		System.out.println("getCri의 값은? " + pageMaker.getCri());
		System.out.println("lists의 값은? " + model.getAttribute("lists"));
		System.out.println("users의 값은? " + model.getAttribute("users"));
		
		return "board/gonggo/boardList_M"; // board/gonggo/A_Main
	}
	
	
	
	@RequestMapping(value="/boardInsertM.do", method = RequestMethod.GET)
	public String M_Insert(HttpSession session, Model model) {
		log.info("m_insert.do 접속 완료");
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		String user_seq = user.getUser_seq();
		model.addAttribute("user_seq", user_seq);
		System.out.println("나가는 user_seq값 : " + model.getAttribute("user_seq"));
		return "board/gonggo/M_Insert";
	}
	
	
////////////////////////////////////////////////////// 강사 관련 페이지 ///////////////////////////////////////////////////////	

	@RequestMapping(value="/t_main.do", method = RequestMethod.GET)
	public String boardList_T(HttpSession session, Model model, DTO_Criteria cri) {
		log.info("t_main.do 접속 완료");
		
		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		System.out.println("uDto의 값은? : " + userD);
		// 페이징 처리된 리스트
		List<DTO_Gonggo> glists = null;
		List<DTO_File> flists = new ArrayList<DTO_File>();
		

		Integer listTotal = gonggo.BoardListTotalT();
		
		
		// 1) 1)의 객체를 사용자 List로 가져오는 service!
		glists = gonggo.BoardListRowT(cri);
		
		System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기" + glists + "리스트의 전체 길이 : " + glists.size());
		

		
		// 4) BWL에서 이미 매칭이 된 공고는 지우고 출력되게 만들기
		List<DTO_BWL> BWL_lists = BWL_service.bwl_show();
		for (int i = 0; i < BWL_lists.size(); i++) {
			if(BWL_lists.get(i).getSuccess_person() != null) {
				String BWL_Seq = BWL_lists.get(i).getGonggo_seq();
				for (int j = 0; j < glists.size(); j++) {
					String gong_seq = glists.get(j).getGonggo_seq();
					if(BWL_Seq.equalsIgnoreCase(gong_seq)) {
						glists.remove(j);
						--listTotal;
						--j;
					}
				}
			}
		}


		System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기2222 : " + glists);
		for (int i = 0; i < glists.size(); i++) {
			DTO_Gonggo gongDto = glists.get(i);
			String seq = gongDto.getGonggo_seq();
			String code = gongDto.getBoard_code();
			try {
				DTO_File fDto = new DTO_File(code, seq);
				DTO_File resultFDto;
				resultFDto = file_service.searchFile(fDto);
				flists.add(resultFDto);
			} catch (Exception e) {
				
			}
		}
		
		model.addAttribute("flists", flists);
		System.out.println("flists의 값은? " + model.getAttribute("flists"));

		
		DTO_PageMaker pageMaker = new DTO_PageMaker();
	    pageMaker.setCri(cri);
	    System.out.println("glists.size()의 값은? : " + glists.size());
	    pageMaker.setTotalCount(listTotal);
	    model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("lists", glists);
		model.addAttribute("users", userD);
		System.out.println("pageMaker의 값은? " + pageMaker.getTotalCount());
		System.out.println("lists의 값은? " + model.getAttribute("lists"));
		System.out.println("users의 값은? " + model.getAttribute("users"));
		

		model.addAttribute("listTotal", listTotal);
		System.out.println("listTotal의 값은? " + model.getAttribute("listTotal"));
		
		return "board/gonggo/boardList_T"; // board/gonggo/A_Main
	}
	
	
	@RequestMapping(value="/boardInsertT.do", method = RequestMethod.GET)
	public String T_Insert(HttpSession session, Model model) {
		log.info("t_insert.do 접속 완료");
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		String user_seq = user.getUser_seq();
		model.addAttribute("user_seq", user_seq);
		System.out.println("나가는 user_seq값 : " + model.getAttribute("user_seq"));
		return "board/gonggo/T_Insert";
	}
	
	@RequestMapping(value="/t_modify.do", method = RequestMethod.GET)
	public String T_Modify() {
		log.info("t_modify.do 접속 완료");
		return "board/gonggo/T_Modify";
	}
	
	
	
	
//////////////////////////////////////////////////////// 관리자 관련 페이지  //////////////////////////////////////////////////////	
	
	
	
	@RequestMapping(value="/a_main.do", method = RequestMethod.GET)
	
	public String boardList_A(HttpSession session, Model model, DTO_Criteria cri) {
		log.info("boardList_A.do 접속 완료");
		
		DTO_User userD = (DTO_User) session.getAttribute("newstart");
		System.out.println("uDto의 값은? : " + userD);
		
		// 페이징 처리된 리스트
		List<DTO_Gonggo> glists = null;
		List<DTO_File> flists = new ArrayList<DTO_File>();


		Integer listTotal = gonggo.BoardListTotalA();
		

		if(userD.getUser_grade().equalsIgnoreCase("A")) { //U는 일반유저, A는 관리자
			glists = gonggo.BoardListRowA(cri);
			System.out.println("강사페이지  공고글 전체 불러와 리스트에 담기" + glists + "리스트의 전체 길이 : " + glists.size());
		} 
		
		
		for (int i = 0; i < glists.size(); i++) {
			DTO_Gonggo gongDto = glists.get(i);
			String seq = gongDto.getGonggo_seq();
			String code = gongDto.getBoard_code();
			try {
				DTO_File fDto = new DTO_File(code, seq);
				DTO_File resultFDto;
				resultFDto = file_service.searchFile(fDto);
				flists.add(resultFDto);
			} catch (Exception e) {
				
			}
		}
		
		model.addAttribute("flists", flists);
		System.out.println("flists의 값은? " + model.getAttribute("flists"));
		
		
		DTO_PageMaker pageMaker = new DTO_PageMaker();
	      pageMaker.setCri(cri);
	      pageMaker.setTotalCount(listTotal);
	      model.addAttribute("pageMaker", pageMaker);
		
	      
	      
	      
	      
	      
		// 2) 글들
		model.addAttribute("lists", glists);
		model.addAttribute("users", userD);
		
		System.out.println("pageMaker의 값은? " + model.getAttribute("pageMaker"));
		System.out.println("lists의 값은? " + model.getAttribute("lists"));
		System.out.println("users의 값은? " + model.getAttribute("users"));
		

		model.addAttribute("listTotal", listTotal);
		System.out.println("listTotal의 값은? " + model.getAttribute("listTotal"));
		
		
		return "board/gonggo/boardList_A"; // board/gonggo/A_Main
	}
	
	
	
	
///////////////////////////////////////////////////  글 삭제  /////////////////////////////////////////////////////
	@RequestMapping(value="/delGonggo.do", method = RequestMethod.GET)
	public String del(HttpSession session, String seq) {
		log.info("Welcome del.do : \t{}", seq);
		DTO_Gonggo dto = gonggo.gonggo_detail(seq);
		System.out.println("▥▥▥▥▥▥▥▥값 찾자 dto : " + dto + "▥▥▥▥▥▥▥▥");
		
		DTO_User mDto = (DTO_User) session.getAttribute("newstart");
		System.out.println("▥▥▥▥▥▥▥▥값 찾자 mDto : " + mDto + "▥▥▥▥▥▥▥▥");
			
		boolean isc = false;
		if(mDto.getUser_grade().equalsIgnoreCase("A") || dto.getUser_seq().equalsIgnoreCase(mDto.getUser_seq())) {
			isc = gonggo.gonggo_delete(seq);
		} else {
			System.out.println("값처리가 안됐어");
		}
		
		
		// TODO
		// 나중엔 홈페이지의 메인페이지로 돌아가게 만들면 되겠다!
		return isc ? "redirect:/gonggo.do" : "redirect:/logout.do";			
	}
	
	
	
	
	
//////////////////////////////////////////////////////글 수정하기 //////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modifyGo.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8;")
	@ResponseBody
	public String modifyForm(String gonggo_seq) {
		log.info("Welcome modifyForm : \t{}", gonggo_seq);
		JSONObject json = new JSONObject();

		DTO_Gonggo dto = gonggo.gonggo_detail(gonggo_seq);
		json.put("Gonggo_seq", dto.getGonggo_seq());
		json.put("Gonggo_title", dto.getGonggo_title());
		json.put("Gonggo_content", dto.getGonggo_content());
		json.put("User_seq", dto.getUser_seq());
		json.put("Gonggo_time", dto.getGonggo_time());
		json.put("Fileox", dto.getFileox());
		json.put("Gonggo_cost", dto.getgongo_cost());
		
		log.info("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ json의 결과 " + json.toString() + " ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return json.toString();
	}
	
	
	@RequestMapping(value="/modifyFormGonggo.do", method = RequestMethod.GET)
	public String modifyGo(String gonggo_seq, Model model) {
		DTO_Gonggo dto = gonggo.gonggo_detail(gonggo_seq);
		String title = dto.getGonggo_title();
		String content = dto.getGonggo_content();
		String Gonggo_seq = dto.getGonggo_seq();
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("Gonggo_seq", Gonggo_seq);
		System.out.println("모델 title의 값 : "+model.getAttribute("title"));
		System.out.println("모델 content의 값 : "+model.getAttribute("content"));
		System.out.println("모델 Gonggo_seq의 값 : "+model.getAttribute("Gonggo_seq"));
		return "board/gonggo/T_Modify";			
	}
	
	
	@RequestMapping(value="/modifyGonggo.do", method = RequestMethod.GET)
	public String modify(DTO_Gonggo dto,String title, String content,  String Gonggo_seq) {
		log.info("Welcome modify.do : \t{}", dto);
		dto = new DTO_Gonggo(Gonggo_seq, title, content);
		boolean isc = gonggo.gonggo_modify(dto);
		return "redirect:/gonggo.do";			
	}
	
	
	
	////////////////////////////////////////// 게시글에 파일 업로드 시키기~ ///////////////////////////////////////////
	
	// /upload.do
	@Autowired
	private ServletFileUpload upload;
	@RequestMapping(value="/upload.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject upload(HttpServletRequest request, HttpServletResponse resp) {
		String url = request.getContextPath()+"/img/";
//		String rootPath = request.getRealPath("/img");  => 이거 이제 사라졌다.
		String rootPath = request.getSession().getServletContext().getRealPath("/img");

		File file = new File(rootPath);
		if(!file.exists()) { file.mkdir();	}
		String filename = "";
		int uploaded = 0;
		try {
			List<FileItem> items = upload.parseRequest((HttpServletRequest)request);
			for (FileItem fileItem : items) {
				if(fileItem.isFormField()) {
				}else {
					filename = fileItem.getName();
					File upFile = new File(rootPath+File.separator+filename);
					try {
						fileItem.write(upFile);
						uploaded++;
					} catch (Exception e) {	e.printStackTrace();	}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();	}
			JSONObject obj = new JSONObject();
			obj.put("uploaded", uploaded);
			obj.put("url", url+filename);
			obj.put("fileName", filename);
		return obj;	
	}
	
	////////////////////////////////////////////////// 글 작성하기 & 썸네파일 업로드 ///////////////////////////////////////////////////////////

	private static final String charSet = "utf-8";
	//private static final String dir = "C:/img";
	private static final int fileSize = 1024 * 1024 * 100;
	
	// /boardInsert.do
	@RequestMapping(value="/boardInsert.do", method = RequestMethod.POST)
	 public String boardInsert(DTO_Gonggo dto, HttpServletRequest req, HttpSession session, 
			 @RequestParam("myfile") MultipartFile report) throws IOException {    
		String dir = req.getSession().getServletContext().getRealPath("/img");
		//command객체가 아닌 request로 submit한 값 받아오기     
		if(dto.getGonggo_content().contains("<")) {
			String lt = "&lt";
			char ltset = lt.charAt(0);
			dto.getGonggo_content().replace('<', ltset);
			System.out.println("ltset은???" + ltset);
		} else if(dto.getGonggo_content().contains(">")) {
			String gt = "&gt";
			char gtset = gt.charAt(0);
			dto.getGonggo_content().replace('>', gtset);
			System.out.println("gtset은???" + gtset);
		}
		gonggo.gonggo_insert(dto);
		String fileboard = "2000";
		String board_seq = dto.getGonggo_seq();
		
		////////////////////////////////////////////////////////// 여기서부터는 공통 모듈 /////////////////////////////////////////////////////////////////////////////
		
        //파일명
        String userfile = report.getOriginalFilename();
        //파일명 중 확장자만 추출
        //lastIndexOf(".") - 뒤에 있는 . 의 index번호
        String originalFileExtension = userfile.substring(userfile.lastIndexOf("."));
        
        String realfile = UUID.randomUUID().toString().replaceAll("-", "");
        String separator = File.separator; 
        String extension = userfile.substring(userfile.lastIndexOf("."), userfile.length());
        String fileurl = dir+separator+realfile+originalFileExtension;
        
        //파일을 저장하기 위한 파일 객체 생성
        File file1 = new File(fileurl);
        
        //파일 저장
        report.transferTo(file1);
        
        System.out.println("업로드한 파일은");
        System.out.println(userfile + "은 업로드한 파일이다.");
        System.out.println(realfile + "라는 이름으로 업로드 됐다.");
        System.out.println("파일사이즈는 " + report.getSize());
        DTO_File fileDto = new DTO_File(fileboard, board_seq, realfile+originalFileExtension, userfile, fileurl, extension);
        System.out.println("DTO는 " + fileDto);
        file_service.insertFile(fileDto);
		return "redirect:/gonggo.do";
	}

	
}