package com.start.pro.models.bidding;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Bidding;

@Service
public class Service_BiddingImpl implements IService_Bidding{

	@Autowired
	IDao_Bidding dao;
	
	Logger log = LoggerFactory.getLogger(getClass());



	@Override
	public boolean bidding_insert(DTO_Bidding dto) {
		log.info("Service Impl bidding_insert 진입 완료: \t{}", dto);
		return dao.bidding_insert(dto);
	}




	@Override
	public boolean bidding_update(String gonggo_seq) {
		log.info("Service Impl bidding_update 진입 완료: \t{}", gonggo_seq);
		return dao.bidding_update(gonggo_seq);
	}

	
	
	@Override
	public List<DTO_Bidding> bidding_list(Map<String, String> map) {
		log.info("Service Impl bidding_list 진입 완료: \t{}", map);
		return dao.bidding_list(map);
	}



	

}