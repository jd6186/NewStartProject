package com.start.pro.models.gonggo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.file.IService_File;

public class inputListM {
	
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

	
	// TODO
	// 이미지 미리보기 구현 파트로 파일 업로드 구현이 완료되면 사용자가 선택한 사진이 여기에 자동으로 어펜드되게 만들어주면 되겠다.
	private String titleFormat(String go_seq) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < flists.size(); i++) {
			DTO_File fileDto = flists.get(i);
			try {
				if(fileDto.getFileboard().equalsIgnoreCase("2000")) {
					if(fileDto.getBoard_seq().equalsIgnoreCase(go_seq)) {
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
	private String listForm(DTO_Gonggo dto) {
		
		System.out.println("inputList시작합니다. : \t{}");
		System.out.println("DTO_Gonggo : \t{}" + dto);
		System.out.println("DTO_User : \t{}" + users);
		StringBuffer buf = new StringBuffer();
		
		// 공고글을 작성한 작성자 등급 불러오기
		String user_seqs = dto.getUser_seq();
		System.out.println(user_seqs);
		
		
		
		// colspan의 기본 user 종류별로 구분
			System.out.println("유저쪽으로 유입 : \t{}" + dto);
			int n = 7;
			
			// TODO
			// 나중에는 newstart에서 유저 정보 빼내가지고 users.getUser_seq대신에 접속한  유저의 seq를 넣어주어야도니다.
			// session.getAttribute("newstart");
			System.out.println("users.getUser_seq() : " + users.getUser_seq() + " dto.getUser_seq() : " + dto.getUser_seq());
			
			System.out.println(users.getUser_seq().equalsIgnoreCase(dto.getUser_seq()) && dto.getFileox().equalsIgnoreCase("Y"));
			if(dto.getFileox().equalsIgnoreCase("Y")) {
				
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
				buf.append("<div class ='form-group' name='biddingList' id='biddingList"+dto.getGonggo_seq()+"'>");
				if(users.getUser_seq().equalsIgnoreCase(dto.getUser_seq())) {
					buf.append("<input id='biddingOX"+dto.getGonggo_seq()+"' name='biddingOX"+dto.getGonggo_seq()+"' class='btn btn-primary btn-center' type='button' value='입찰 내역보기' onclick='biddingList(\""+dto.getGonggo_seq()+"\")'>");
				}
				if(users.getUser_seq().equalsIgnoreCase(dto.getUser_seq())) {
			  		buf.append("<input class='btn btn-primary btn-center' type='button' value='글 수정' onclick='modify(\""+dto.getGonggo_seq()+"\")'>");
			  	}
				if(users.getUser_seq().equalsIgnoreCase(dto.getUser_seq())) {
					buf.append("<input class='btn btn-primary btn-center' type='button' value='글 삭제' onclick='del(\""
							+ dto.getGonggo_seq() + "\")'>");
				}
				buf.append("<br/>");

				if(users.getUser_grade().equalsIgnoreCase("T")) {
					buf.append("<input class='btn btn-primary btn-center' name='bidding_btn"+dto.getGonggo_seq()+"' type='button' value='입찰하기' onclick='bidding(\""
							+ dto.getGonggo_seq() + "\")'>");
				}
				
				buf.append("</div>");
				buf.append("</div>");
				buf.append("</div>");
				buf.append("</td>");
				buf.append("</tr>");
				
				return buf.toString();
			} else {
				return "실패";
			}
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
