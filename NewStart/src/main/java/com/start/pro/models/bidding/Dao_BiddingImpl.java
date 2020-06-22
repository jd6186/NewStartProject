package com.start.pro.models.bidding;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Bidding;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;

@Repository
public class Dao_BiddingImpl implements IDao_Bidding{

	@Autowired
	SqlSessionTemplate session;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.bidding.";

	@Override
	public boolean bidding_insert(DTO_Bidding dto) {
		log.info("WelCome bidding_insert :  \t {}", dto);
		int n = session.insert(NS+"bidding_insert", dto);
		return n > 0 ? true : false;
	}
	
	@Override
	public boolean bidding_update(String gonggo_seq) {
		log.info("WelCome bidding_update :  \t {}", gonggo_seq);
		int n = session.insert(NS+"bidding_update", gonggo_seq);
		return n > 0 ? true : false;
	}
	
	@Override
	public List<DTO_Bidding> bidding_list(Map<String, String> map) {
		log.info("WelCome bidding_list :  \t {}", map);
		//List<DTO_Bidding> lists = session.selectList(NS+"bidding_list", map);
		//return lists;
		return session.selectList(NS+"bidding_list", map);
	}
	
	// 이거부터 다시 수정하면 되요ㅛㅇ
	

}
