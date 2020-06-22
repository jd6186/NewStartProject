package com.start.pro.models.gonggi;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Gonggi;

@Service
public class Service_Gonggi implements IService_Gonggi {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDao_Gonggi dao;
	
	@Override
	public List<DTO_Gonggi> GI_AllSelect(DTO_Criteria cri) {
		logger.info("GI_AllSelect 전체조회, {}", cri);
		return dao.GI_AllSelect(cri);
	}
	
	@Override
	public int listCount() {
		logger.info("listCount 전체조회, {}", new Date());
		return dao.listCount();
	}

	@Override
	public DTO_Gonggi GI_OneSelect(Map<String, Object> map) {
		logger.info("GI_OneSelect 상세조회, {}", map);
		return dao.GI_OneSelect(map);
	}

	@Override
	public Boolean GI_Imp_Insert(DTO_Gonggi dto) {
		logger.info("GI_Imp_Insert 중요공지 등록, {}", dto);
		return dao.GI_Imp_Insert(dto);
	}

	@Override
	public Boolean GI_UImp_Insert(DTO_Gonggi dto) {
		logger.info("GI_UImp_Insert 일반공지 등록, {}", dto);
		return dao.GI_UImp_Insert(dto);
	}

	@Override
	public Boolean GI_Update(DTO_Gonggi dto) {
		logger.info("GI_Update 공지 수정, {}", dto);
		return dao.GI_Update(dto);
	}

	@Override
	public Boolean GI_Delete(String seq) {
		logger.info("GI_Delete 공지 삭제, {}", seq);
		return dao.GI_Delete(seq);
	}

	@Override
	public Boolean N_update(DTO_Gonggi dto) {
		logger.info("N_update 공지 삭제, {}", dto);
		return dao.N_update(dto);
	}

	

}
