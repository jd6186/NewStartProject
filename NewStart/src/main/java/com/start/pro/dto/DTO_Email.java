package com.start.pro.dto;

public class DTO_Email {

	
	private String email_seq;
	private String category_code;
	private String user_email;
	private String email_title;
	private String email_content;
	private String filechk;
	private String use_chk;
	private String successchk;
	private String fail_reason;
	private String regdate;
	private String filter;
	
	public DTO_Email() {}

	
	public DTO_Email(String user_email, String email_title, String email_content) {
		super();
		this.user_email = user_email;
		this.email_title = email_title;
		this.email_content = email_content;
	}


	public DTO_Email(String email_seq, String category_code, String user_email, String email_title,
			String email_content, String filechk, String use_chk, String successchk, String fail_reason, String regdate,
			String filter) {
		super();
		this.email_seq = email_seq;
		this.category_code = category_code;
		this.user_email = user_email;
		this.email_title = email_title;
		this.email_content = email_content;
		this.filechk = filechk;
		this.use_chk = use_chk;
		this.successchk = successchk;
		this.fail_reason = fail_reason;
		this.regdate = regdate;
		this.filter = filter;
	}


	public String getEmail_seq() {
		return email_seq;
	}


	public void setEmail_seq(String email_seq) {
		this.email_seq = email_seq;
	}


	public String getCategory_code() {
		return category_code;
	}


	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getEmail_title() {
		return email_title;
	}


	public void setEmail_title(String email_title) {
		this.email_title = email_title;
	}


	public String getEmail_content() {
		return email_content;
	}


	public void setEmail_content(String email_content) {
		this.email_content = email_content;
	}


	public String getFilechk() {
		return filechk;
	}


	public void setFilechk(String filechk) {
		this.filechk = filechk;
	}


	public String getUse_chk() {
		return use_chk;
	}


	public void setUse_chk(String use_chk) {
		this.use_chk = use_chk;
	}


	public String getSuccesschk() {
		return successchk;
	}


	public void setSuccesschk(String successchk) {
		this.successchk = successchk;
	}


	public String getFail_reason() {
		return fail_reason;
	}


	public void setFail_reason(String fail_reason) {
		this.fail_reason = fail_reason;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	public String getFilter() {
		return filter;
	}


	public void setFilter(String filter) {
		this.filter = filter;
	}


	@Override
	public String toString() {
		return "DTO_Email [email_seq=" + email_seq + ", category_code=" + category_code + ", user_email=" + user_email
				+ ", email_title=" + email_title + ", email_content=" + email_content + ", filechk=" + filechk
				+ ", use_chk=" + use_chk + ", successchk=" + successchk + ", fail_reason=" + fail_reason + ", regdate="
				+ regdate + ", filter=" + filter + "]";
	}

	
	
}
