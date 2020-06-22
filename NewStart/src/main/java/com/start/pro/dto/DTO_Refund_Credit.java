package com.start.pro.dto;

import java.util.Date;

public class DTO_Refund_Credit {

	private String refundcredit_seq  ;
	private Date refundcredit_date ;
	private String credit_seq        ;
	
	public DTO_Refund_Credit() {
		// TODO Auto-generated constructor stub
	}
	public String getRefundcredit_seq() {
		return refundcredit_seq;
	}
	public void setRefundcredit_seq(String refundcredit_seq) {
		this.refundcredit_seq = refundcredit_seq;
	}
	public Date getRefundcredit_date() {
		return refundcredit_date;
	}
	public void setRefundcredit_date(Date refundcredit_date) {
		this.refundcredit_date = refundcredit_date;
	}
	public String getCredit_seq() {
		return credit_seq;
	}
	public void setCredit_seq(String credit_seq) {
		this.credit_seq = credit_seq;
	}
	@Override
	public String toString() {
		return "DTO_Refund_Pay [refundcredit_seq=" + refundcredit_seq + ", refundcredit_date=" + refundcredit_date
				+ ", credit_seq=" + credit_seq + "]";
	}
	public DTO_Refund_Credit(String refundcredit_seq, Date refundcredit_date, String credit_seq) {
		super();
		this.refundcredit_seq = refundcredit_seq;
		this.refundcredit_date = refundcredit_date;
		this.credit_seq = credit_seq;
	}
	
}
