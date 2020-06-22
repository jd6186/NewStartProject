package com.start.pro.models.review;

import java.util.List;

import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_Review;
import com.start.pro.dto.DTO_User;

public class inputList {
	private List<DTO_Review> lists;
	private DTO_User users;
	private DTO_PageMaker pageMaker;
	private int listTotal;
	private List<DTO_File> flists;
	
	
	public void setFlists(List<DTO_File> flists) {
		this.flists = flists;
	}
	
	public void setLists(List<DTO_Review> lists) {
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

	// TODO
	// 관리자의 이미지 미리보기 구현 파트로 사용자들이 등록한 이미지를 디비로부터 찾아와서 연결해주면 되겠다.
	private String titleFormat(String re_seq) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < flists.size(); i++) {
			DTO_File fileDto = flists.get(i);
			try {
				if(fileDto.getFileboard().equalsIgnoreCase("4000")) {
					if(fileDto.getBoard_seq().equalsIgnoreCase(re_seq)) {
						System.out.println("값은 들어와" + fileDto.getFileboard() + fileDto.getBoard_seq() + fileDto.getFilename() + fileDto.getFiletype());
						System.out.println("근데 통과도 하네?? ");
						String url = "./img/" + fileDto.getFilename();
						System.out.println("fUrl은? : " + url);
						buf.append("<img src=\'" + url + "\' style='width: 100px; height:100px'>");
					}
				}
			} catch (Exception e) {
			}
		}
		
		return buf.toString();
	}

	// 출력 리스트 폼
	private String listForm(DTO_Review dto) {
		System.out.println("inputList시작합니다. : \t{}");
		System.out.println("DTO_Gonggo : \t{}" + dto);
		System.out.println("DTO_User : \t{}" + users);
		StringBuffer buf = new StringBuffer();

		// colspan의 기본 user 종류별로 구분

		System.out.println("유저쪽으로 유입 : \t{}" + dto);
		int n = 7;
		buf.append("<tr><td colspan='6'>"
				+ "			<div>");
		buf.append("			<input type='hidden' name='re_seq' id='re_seq' value='"+dto.getRe_seq()+"'>");
		buf.append("			<input type='hidden' name='title' id='title' value='"+dto.getRe_title()+"'>");
		buf.append("			<input type='hidden' name='content' id='content' value='"+dto.getRe_content()+"'>");
		buf.append("		</div><td>"
				+ "</tr>");
		buf.append("<tr>");
		buf.append("	<td><input type='checkbox' name='chkVal' value='" + dto.getRe_seq() + "'></td>");
		buf.append("	<td><a class='' data-toggle='collapse' href='#col"+dto.getRe_seq()+"'>" + titleFormat(dto.getRe_content()) + "</a></td>");
		buf.append("	<td>" + dto.getUser_seq() + "</td>");
		buf.append("	<td>");
		buf.append("		<div class='panel-heading'>");
		buf.append("		"+ dto.getRe_content() +"");
		buf.append("		</div>");
		buf.append("	</td>");
		buf.append("	<td>" + dateFormat(dto.getRe_regdate()) + "</td>");
		n = 7;
		buf.append("	<td>" + dto.getFileox() + "</td>");
		buf.append("</tr>");
		buf.append("<tr>");
		buf.append("	<td colspan='" + n + "'>");
		buf.append("	<div>");
		buf.append("		<div class='collapse' id='col"+dto.getRe_seq()+"'>");
		buf.append("			<label>내용</label>");
		buf.append("			<div style='width: 100%; height: 100%;'>" + dto.getRe_content()+"</div>");
		buf.append("		</div>");
		buf.append("	<div>");
		buf.append("		<div class ='form-group'>");
		buf.append("			<input class='btn btn-primary btn-center' type='button' value='글 삭제' onclick='del(\""
								+ dto.getRe_seq() + "\")'>");
		buf.append("		</div>");
		
		buf.append("	</div>");
		buf.append("</div>");
		buf.append("</td>");
		buf.append("</tr>");
		return buf.toString();
	}

	// 리스트 가져가기
	public String getListForm() {
		
		System.out.println("getListForm으로 들어오긴 합니다.");
		StringBuffer buf = new StringBuffer();
		
		System.out.println("listTotal값은???" + listTotal);
		for (int i = 0; i < lists.size(); i++) {
			System.out.println("나가는 lists의 값은 뭔가요?" + lists);
			buf.append(listForm(lists.get(i)));
		}
		return buf.toString();
	}

}

