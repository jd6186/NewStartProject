package com.start.pro.dto;

public class DTO_Profile {
	private String user_seq;
	private String pro_school;
	private String pro_major;
	private String pro_tech;
	private String pro_info;
	private String pro_star;

	public DTO_Profile(String user_seq, String pro_school, String pro_major, String pro_tech, String pro_info) {
		super();
		this.user_seq = user_seq;
		this.pro_school = pro_school;
		this.pro_major = pro_major;
		this.pro_tech = pro_tech;
		this.pro_info = pro_info;
	}
	

	public DTO_Profile(String pro_school, String pro_major, String pro_tech, String pro_info) {
		super();
		this.pro_school = pro_school;
		this.pro_major = pro_major;
		this.pro_tech = pro_tech;
		this.pro_info = pro_info;
	}


	public DTO_Profile() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}

	public String getPro_school() {
		return pro_school;
	}

	public void setPro_school(String pro_school) {
		this.pro_school = pro_school;
	}

	public String getPro_major() {
		return pro_major;
	}

	public void setPro_major(String pro_major) {
		this.pro_major = pro_major;
	}

	public String getPro_tech() {
		return pro_tech;
	}

	public void setPro_tech(String pro_tech) {
		this.pro_tech = pro_tech;
	}

	public String getPro_info() {
		return pro_info;
	}

	public void setPro_info(String pro_info) {
		this.pro_info = pro_info;
	}

	public String getPro_star() {
		return pro_star;
	}

	public void setPro_star(String pro_star) {
		this.pro_star = pro_star;
	}

	@Override
	public String toString() {
		return "DTO_Profile [user_seq=" + user_seq + ", pro_school=" + pro_school + ", pro_major=" + pro_major
				+ ", pro_tech=" + pro_tech + ", pro_info=" + pro_info + ", pro_star=" + pro_star + "]";
	}

}
