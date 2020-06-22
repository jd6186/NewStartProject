package com.start.pro.models.bwl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_BWL;

@Service
public class Service_BWLImpl implements IService_BWL{

	@Autowired
	IDao_BWL dao;
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public List<DTO_BWL> bwl_show() {
		return dao.bwl_show();
	}

	@Override
	public DTO_BWL bwl_detail(String gonggo_seq){
		return dao.bwl_detail(gonggo_seq);
	}

	@Override
	public boolean bwl_winner(DTO_BWL dto) {
		return dao.bwl_winner(dto);
	}
	

}