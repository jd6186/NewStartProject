package com.start.pro.dto;

import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;

public class DTO_Filter {

	private String user_grade;
	private String replychk;
	private String delchk;
	private String filter;
	private String firstDate;
	private String lastDate;
	private List<String> successchk;
	private String start;
	private String last;
	private String[] temp;
	
	
	
	
	public String[] getTemp() {
		return temp;
	}

	public void setTemp(String[] temp) {
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}
		this.successchk = new ArrayList<String>(Arrays.asList(temp));
		System.out.println("잘 겹쳤어");
		this.temp = temp;
	}

	public DTO_Filter() {}


	
	


	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	public String getReplychk() {
		return replychk;
	}

	public void setReplychk(String replychk) {
		this.replychk = replychk;
	}

	public String getDelchk() {
		return delchk;
	}

	public void setDelchk(String delchk) {
		this.delchk = delchk;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		System.out.println("이건어케들어오는데 야발"+filter);
		this.filter = filter;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}



	public List<String> getSuccesschk() {
		return successchk;
	}

	@SuppressWarnings("unchecked")
	public void setSuccesschk(String[] successchk) {
		this.successchk = new ArrayList<String>(Arrays.asList(successchk));
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return "DTO_Filter [user_grade=" + user_grade + ", replychk=" + replychk + ", delchk=" + delchk + ", filter="
				+ filter + ", firstDate=" + firstDate + ", lastDate=" + lastDate + ", successchk=" + successchk
				+ ", start=" + start + ", last=" + last + "]";
	}

	
	
	
}
