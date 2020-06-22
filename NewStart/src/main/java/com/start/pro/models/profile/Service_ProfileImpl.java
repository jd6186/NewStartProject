package com.start.pro.models.profile;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.start.pro.dto.DTO_Career;
import com.start.pro.dto.DTO_Profile;
import com.start.pro.dto.DTO_User;
import com.start.pro.security.Sc_User;

@Service
public class Service_ProfileImpl implements IService_Profile{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDao_Profile dao;
	
	@Override
	public DTO_Profile searchProfile(String user_seq) {
		log.info("Service@@@@@프로필 전체 조회,{}",user_seq);
		return dao.searchProfile(user_seq);
	}
	
	@Override
	public DTO_Career searchCareer(String user_seq) {
		log.info("Service@@@@@프로필 전체 조회,{}",user_seq);
		return dao.searchCareer(user_seq);
	}


	@Override
	public boolean insertProfile(DTO_Profile pDto) {
		log.info("Service@@@@@프로필 등록 ,{}",pDto);
		return dao.insertProfile(pDto);
	}

	@Override
	public boolean insertCareer(DTO_Career cDto) {
		log.info("Service@@@@@경력 등록,{}",cDto);
		return dao.insertCareer(cDto);
	}

	@Override
	public boolean updateProfile(DTO_Profile pDto) {
		log.info("Service@@@@@프로필 수정,{}",pDto);
		return dao.updateProfile(pDto);
	}

	@Override
	public boolean updateCareer(DTO_Career cDto) {
		log.info("Service@@@@@경력 수정,{}",cDto);
		return dao.updateCareer(cDto);
	}

	@Override
	public String avgStar(String user_seq) {
		log.info("Service@@@@@경력 수정,{}",user_seq);
		return dao.avgStar(user_seq);
	}

	


}
