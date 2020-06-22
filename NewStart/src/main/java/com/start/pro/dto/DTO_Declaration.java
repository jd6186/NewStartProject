package com.start.pro.dto;

public class DTO_Declaration {
	private String dec_seq;
	private String dec_victim;
	private String dec_suspect;
	private String board_code;
	private String board_seq;
	private String dec_date;
	private String dec_prodate;
	private String content;

	public DTO_Declaration(String dec_seq, String dec_victim, String dec_suspect, String board_code, String board_seq,
			String dec_date, String dec_prodate, String content) {
		super();
		this.dec_seq = dec_seq;
		this.dec_victim = dec_victim;
		this.dec_suspect = dec_suspect;
		this.board_code = board_code;
		this.board_seq = board_seq;
		this.dec_date = dec_date;
		this.dec_prodate = dec_prodate;
		this.content = content;
	}

	
	public DTO_Declaration(String dec_victim, String dec_suspect, String board_seq) {
		super();
		this.dec_victim = dec_victim;
		this.dec_suspect = dec_suspect;
		this.board_seq = board_seq;
	}


	public DTO_Declaration() {
		// TODO Auto-generated constructor stub
	}

	public String getDec_seq() {
		return dec_seq;
	}

	public void setDec_seq(String dec_seq) {
		this.dec_seq = dec_seq;
	}

	public String getDec_victim() {
		return dec_victim;
	}

	public void setDec_victim(String dec_victim) {
		this.dec_victim = dec_victim;
	}

	public String getDec_suspect() {
		return dec_suspect;
	}

	public void setDec_suspect(String dec_suspect) {
		this.dec_suspect = dec_suspect;
	}

	public String getBoard_code() {
		return board_code;
	}

	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}

	public String getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}

	public String getDec_date() {
		return dec_date;
	}

	public void setDec_date(String dec_date) {
		this.dec_date = dec_date;
	}

	public String getDec_prodate() {
		return dec_prodate;
	}

	public void setDec_prodate(String dec_prodate) {
		this.dec_prodate = dec_prodate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "DTO_Declaration [dec_seq=" + dec_seq + ", dec_victim=" + dec_victim + ", dec_suspect=" + dec_suspect
				+ ", board_code=" + board_code + ", board_seq=" + board_seq + ", dec_date=" + dec_date
				+ ", dec_prodate=" + dec_prodate + ", content=" + content + "]";
	}

}
