package com.start.pro.intercepter;



import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.start.pro.dto.DTO_File;
import com.start.pro.models.file.IService_File;


public class intercepter extends HandlerInterceptorAdapter {

	@Autowired
	private IService_File file_service;

	private static final String separator = File.separator; 
	private static final String charSet = "utf-8";
	private static final String dir = "C:"+separator+"NewStart"+separator+"img";
	private static final int fileSize = 1024 * 1024 * 100;
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Map<String, Object> map = modelAndView.getModel();
		
		@SuppressWarnings("unchecked")
		List<MultipartFile> report = (List<MultipartFile>)map.get("file");
		String fileboard = (String)map.get("fileboard");
		String board_seq = (String)map.get("board_seq");
		
		if(report != null) {
			
			for (MultipartFile filereport : report) {
				
			
			
			//파일명
			String filename  = filereport.getOriginalFilename();
			
			//파일명 중 확장자만 추출
			//lastIndexOf(".") - 뒤에 있는 . 의 index번호
			//사용자 이름
			String originalFileExtension = filename.substring(filename.lastIndexOf("."));
			
			String realfile = UUID.randomUUID().toString().replaceAll("-", "");
			String fileurl = dir +  separator + realfile+originalFileExtension;
			String extension = filename.substring(filename.lastIndexOf("."), filename.length());
			
			//파일을 저장하기 위한 파일 객체 생성
			File file = new File(fileurl);
			
			//파일 저장
			filereport.transferTo(file);
			
			System.out.println("업로드한 파일은");
			System.out.println(filename + "은 업로드한 파일이다.");
			System.out.println(realfile + "라는 이름으로 업로드 됐다.");
			System.out.println("파일사이즈는 " + filereport.getSize());
			DTO_File fileDto = new DTO_File(fileboard, board_seq, realfile+originalFileExtension, filename, fileurl, extension);
			
			System.out.println("1111왜떠"+fileDto.toString());
			file_service.insertFile(fileDto);
			}
		}else {
			System.out.println("파일업음 뽕");
		}
		
			
	}
	
}
