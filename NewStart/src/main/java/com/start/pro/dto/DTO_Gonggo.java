package com.start.pro.dto;

public class DTO_Gonggo {
	private String gonggo_seq    ;
	private String board_code    ;
	private String user_seq      ;
	private String gonggo_title  ;
	private String gonggo_content;
	private String bidding       ;
	private String gonggo_time   ;
	private String views         ;
	private String fileox        ;
	private String user_grade    ;
	private String grade_seq;
	private String gongo_cost;
	
	public DTO_Gonggo() {
	}
	
	
	public DTO_Gonggo(String gonggo_seq, String gonggo_title, String gonggo_content) {
		super();
		this.gonggo_seq = gonggo_seq;
		this.gonggo_title = gonggo_title;
		this.gonggo_content = gonggo_content;
	}


	public DTO_Gonggo(String user_seq) {
		super();
		this.user_seq = user_seq;
	}


	public DTO_Gonggo(String gonggo_seq, String board_code, String user_seq, String gonggo_title, String gonggo_content,
			String bidding, String gonggo_time, String views, String fileox) {
		super();
		this.gonggo_seq = gonggo_seq;
		this.board_code = board_code;
		this.user_seq = user_seq;
		this.gonggo_title = gonggo_title;
		this.gonggo_content = gonggo_content;
		this.bidding = bidding;
		this.gonggo_time = gonggo_time;
		this.views = views;
		this.fileox = fileox;
	}





	public String getGonggo_seq() {
		return gonggo_seq;
	}
	public void setGonggo_seq(String gonggo_seq) {
		this.gonggo_seq = gonggo_seq;
	}
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getGonggo_title() {
		return gonggo_title;
	}
	public void setGonggo_title(String gonggo_title) {
		this.gonggo_title = gonggo_title;
	}
	public String getGonggo_content() {
		return gonggo_content;
	}
	public void setGonggo_content(String gonggo_content) {
		this.gonggo_content = gonggo_content;
	}
	public String getBidding() {
		return bidding;
	}
	public void setBidding(String bidding) {
		this.bidding = bidding;
	}
	public String getGonggo_time() {
		return gonggo_time;
	}
	public void setGonggo_time(String gonggo_time) {
		this.gonggo_time = gonggo_time;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String views) {
		this.views = views;
	}
	public String getFileox() {
		return fileox;
	}
	public void setFileox(String fileox) {
		this.fileox = fileox;
	}


	public String getUser_grade() {
		return user_grade;
	}


	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}


	public String getGrade_seq() {
		return grade_seq;
	}


	public void setGrade_seq(String grade_seq) {
		this.grade_seq = grade_seq;
	}


	public String getgongo_cost() {
		return gongo_cost;
	}


	public void setgongo_cost(String gongo_cost) {
		this.gongo_cost = gongo_cost;
	}


	public DTO_Gonggo(String user_seq, String gonggo_title, String gonggo_content, String gongo_cost) {
		super();
		this.user_seq = user_seq;
		this.gonggo_title = gonggo_title;
		this.gonggo_content = gonggo_content;
		this.gongo_cost = gongo_cost;
	}


	public DTO_Gonggo(String gonggo_seq, String board_code, String user_seq, String gonggo_title, String gonggo_content,
			String bidding, String gonggo_time, String views, String fileox, String user_grade, String grade_seq,
			String gongo_cost) {
		super();
		this.gonggo_seq = gonggo_seq;
		this.board_code = board_code;
		this.user_seq = user_seq;
		this.gonggo_title = gonggo_title;
		this.gonggo_content = gonggo_content;
		this.bidding = bidding;
		this.gonggo_time = gonggo_time;
		this.views = views;
		this.fileox = fileox;
		this.user_grade = user_grade;
		this.grade_seq = grade_seq;
		this.gongo_cost = gongo_cost;
	}


	@Override
	public String toString() {
		return "DTO_Gonggo [gonggo_seq=" + gonggo_seq + ", board_code=" + board_code + ", user_seq=" + user_seq
				+ ", gonggo_title=" + gonggo_title + ", gonggo_content=" + gonggo_content + ", bidding=" + bidding
				+ ", gonggo_time=" + gonggo_time + ", views=" + views + ", fileox=" + fileox + ", user_grade="
				+ user_grade + ", grade_seq=" + grade_seq + ", gongo_cost=" + gongo_cost + "]";
	}

	
	
	
}
