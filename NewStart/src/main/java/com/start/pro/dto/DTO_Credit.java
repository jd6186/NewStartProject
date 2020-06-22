package com.start.pro.dto;

public class DTO_Credit {

	private int credit_seq;
	private int milg_credit;
	private int remain_credit;
	private String credit_date;
	private int user_seq;
	
	public DTO_Credit() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "DTO_Credit [credit_seq=" + credit_seq + ", milg_credit=" + milg_credit + ", remain_credit="
				+ remain_credit + ", credit_date=" + credit_date + ", user_seq=" + user_seq + "]";
	}

	public int getCredit_seq() {
		return credit_seq;
	}
	public void setCredit_seq(int credit_seq) {
		this.credit_seq = credit_seq;
	}
	public int getMilg_credit() {
		return milg_credit;
	}
	public void setMilg_credit(int milg_credit) {
		this.milg_credit = milg_credit;
	}
	public int getRemain_credit() {
		return remain_credit;
	}
	public void setRemain_credit(int remain_credit) {
		this.remain_credit = remain_credit;
	}
	public String getCredit_date() {
		return credit_date;
	}
	public void setCredit_date(String credit_date) {
		this.credit_date = credit_date;
	}
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	public DTO_Credit(int credit_seq, int milg_credit, int remain_credit, String credit_date, int user_seq) {
		super();
		this.credit_seq = credit_seq;
		this.milg_credit = milg_credit;
		this.remain_credit = remain_credit;
		this.credit_date = credit_date;
		this.user_seq = user_seq;
	}
	public DTO_Credit(int milg_credit, int remain_credit, int user_seq) {
		super();
		this.milg_credit = milg_credit;
		this.remain_credit = remain_credit;
		this.user_seq = user_seq;
	}
	
	
	
}
