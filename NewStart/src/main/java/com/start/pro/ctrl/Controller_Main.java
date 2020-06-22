package com.start.pro.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.start.pro.dto.DTO_File;
import com.start.pro.models.file.IService_File;

@Controller
public class Controller_Main {

	@Autowired
	private IService_File file_Service;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String mainPage() {
		logger.info("main.do");
		
		return "index";
	}

	@RequestMapping(value = "/access_denied_page.do", method = RequestMethod.GET)
	public String denied() {
		logger.info("denied.do");
		System.out.println("안들어와??");
		return "access_denied";
	}

	
	@RequestMapping(value = "/fileDownload.do", method = RequestMethod.GET)
	public void fileDownload(String seq, HttpServletRequest req ,HttpServletResponse resp) throws Exception {
		
		
		DTO_File fdto = file_Service.getDown(seq);
		File file = new File(fdto.getFileurl());
		
		resp.setContentType("application/octet-stream");
		resp.setContentLength((int)file.length());
		
	         
	        String fileName = null;
	         
	        if(req.getHeader("user-agent").indexOf("MSIE") == -1) {
				// 인코딩을 UTF-8로 인코딩하겠다 라는 뜻.
	        	fileName = new String(fdto.getFilerealname().getBytes("UTF-8"), "8859_1");
			}else{
				fileName = new String(fdto.getFilerealname().getBytes("EUC-KR"), "8859_1");
			}


	        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	         
	        resp.setHeader("Content-Transfer-Encoding", "binary");

	        OutputStream out = resp.getOutputStream();
	         
	        FileInputStream fis = null;

	        try {
	             
	            fis = new FileInputStream(file);
	             
	            FileCopyUtils.copy(fis, out);
	             
	             
	        } catch(Exception e){
	             
	            e.printStackTrace();
	             
	        }finally{
	             
	            if(fis != null){
	                 
	                try{
	                    fis.close();
	                }catch(Exception e){}
	            }
	             
	        }// try end;
	         
	        out.flush();

	        
	        
		
	}
	
}
