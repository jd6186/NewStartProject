package com.start.pro.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.start.pro.dto.DTO_Email;
import com.start.pro.util.Util_JSON;

@Component
public class Email_InputList {

//	@Autowired
//	public Util_JSON jsonUtil;
	
	private List<DTO_Email> lists;

	public void setLists(List<DTO_Email> lists) {
		this.lists = lists;
	}


	// 출력 리스트 폼
	private String listForm(DTO_Email dto, Util_JSON jsonUtil) {
		StringBuffer sb = new StringBuffer();
		//collspan의 기본 user의 값
		sb.append("<tr>");
		sb.append("<td><input type='checkbox' name='seq' value='"+dto.getEmail_seq()+"' onclick='chk(this.name)'></td>");
		sb.append("<td>"+dto.getEmail_seq()+"</td>");
		sb.append("<td>");
		if(dto.getCategory_code().equalsIgnoreCase("1")) {
			sb.append("광고성");
		}
		sb.append("</td>");
		sb.append("<td><a href='./ASelMailDetail.do?seq="+dto.getEmail_seq()+"'>"+dto.getEmail_title()+"</a></td>");
		sb.append("<td>");
		switch (dto.getSuccesschk()) {
		case "S":
			sb.append("대기 중");
			break;
		case "F":
			sb.append("실패");
			break;
		case "Y":
			sb.append("성공");
			break;
		}
		sb.append("</td>");
		sb.append("<td>");
		System.out.println(dto.getUser_email());
		jsonUtil.setStr(dto.getUser_email());
		System.out.println(jsonUtil.getStr());
		
		sb.append(jsonUtil.getStr());
		sb.append("<td>"+dto.getRegdate()+"</td>");
		sb.append("</tr>");
		
		
		return sb.toString();
	}

	public String getListForm() {
		StringBuffer sb = new StringBuffer();
		Util_JSON jsonUtil = new Util_JSON();
		jsonUtil.setKey("user_email");
		System.out.println("부비디바비디부"+lists.size());
		System.out.println("부비디바비디부"+lists.toString());
		for (int i = 0; i < lists.size(); i++) {
			sb.append(listForm(lists.get(i),jsonUtil ));
		}
		return sb.toString();
	}
}
