package com.start.pro.dto;

public class DTO_Push {
	private String push_seq      ;
	private String admin_seq     ;
	private String push_title    ;
	private String push_content  ;
	private String push_accident ;
	private String push_date     ;
	private String push_am       ;
	private String push_state    ;
	private String push_touser   ;
	
	public DTO_Push() {
		// TODO Auto-generated constructor stub
	}
	
	public DTO_Push(String push_seq, String admin_seq, String push_title, String push_content, String push_accident,
			String push_date, String push_am, String push_state, String push_touser) {
		super();
		this.push_seq = push_seq;
		this.admin_seq = admin_seq;
		this.push_title = push_title;
		this.push_content = push_content;
		this.push_accident = push_accident;
		this.push_date = push_date;
		this.push_am = push_am;
		this.push_state = push_state;
		this.push_touser = push_touser;
	}
	
	
	public String getPush_seq() {
		return push_seq;
	}
	public void setPush_seq(String push_seq) {
		this.push_seq = push_seq;
	}
	public String getAdmin_seq() {
		return admin_seq;
	}
	public void setAdmin_seq(String admin_seq) {
		this.admin_seq = admin_seq;
	}
	public String getPush_title() {
		return push_title;
	}
	public void setPush_title(String push_title) {
		this.push_title = push_title;
	}
	public String getPush_content() {
		return push_content;
	}
	public void setPush_content(String push_content) {
		this.push_content = push_content;
	}
	public String getPush_accident() {
		return push_accident;
	}
	public void setPush_accident(String push_accident) {
		this.push_accident = push_accident;
	}
	public String getPush_date() {
		return push_date;
	}
	public void setPush_date(String push_date) {
		this.push_date = push_date;
	}
	public String getPush_am() {
		return push_am;
	}
	public void setPush_am(String push_am) {
		this.push_am = push_am;
	}
	public String getPush_state() {
		return push_state;
	}
	public void setPush_state(String push_state) {
		this.push_state = push_state;
	}
	public String getPush_touser() {
		return push_touser;
	}
	public void setPush_touser(String push_touser) {
		this.push_touser = push_touser;
	}
	
	@Override
	public String toString() {
		return "DTO_Push [push_seq=" + push_seq + ", admin_seq=" + admin_seq + ", push_title=" + push_title
				+ ", push_content=" + push_content + ", push_accident=" + push_accident + ", push_date=" + push_date
				+ ", push_am=" + push_am + ", push_state=" + push_state + ", push_touser=" + push_touser + "]";
	}
	
	
	
	
}
