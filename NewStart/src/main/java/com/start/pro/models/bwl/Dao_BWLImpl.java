package com.start.pro.models.bwl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_BWL;

@Repository
public class Dao_BWLImpl implements IDao_BWL{

	@Autowired
	SqlSessionTemplate session;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.bidding_w_list.";
	
	@Override
	public List<DTO_BWL> bwl_show() {
		log.info("bwl_show 진입");
		return session.selectList(NS+"bwl_show");
	}
	@Override
	public DTO_BWL bwl_detail(String gonggo_seq) {
		log.info("bwl_detail 진입 : \t{}", gonggo_seq);
		return session.selectOne(NS+"bwl_detail", gonggo_seq);
	}
	@Override
	public boolean bwl_winner(DTO_BWL dto) {
		log.info("bwl_winner 진입 : \t{}", dto);
		int n = session.insert(NS+"bwl_winner", dto);
		return n > 0 ? true : false;
	}


}
