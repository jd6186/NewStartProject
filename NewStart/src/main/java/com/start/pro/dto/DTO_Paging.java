package com.start.pro.dto;


public class DTO_Paging {

	private int pageList;	// 출력할 페이지번호 갯수
	private int index;		// 출력할 페이지 번호
	private int pageNum;	// 출력할 페이지 시작 번호
	private int listNum;	// 출력할 리스트 갯수
	private int total;		// 글 total 갯수
	
	{
		 pageList=10;
		 index = 0;
		 pageNum = 1;
		 listNum=10;
	}
	
	public DTO_Paging() {
	}

	public DTO_Paging(String index, String pageNum, String listNum) {
		if(index != null){
			this.index = Integer.parseInt(index);
		}
		if(pageNum != null){
			this.pageNum = Integer.parseInt(pageNum);
		}
		if(listNum != null){
			this.listNum = Integer.parseInt(listNum);
		}
	}

	@Override
	public String toString() {
		return "RowNumDto [출력할 페이지 번호 갯수=" + pageList + ", 출력할 페이지 번호=" + index + ", 출력할 페이지의 시작 번호=" + pageNum + ", 출력할 리스트 갯수=" + listNum
				+ ", 리스트의 총 갯수=" + total + "]";
	}
	
	// 글 리스트 중 시작 번호
	public int getStart() {
		return index*listNum+1;
	}

	// 글 리스트 중 끝 번호
	public int getlast() {
		return(index*listNum)+listNum;
	}
	
	// 카운트 
	public int getCount(){
				 //전체 갯수 - 출력 갯수 *(시작번호); 91-5(0)
		int temp = total  - listNum*(pageNum-1); 
		// 페이지의 최소값. 전체 갯수 / 출력 갯수 
		int min = 		temp  /  listNum;
		
		if(temp%listNum != 0) {
			min++;
		}
		
		int count = 0;
		if(temp < listNum) {
			count = pageNum;
		}else if(min <= pageList) {
			count = min+pageNum-1;
		}else {
			count = pageList + pageNum -1;
		}
		
		System.out.println("-------------------------------------count"+count);
		return count;
	}

	public int getPageList() {
		return pageList;
	}

	public void setPageList(int pageList) {
		this.pageList = pageList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = Integer.parseInt(index);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getListNum() {
		return listNum;
	}

	public void setListNum(int listNum) {
		this.listNum = listNum;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
