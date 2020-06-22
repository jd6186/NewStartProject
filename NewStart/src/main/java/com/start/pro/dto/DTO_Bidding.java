package com.start.pro.dto;

public class DTO_Bidding {
	private String bidding_seq;
	private String gonggo_seq;
	private String board_code;
	private String biddinger;
	private String biddinger_w;
	private String bidding_cost;
	
	
	
	
	
	public DTO_Bidding(String bidding_seq, String gonggo_seq, String board_code, String biddinger, String biddinger_w,
			String bidding_cost) {
		super();
		this.bidding_seq = bidding_seq;
		this.gonggo_seq = gonggo_seq;
		this.board_code = board_code;
		this.biddinger = biddinger;
		this.biddinger_w = biddinger_w;
		this.bidding_cost = bidding_cost;
	}







	public DTO_Bidding(String gonggo_seq, String biddinger, String biddinger_w, String bidding_cost) {
		super();
		this.gonggo_seq = gonggo_seq;
		this.biddinger = biddinger;
		this.biddinger_w = biddinger_w;
		this.bidding_cost = bidding_cost;
	}







	public DTO_Bidding(String gonggo_seq) {
		super();
		this.gonggo_seq = gonggo_seq;
	}

	public DTO_Bidding() {
		// TODO Auto-generated constructor stub
	}





	public String getBidding_seq() {
		return bidding_seq;
	}
	public void setBidding_seq(String bidding_seq) {
		this.bidding_seq = bidding_seq;
	}
	public String getGonggo_seq() {
		return gonggo_seq;
	}
	public void setGonggo_seq(String gonggo_seq) {
		this.gonggo_seq = gonggo_seq;
	}
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getBiddinger() {
		return biddinger;
	}
	public void setBiddinger(String biddinger) {
		this.biddinger = biddinger;
	}
	public String getBiddinger_w() {
		return biddinger_w;
	}
	public void setBiddinger_w(String biddinger_w) {
		this.biddinger_w = biddinger_w;
	}

	public String getBidding_cost() {
		return bidding_cost;
	}

	public void setBidding_cost(String bidding_cost) {
		this.bidding_cost = bidding_cost;
	}







	@Override
	public String toString() {
		return "DTO_Bidding [bidding_seq=" + bidding_seq + ", gonggo_seq=" + gonggo_seq + ", board_code=" + board_code
				+ ", biddinger=" + biddinger + ", biddinger_w=" + biddinger_w + ", bidding_cost=" + bidding_cost + "]";
	}
	
	
	
	
	
	
}
