package com.start.pro.dto;

public class DTO_FAQ {

	private String board_code;
	private String category_seq;
	private String category_sort;
	private String category_title;
	private String faq_content;
	private String faq_regdate;
	private String faq_seq;
	private String faq_title;
	private String filechk;

	public DTO_FAQ() {}

	public String getBoard_code() {
		return board_code;
	}

	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}

	public String getCategory_seq() {
		return category_seq;
	}

	public void setCategory_seq(String category_seq) {
		this.category_seq = category_seq;
	}

	public String getCategory_sort() {
		return category_sort;
	}

	public void setCategory_sort(String category_sort) {
		this.category_sort = category_sort;
	}

	public String getCategory_title() {
		return category_title;
	}

	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}

	public String getFaq_content() {
		return faq_content;
	}

	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}

	public String getFaq_regdate() {
		return faq_regdate;
	}

	public void setFaq_regdate(String faq_regdate) {
		this.faq_regdate = faq_regdate;
	}

	public String getFaq_seq() {
		return faq_seq;
	}

	public void setFaq_seq(String faq_seq) {
		this.faq_seq = faq_seq;
	}

	public String getFaq_title() {
		return faq_title;
	}

	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}

	public String getFilechk() {
		return filechk;
	}

	public void setFilechk(String filechk) {
		this.filechk = filechk;
	}

	@Override
	public String toString() {
		return "DTO_FAQ [board_code=" + board_code + ", category_seq=" + category_seq + ", category_sort="
				+ category_sort + ", category_title=" + category_title + ", faq_content=" + faq_content
				+ ", faq_regdate=" + faq_regdate + ", faq_seq=" + faq_seq + ", faq_title=" + faq_title + ", filechk="
				+ filechk + "]";
	}
	
	
}
