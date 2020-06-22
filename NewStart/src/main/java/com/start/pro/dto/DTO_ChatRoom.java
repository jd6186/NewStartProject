package com.start.pro.dto;

public class DTO_ChatRoom {
	private String content_seq;
	private String user_seq   ;
	private String chatcontent;
	private String chattime   ;
	private String chat_seq   ;
	
	public DTO_ChatRoom() {
	}

	public DTO_ChatRoom(String content_seq, String user_seq, String chatcontent, String chattime, String chat_seq) {
		super();
		this.content_seq = content_seq;
		this.user_seq = user_seq;
		this.chatcontent = chatcontent;
		this.chattime = chattime;
		this.chat_seq = chat_seq;
	}

	
	public DTO_ChatRoom(String chat_seq, String user_seq) {
		super();
		this.chat_seq = chat_seq;
		this.user_seq = user_seq;
	}

	public DTO_ChatRoom(String user_seq) {
		super();
		this.user_seq = user_seq;
	}

	public String getContent_seq() {
		return content_seq;
	}

	public void setContent_seq(String content_seq) {
		this.content_seq = content_seq;
	}

	public String getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}

	public String getChatcontent() {
		return chatcontent;
	}

	public void setChatcontent(String chatcontent) {
		this.chatcontent = chatcontent;
	}

	public String getChattime() {
		return chattime;
	}

	public void setChattime(String chattime) {
		this.chattime = chattime;
	}

	public String getChat_seq() {
		return chat_seq;
	}

	public void setChat_seq(String chat_seq) {
		this.chat_seq = chat_seq;
	}

	
	
	@Override
	public String toString() {
		return "DTO_ChatRoom [content_seq=" + content_seq + ", user_seq=" + user_seq + ", chatcontent=" + chatcontent
				+ ", chattime=" + chattime + ", chat_seq=" + chat_seq + "]";
	}
	
}
