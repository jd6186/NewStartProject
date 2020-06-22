package com.start.pro.models.pay;

import java.util.List;

import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_User;

public class InputPayList {

	   private List<DTO_Pay> lists;
	   private DTO_User users;
	   private DTO_PageMaker pageMaker;
	   private int listTotal;
	
	   public void setLists(List<DTO_Pay> lists) {
	      this.lists = lists;
	   }
	
	   public void setUsers(DTO_User users) {
	      this.users = users;
	   }
	
	   public void setPageMaker(DTO_PageMaker pageMaker) {
	      this.pageMaker = pageMaker;
	   }
	   public void setListTotal(int listTotal) {
	      this.listTotal = listTotal;
	   }
	
}
