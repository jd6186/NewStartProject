package com.start.pro.dto;

public class DTO_Review {

	private int board_code;
	private int re_seq;
	private String user_seq;
	private String re_title;
	private String re_regdate;
	private String re_content;
	private String re_teacher;
	private int re_group;
	private String re_reply;
	private String re_delete;
	private int re_star;
	private String fileox;
	
	public DTO_Review() {
		// TODO Auto-generated constructor stub
	}

	
	public DTO_Review(int board_code, int re_seq, String user_seq, String re_title, String re_regdate, String re_content,
			String re_teacher, int re_group, String re_reply, String re_delete, int re_star) {
		super();
		this.board_code = board_code;
		this.re_seq = re_seq;
		this.user_seq = user_seq;
		this.re_title = re_title;
		this.re_regdate = re_regdate;
		this.re_content = re_content;
		this.re_teacher = re_teacher;
		this.re_group = re_group;
		this.re_reply = re_reply;
		this.re_delete = re_delete;
		this.re_star = re_star;
	}
	
	//게시글 작성
	public DTO_Review(String user_seq,  String re_content, String re_teacher, int re_star,String fileox) {
		super();
		this.user_seq = user_seq;
//		this.re_title = re_title;
		this.re_content = re_content;
		this.re_teacher = re_teacher;
		this.re_star = re_star;
		this.fileox = fileox;
	}

	//게시글 수정
	//답글 작성
	public DTO_Review(int re_seq,  String re_content, int re_star) {
		super();
		this.re_seq = re_seq;
//		this.re_title = re_title;
		this.re_content = re_content;
		this.re_star = re_star;
	}
	
	

	public int getBoard_code() {
		return board_code;
	}

	


	public void setBoard_code(int board_code) {
		this.board_code = board_code;
	}

	public int getRe_seq() {
		return re_seq;
	}

	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}

	public String getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}

	public String getRe_title() {
		return re_title;
	}

	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}

	public String getRe_regdate() {
		return re_regdate;
	}

	public void setRe_regdate(String re_regdate) {
		this.re_regdate = re_regdate;
	}

	public String getRe_content() {
		return re_content;
	}

	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}

	public String getRe_teacher() {
		return re_teacher;
	}

	public void setRe_teacher(String re_teacher) {
		this.re_teacher = re_teacher;
	}

	public int getRe_group() {
		return re_group;
	}

	public void setRe_group(int re_group) {
		this.re_group = re_group;
	}

	public String getRe_reply() {
		return re_reply;
	}

	public void setRe_reply(String re_reply) {
		this.re_reply = re_reply;
	}

	public String getRe_delete() {
		return re_delete;
	}

	public void setRe_delete(String re_delete) {
		this.re_delete = re_delete;
	}

	public int getRe_star() {
		return re_star;
	}

	public void setRe_star(int re_star) {
		this.re_star = re_star;
	}


	public String getFileox() {
		return fileox;
	}


	public void setFileox(String fileox) {
		this.fileox = fileox;
	}


	@Override
	public String toString() {
		return "DTO_Review [board_code=" + board_code + ", re_seq=" + re_seq + ", user_seq=" + user_seq + ", re_title="
				+ re_title + ", re_regdate=" + re_regdate + ", re_content=" + re_content + ", re_teacher=" + re_teacher
				+ ", re_group=" + re_group + ", re_reply=" + re_reply + ", re_delete=" + re_delete + ", re_star="
				+ re_star + ", fileox=" + fileox + "]";
	}
	

	
}
