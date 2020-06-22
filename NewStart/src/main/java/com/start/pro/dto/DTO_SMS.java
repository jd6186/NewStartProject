package com.start.pro.dto;

public class DTO_SMS {
	private int sms_seq;
	private int user_seq;
	private String sms_type;
	private String sms_contype;
	private String sms_title;
	private String sms_content;
	private String sms_sendtime;
	private String sms_resultcode;
	private String user_phone;

	public DTO_SMS() {
		// TODO Auto-generated constructor stub
	}

	public DTO_SMS(int user_seq, String sms_resultcode) {
		super();
		this.user_seq = user_seq;
		this.sms_resultcode = sms_resultcode;
	}

	public DTO_SMS(String user_phone, String sms_contype, String sms_title, String sms_content) {
		super();
		this.user_phone = user_phone;
		this.sms_contype = sms_contype;
		this.sms_title = sms_title;
		this.sms_content = sms_content;
	}

	public DTO_SMS(int sms_seq, int user_seq, String sms_type, String sms_contype, String sms_title, String sms_content,
			String sms_sendtime, String sms_resultcode) {
		super();
		this.sms_seq = sms_seq;
		this.user_seq = user_seq;
		this.sms_type = sms_type;
		this.sms_contype = sms_contype;
		this.sms_title = sms_title;
		this.sms_content = sms_content;
		this.sms_sendtime = sms_sendtime;
		this.sms_resultcode = sms_resultcode;
	}

	protected int getSms_seq() {
		return sms_seq;
	}

	protected void setSms_seq(int sms_seq) {
		this.sms_seq = sms_seq;
	}

	protected int getUser_seq() {
		return user_seq;
	}

	protected void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	protected String getSms_type() {
		return sms_type;
	}

	protected void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}

	protected String getSms_contype() {
		return sms_contype;
	}

	protected void setSms_contype(String sms_contype) {
		this.sms_contype = sms_contype;
	}

	protected String getSms_title() {
		return sms_title;
	}

	protected void setSms_title(String sms_title) {
		this.sms_title = sms_title;
	}

	protected String getSms_content() {
		return sms_content;
	}

	protected void setSms_content(String sms_content) {
		this.sms_content = sms_content;
	}

	protected String getSms_sendtime() {
		return sms_sendtime;
	}

	protected void setSms_sendtime(String sms_sendtime) {
		this.sms_sendtime = sms_sendtime;
	}

	protected String getSms_resultcode() {
		return sms_resultcode;
	}

	protected void setSms_resultcode(String sms_resultcode) {
		this.sms_resultcode = sms_resultcode;
	}

	protected String getUser_phone() {
		return user_phone;
	}

	protected void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	@Override
	public String toString() {
		return "DTO_SMS [sms_seq=" + sms_seq + ", user_seq=" + user_seq + ", sms_type=" + sms_type + ", sms_contype="
				+ sms_contype + ", sms_title=" + sms_title + ", sms_content=" + sms_content + ", sms_sendtime="
				+ sms_sendtime + ", sms_resultcode=" + sms_resultcode + ", user_phone=" + user_phone + "]";
	}

	
}
