package com.start.pro.dto;

public class DTO_ChatList {
	private String chat_seq  ;
	private String user_seq  ;
	private String chattitle ;
	private String createdate;
	private String lastchat  ;
	

	public DTO_ChatList() {
	}
	
	public DTO_ChatList(String chat_seq, String user_seq, String chattitle, String createdate, String lastchat) {
		super();
		this.chat_seq = chat_seq;
		this.user_seq = user_seq;
		this.chattitle = chattitle;
		this.createdate = createdate;
		this.lastchat = lastchat;
	}
	
	
	
	
	
	public DTO_ChatList(String user_seq) {
		super();
		this.user_seq = user_seq;
	}

	public String getChat_seq() {
		return chat_seq;
	}
	public void setChat_seq(String chat_seq) {
		this.chat_seq = chat_seq;
	}
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getChattitle() {
		return chattitle;
	}
	public void setChattitle(String chattitle) {
		this.chattitle = chattitle;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getLastchat() {
		return lastchat;
	}
	public void setLastchat(String lastchat) {
		this.lastchat = lastchat;
	}
	
	
	
	
	@Override
	public String toString() {
		return "DTO_CHATLIST [chat_seq=" + chat_seq + ", user_seq=" + user_seq + ", chattitle=" + chattitle
				+ ", createdate=" + createdate + ", lastchat=" + lastchat + "]";
	}
	
	
}
