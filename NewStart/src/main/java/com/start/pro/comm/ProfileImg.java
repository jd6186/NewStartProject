package com.start.pro.comm;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class ProfileImg {
	/* 절대 경로 */
//	private static String attach_path = "C:\\nobrand\\workspace_spring\\20200511_Spring\\src\\main\\webapp\\resource\\profileImg"; /* 절대 경로 */
	private static String attach_path = "C:\\Users\\IT_LMK\\Desktop\\upload"; /* 절대 경로 */
//	WS에서 관리하는 폴더 혹은 파일 상황에 따라서 변경됨
	//상대 경로
//	private static String attach_path = "C:\\nobrand\\workspace_spring\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\20200511_Spring\\resource\\profileImg"; 

	public static String saveFile(MultipartFile file) {
//		UUID를 통해서 OriginalFIleName <-> StoreFileName(UUID) 생성해서 파일명이 같아도 저장되는 이름은 다르게
		
		String saveName = file.getOriginalFilename();
		
		File f = new File(attach_path, saveName);
		String fileUrl = String.valueOf(f);
		System.out.println("파일의 위치는!!!!"+fileUrl.getClass().getName());
		try {
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveName;
	}
	
}
