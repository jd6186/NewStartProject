package com.start.pro.dto;

public class DTO_Refund_Pay {

	private int refund_seq  ;
	private String refund_date ;
	private int pay_seq     ;
	private int user_seq;
	
	public DTO_Refund_Pay() {
		// TODO Auto-generated constructor stub
	}
	public int getRefund_seq() {
		return refund_seq;
	}
	public void setRefund_seq(int refund_seq) {
		this.refund_seq = refund_seq;
	}
	public String getRefund_date() {
		return refund_date;
	}
	public void setRefund_date(String refund_date) {
		this.refund_date = refund_date;
	}
	public int getPay_seq() {
		return pay_seq;
	}
	public void setPay_seq(int pay_seq) {
		this.pay_seq = pay_seq;
	}
	
	
	public int getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}
	
	
	@Override
	public String toString() {
		return "DTO_Refund_Pay [refund_seq=" + refund_seq + ", refund_date=" + refund_date + ", pay_seq=" + pay_seq
				+ ", user_seq=" + user_seq + "]";
	}
	public DTO_Refund_Pay(int refund_seq, String refund_date, int user_seq) {
		super();
		this.refund_seq = refund_seq;
		this.refund_date = refund_date;
		this.user_seq = user_seq;
	}
	public DTO_Refund_Pay(int user_seq, int pay_seq) {
		super();
		this.user_seq = user_seq;
		this.pay_seq = pay_seq;
	}
	
	
	
}
