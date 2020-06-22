package com.start.pro.models.dec;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Declaration;

@Service
public class Service_DecImpl implements IService_Dec{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IDao_Dec dao;
	@Override
	public List<DTO_Declaration> searchDecAll() {
		log.info("Service searchDecAll{}",new Date());
		return dao.searchDecAll();
	}

	@Override
	public boolean insertDec(DTO_Declaration dto) {
		log.info("Service insertDec{}",dto);
		return dao.insertDec(dto);
	}

	@Override
	public boolean updateDeccnt(String dec_seq) {
		log.info("Service updateDeccnt{}",dec_seq);
		return dao.updateDeccnt(dec_seq);
	}



}
