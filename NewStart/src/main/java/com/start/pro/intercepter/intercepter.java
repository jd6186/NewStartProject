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
	private static final String dir = "C:"+separator+"NewStart"+separator+"img";
	
	
	
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
			
			DTO_File fileDto = new DTO_File(fileboard, board_seq, realfile+originalFileExtension, filename, fileurl, extension);
			
			file_service.insertFile(fileDto);
			}
		}
	}
}
