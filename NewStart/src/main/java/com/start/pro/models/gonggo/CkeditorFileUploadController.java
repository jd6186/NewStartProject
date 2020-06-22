package com.start.pro.models.gonggo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;

@Controller
@RequestMapping("/adm")
public class CkeditorFileUploadController {

	@RequestMapping(value="/fileupload.do", method=RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletRequest req, HttpServletResponse resp, 
                 MultipartHttpServletRequest multiFile) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		MultipartFile file = multiFile.getFile("upload");
		if(file != null){
			System.out.println("업로드 어디까지 해봤니? : file != null 통과");
			if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())){
				System.out.println("업로드 어디까지 해봤니? : file.getSize() > 0 && StringUtils.isNotBlank(file.getName())통과");
				if(file.getContentType().toLowerCase().startsWith("image/")){
					System.out.println("file.getContentType().toLowerCase().startsWith(\"image/\")의 값은 뭐가 찍히냐??? " + file.getContentType().toLowerCase().startsWith("image/"));
					try{
						String fileName = file.getOriginalFilename();
						byte[] fileB = file.getBytes();
						if(fileB.length < 500000) {
							
						System.out.println("fileName????" + fileName);
				        String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						byte[] bytes = file.getBytes();
						String uploadPath = req.getSession().getServletContext().getRealPath("/img");
						File uploadFile = new File(uploadPath);
						if(!uploadFile.exists()){
							uploadFile.mkdirs();
						}
						fileName = UUID.randomUUID().toString();
						System.out.println("어디까지 전개되는지 보자 0");
						uploadPath = uploadPath + "/" + fileName + extension;
						System.out.println("어디까지 전개되는지 보자 0_1");
						out = new FileOutputStream(new File(uploadPath));
						System.out.println("어디까지 전개되는지 보자 0_2");
                        out.write(bytes);
                        System.out.println("어디까지 전개되는지 보자 1");
                        printWriter = resp.getWriter();
                        resp.setContentType("text/html");
                        String fileUrl = req.getContextPath() + "/img/" + fileName + extension;
                        
                        System.out.println("어디까지 전개되는지 보자 2");
                        // json 데이터로 등록
                        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
                        // 이런 형태로 리턴이 나가야함.
                        
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);
                        System.out.println("어디까지 전개되는지 보자 3");
                        
                        printWriter.println(json);
                        System.out.println("어디까지 전개되는지 보자 4");
						}
                        
                    }catch(IOException e){
                    	System.out.println("파일의 크기가 너무 큰 것 같은데?");
                        e.printStackTrace();
                    }finally{
                        if(out != null){
                            out.close();
                        }
                        if(printWriter != null){
                            printWriter.close();
                        }		
					}
				}
			}
		}
		System.out.println("json에 저장된 형태 : " + json.toString());
		return null;
	}	
	
	
}