package com.start.pro.dto;

public class DTO_User {

	private String user_seq;
	private String user_email;
	private String user_nickname;
	private String user_name;
	private String user_grade;
	private String user_type;
	private String user_pw;
	private String user_phone;
	private String user_adchk;
	private String user_regdate;
	private String user_tchk;
	private String user_treq;
	private String user_pchk;
	
	//강사 인증 시간
	private String tea_date;
	private String tea_handate;
	
	
	public DTO_User() {}

	public DTO_User(String user_email, String user_nickname, String user_name, String user_pw, String user_phone,
			String user_adchk) {
		super();
		this.user_email = user_email;
		this.user_nickname = user_nickname;
		this.user_name = user_name;
		this.user_pw = user_pw;
		this.user_phone = user_phone;
		this.user_adchk = user_adchk;
	}

	
	public DTO_User(String user_seq,String user_nickname, String user_phone) {
		super();
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.user_phone = user_phone;
	}

	public DTO_User(String user_seq, String user_nickname, String user_phone, String user_adchk) {
		super();
		this.user_seq = user_seq;
		this.user_nickname = user_nickname;
		this.user_phone = user_phone;
		this.user_adchk = user_adchk;
	}
	public String getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_adchk() {
		return user_adchk;
	}

	public void setUser_adchk(String user_adchk) {
		this.user_adchk = user_adchk;
	}

	public String getUser_regdate() {
		return user_regdate;
	}

	public void setUser_regdate(String user_regdate) {
		this.user_regdate = user_regdate;
	}

	public String getUser_tchk() {
		return user_tchk;
	}

	public void setUser_tchk(String user_tchk) {
		this.user_tchk = user_tchk;
	}

	
	public String getUser_treq() {
		return user_treq;
	}

	public void setUser_treq(String user_treq) {
		this.user_treq = user_treq;
	}
	
	//-----------강사 인증 시간

	public String getTea_date() {
		return tea_date;
	}

	public void setTea_date(String tea_date) {
		this.tea_date = tea_date;
	}

	public String getTea_handate() {
		return tea_handate;
	}

	public void setTea_handate(String tea_handate) {
		this.tea_handate = tea_handate;
	}
	
	//-----------결제 처음하는제 체크
	public String getUser_pchk() {
		return user_pchk;
	}

	public void setUser_pchk(String user_pchk) {
		this.user_pchk = user_pchk;
	}

	@Override
	public String toString() {
		return "DTO_User [user_seq=" + user_seq + ", user_email=" + user_email + ", user_nickname=" + user_nickname
				+ ", user_name=" + user_name + ", user_grade=" + user_grade + ", user_type=" + user_type
				+ ", user_phone=" + user_phone + ", user_adchk=" + user_adchk + ", user_regdate=" + user_regdate
				+ ", user_tchk=" + user_tchk + ", user_treq=" + user_treq + ", user_pchk=" + user_pchk + "]";
	}

	
	
	
}
