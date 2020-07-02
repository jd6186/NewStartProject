package com.start.pro.models.gonggo;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.file.IService_File;
import com.start.pro.models.file.Service_FileImpl;

public class inputList {
	private List<DTO_Gonggo> lists;
	private DTO_User users;
	private DTO_PageMaker pageMaker;
	private int listTotal;
	private List<DTO_File> flists;
	
	
	public void setFlists(List<DTO_File> flists) {
		this.flists = flists;
	}
	
	public void setLists(List<DTO_Gonggo> lists) {
		this.lists = lists;
	}

	public void setUsers(DTO_User users) {
		this.users = users;
	}

	public void setPageMaker(DTO_PageMaker pageMaker) {
		this.pageMaker = pageMaker;
	}
	public void setListTotal(int listTotal) {
		this.listTotal = listTotal;
	}

	// 날짜
	private String dateFormat(String date) {
		return date.substring(0, date.indexOf(" "));
	}

	private String titleFormat(String go_seq) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < flists.size(); i++) {
			DTO_File fileDto = flists.get(i);
			try {
				if(fileDto.getFileboard().equalsIgnoreCase("2000")) {
					if(fileDto.getBoard_seq().equalsIgnoreCase(go_seq)) {
						String url = "./img/" + fileDto.getFilename();
						buf.append("<img src=\'" + url + "\' style='width: 100px; height:100px'>");
					}
				}
			} catch (Exception e) {
			}
		}
		
		return buf.toString();
	}

	// 출력 리스트 폼
	private String listForm(DTO_Gonggo dto) {
		StringBuffer buf = new StringBuffer();

		// colspan의 기본 user 종류별로 구분

		int n = 7;
		buf.append("<tr><td colspan='6'><div>");
		buf.append("<input type='hidden' name='Gonggo_seq' id='Gonggo_seq' value='"+dto.getGonggo_seq()+"'>");
		buf.append("<input type='hidden' name='title' id='title' value='"+dto.getGonggo_title()+"'>");
		buf.append("<input type='hidden' name='content' id='content' value='"+dto.getGonggo_content()+"'>");
		buf.append("</div><td></tr>");
		buf.append("<tr>");
		buf.append("<td><input type='checkbox' name='chkVal' value='" + dto.getGonggo_seq() + "'></td>");
		buf.append("<td><a class='' data-toggle='collapse' href='#col"+dto.getGonggo_seq()+"'>" + titleFormat(dto.getGonggo_seq()) + "</a></td>");
		buf.append("<td>");
		buf.append("<div class='panel-heading'>");
		buf.append(""+ dto.getGonggo_title() +"");
		buf.append("</div>");
		buf.append("</td>");
		buf.append("<td>" + dto.getUser_seq() + "</td>");
		buf.append("<td>" + dateFormat(dto.getGonggo_time()) + "</td>");
		n = 7;
		buf.append("<td>" + dto.getFileox() + "</td>");
		buf.append("</tr>");
		buf.append("<tr>");
		buf.append("<td colspan='" + n + "'>");
		buf.append("<div>");
		buf.append("<div class='collapse' id='col"+dto.getGonggo_seq()+"'>");
		buf.append("<label>내용</label>");
		buf.append("<div style='width: 100%; height: 100%;'>" + dto.getGonggo_content()+"</div>");
		buf.append("</div>");
		buf.append("<div>");
		buf.append("<div class ='form-group'>");
		buf.append("<input class='btn btn-primary btn-center' type='button' value='글 삭제' onclick='del(\""
				+ dto.getGonggo_seq() + "\")'>");
		buf.append("</div>");
		buf.append("</div>");
		buf.append("</div>");
		buf.append("</td>");
		buf.append("</tr>");
		return buf.toString();
	}

	// 리스트 가져가기
	public String getListForm() {
		
		StringBuffer buf = new StringBuffer();
		
		for (int i = 0; i < lists.size(); i++) {
			buf.append(listForm(lists.get(i)));
		}
		return buf.toString();
	}

}

