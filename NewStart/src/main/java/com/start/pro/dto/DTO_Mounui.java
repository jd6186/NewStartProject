package com.start.pro.dto;

public class DTO_Mounui {

	private String board_code = "3000";
	private String mounui_seq;
	private String user_seq;
	private String category_seq;
	private String category_title;
	private String title;
	private String content;
	private String regdate;
	private String replychk;
	private String filechk;
	private String delchk;
	
	public DTO_Mounui() {}

	public DTO_Mounui(String board_code, String mounui_seq, String user_seq, String category_seq, String category_title,
			String title, String content, String regdate, String replychk, String filechk, String delchk) {
		super();
		this.board_code = board_code;
		this.mounui_seq = mounui_seq;
		this.user_seq = user_seq;
		this.category_seq = category_seq;
		this.category_title = category_title;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.replychk = replychk;
		this.filechk = filechk;
		this.delchk = delchk;
	}

	public String getBoard_code() {
		return board_code;
	}

	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}

	public String getMounui_seq() {
		return mounui_seq;
	}

	public void setMounui_seq(String mounui_seq) {
		this.mounui_seq = mounui_seq;
	}

	public String getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}

	public String getCategory_seq() {
		return category_seq;
	}

	public void setCategory_seq(String category_seq) {
		this.category_seq = category_seq;
	}

	public String getCategory_title() {
		return category_title;
	}

	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getReplychk() {
		return replychk;
	}

	public void setReplychk(String replychk) {
		this.replychk = replychk;
	}

	public String getFilechk() {
		return filechk;
	}

	public void setFilechk(String filechk) {
		this.filechk = filechk;
	}

	public String getDelchk() {
		return delchk;
	}

	public void setDelchk(String delchk) {
		this.delchk = delchk;
	}



	@Override
	public String toString() {
		return "DTO_Mounui [board_code=" + board_code + ", mounui_seq=" + mounui_seq + ", user_seq=" + user_seq
				+ ", category_seq=" + category_seq + ", category_title=" + category_title + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", replychk=" + replychk + ", filechk=" + filechk
				+ ", delchk=" + delchk + "]";
	}

	
}
