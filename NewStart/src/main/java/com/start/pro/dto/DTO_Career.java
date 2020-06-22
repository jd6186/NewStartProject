package com.start.pro.dto;

public class DTO_Career {
	private String user_seq;
	private String career_company;
	private String career_dept;
	private String career_job;
	private String career_term;

	public DTO_Career(String user_seq, String career_company, String career_dept, String career_job, String career_term) {
		super();
		this.user_seq = user_seq;
		this.career_company = career_company;
		this.career_dept = career_dept;
		this.career_job = career_job;
		this.career_term = career_term;
	}

	
	public DTO_Career(String career_company, String career_dept, String career_job, String career_term) {
		super();
		this.career_company = career_company;
		this.career_dept = career_dept;
		this.career_job = career_job;
		this.career_term = career_term;
	}


	public DTO_Career() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}

	public String getCareer_company() {
		return career_company;
	}

	public void setCareer_company(String career_company) {
		this.career_company = career_company;
	}

	public String getCareer_dept() {
		return career_dept;
	}

	public void setCareer_dept(String career_dept) {
		this.career_dept = career_dept;
	}

	public String getCareer_job() {
		return career_job;
	}

	public void setCareer_job(String career_job) {
		this.career_job = career_job;
	}

	public String getCareer_term() {
		return career_term;
	}

	public void setCareer_term(String career_term) {
		this.career_term = career_term;
	}

	@Override
	public String toString() {
		return "DTO_Career [user_seq=" + user_seq + ", career_company=" + career_company + ", career_dept="
				+ career_dept + ", career_job=" + career_job + ", career_term=" + career_term + "]";
	}

}
