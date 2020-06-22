package com.start.pro.models.mounui;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_FAQ;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Mounui;

@Service
public class Service_MounuiImpl<E> implements IService_Mounui{

	@Autowired
	private IDao_Mounui dao;
	
	
	@Override
	public boolean insertBoard(DTO_Mounui dto) {
		return dao.insertBoard(dto);
	}

	@Override
	public List<DTO_Mounui> userBoard(Map<String, String> map) {
		return dao.userBoard(map);
	}

	@Override
	public DTO_Mounui userBoardDetail(String seq) {
		return dao.userBoardDetail(seq);
	}

	@Override
	public boolean updateBoard(DTO_Mounui dto) {
		return dao.updateBoard(dto);
	}

	@Override
	public boolean delBoard(Map<String, String[]> map) {
		return dao.delBoard(map);
	}

	@Override
	public List<DTO_Mounui> adminBoard(DTO_Filter dto) {
		return  dao.adminBoard(dto);
	}

	@Override
	public DTO_Mounui adminBoardDetail(String seq) {
		return dao.adminBoardDetail(seq);
	}

	@Override
	public boolean replyMounui(String seq) {
		return dao.replyMounui(seq);
	}

	@Override
	public boolean adminDelBoard(Map<String, String[]> map) {
		return dao.adminDelBoard(map);
	}

	@Override
	public List<DTO_FAQ> getCategory() {
		return dao.getCategory();
	}

	@Override
	public DTO_Email getReply(String seq) {
		return dao.getReply(seq);
	}

	@Override
	public List<String> getTitle() {
		return dao.getTitle();
	}

	@Override
	public int getAdminMounuiCnt(DTO_Filter dto) {
		return dao.getAdminMounuiCnt(dto);
	}

	@Override
	public int getUserMounuiBoard(String seq) {
		return dao.getUserMounuiBoard(seq);
	}

}
