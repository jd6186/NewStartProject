package com.start.pro.dto;

public class DTO_File {

	private String file_seq;
	private String fileboard;
	private String board_seq;
	private String filename;
	private String filerealname;
	private String fileurl;
	private String filedate;
	private String filetype;
	private String user_seq;
	private String file_del;
	private String re_teacher;

	public DTO_File(String file_seq, String fileboard, String board_seq, String filename, String filerealname,
			String fileurl, String filedate, String filetype, String user_seq, String file_del) {
		super();
		this.file_seq = file_seq;
		this.fileboard = fileboard;
		this.board_seq = board_seq;
		this.filename = filename;
		this.filerealname = filerealname;
		this.fileurl = fileurl;
		this.filedate = filedate;
		this.filetype = filetype;
		this.user_seq = user_seq;
		this.file_del = file_del;
	}
	

	public DTO_File(String fileboard, String board_seq) {
		super();
		this.fileboard = fileboard;
		this.board_seq = board_seq;
	}

	
	//강사인증 생성자
	public DTO_File(String fileboard, String filename, String filerealname, String fileurl, String user_seq) {
		super();
		this.fileboard = fileboard;
		this.filename = filename;
		this.filerealname = filerealname;
		this.fileurl = fileurl;
		this.user_seq = user_seq;
	}
	


	public DTO_File(String fileboard, String board_seq, String filename, String filerealname, String fileurl,
			String user_seq, String re_teacher) {
		super();
		this.fileboard = fileboard;
		this.board_seq = board_seq;
		this.filename = filename;
		this.filerealname = filerealname;
		this.fileurl = fileurl;
		this.user_seq = user_seq;
		this.re_teacher = re_teacher;
	}


	public DTO_File(String fileboard, String board_seq, String filename, String filerealname, String fileurl, String filetype) {
		super();
		this.fileboard = fileboard;
		this.board_seq = board_seq;
		this.filename = filename;
		this.filerealname = filerealname;
		this.fileurl = fileurl;
		this.filetype =  filetype;
	}


	public DTO_File() {
		// TODO Auto-generated constructor stub
	}

	public String getFile_seq() {
		return file_seq;
	}

	public void setFile_seq(String file_seq) {
		this.file_seq = file_seq;
	}

	public String getFileboard() {
		return fileboard;
	}

	public void setFileboard(String fileboard) {
		this.fileboard = fileboard;
	}

	public String getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilerealname() {
		return filerealname;
	}

	public void setFilerealname(String filerealname) {
		this.filerealname = filerealname;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public String getFiledate() {
		return filedate;
	}

	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}

	public String getFile_del() {
		return file_del;
	}

	public void setFile_del(String file_del) {
		this.file_del = file_del;
	}
	

	protected String getRe_teacher() {
		return re_teacher;
	}


	protected void setRe_teacher(String re_teacher) {
		this.re_teacher = re_teacher;
	}


	@Override
	public String toString() {
		return "DTO_File [file_seq=" + file_seq + ", fileboard=" + fileboard + ", board_seq=" + board_seq
				+ ", filename=" + filename + ", filerealname=" + filerealname + ", fileurl=" + fileurl + ", filedate="
				+ filedate + ", filetype=" + filetype + ", user_seq=" + user_seq + ", file_del=" + file_del + "]";
	}

}
