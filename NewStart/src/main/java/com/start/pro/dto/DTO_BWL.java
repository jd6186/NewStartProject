package com.start.pro.dto;

public class DTO_BWL {
	private String success_person ;
	private String gonggo_seq     ;
	private String board_code     ;
	
	
	
	
	public DTO_BWL(String success_person, String gonggo_seq) {
		super();
		this.success_person = success_person;
		this.gonggo_seq = gonggo_seq;
	}




	public DTO_BWL(String gonggo_seq) {
		super();
		this.gonggo_seq = gonggo_seq;
	}




	public DTO_BWL(String success_person, String gonggo_seq, String board_code) {
		super();
		this.success_person = success_person;
		this.gonggo_seq = gonggo_seq;
		this.board_code = board_code;
	}




	public String getSuccess_person() {
		return success_person;
	}




	public void setSuccess_person(String success_person) {
		this.success_person = success_person;
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




	@Override
	public String toString() {
		return "DTO_BWL [success_person=" + success_person + ", gonggo_seq=" + gonggo_seq + ", board_code=" + board_code
				+ "]";
	}
	
	
	
	
	
	
	
}
