package com.start.pro.models.gonggo;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Bidding;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;

@Repository
public class Dao_GonggoImpl implements IDao_Gonggo{

	@Autowired
	SqlSessionTemplate session;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.gongo.";
	private final String NSS = "com.start.pro.bidding.";
	
	@Override
	public List<DTO_Gonggo> Gonggo_Show() { // Gonggo_Show
		log.info("dao impl Gonggo_Show : \t{}");
		return session.selectList(NS+"Gonggo_Show");
	}

	@Override
	public boolean gonggo_insert(DTO_Gonggo dto) {
		log.info("dao impl gonggo_insert : \t{}", dto);
		int n = session.insert(NS+"gonggo_insert", dto);
		return n>0?true:false;
	}

	@Override
	public boolean gonggo_modify(DTO_Gonggo dto) {
		log.info("dao impl gonggo_modify : \t{}", dto);
		int n = session.update(NS+"gonggo_modify", dto);
		return n>0?true:false;
	}

	@Override
	public boolean gonggo_delete(String seq) {
		log.info("dao impl gonggo_delete : \t{}", seq);
		int n = session.delete(NS+"gonggo_delete", seq);
		return n>0?true:false;
	}

	@Override
	public DTO_Gonggo gonggo_detail(String seq) {
		log.info("dao impl gonggo_detail : \t{}", seq);
		return session.selectOne(NS+"gonggo_detail", seq);
	}

	@Override
	public String gonggo_bidding_view(String seq) {
		log.info("dao impl gonggo_bidding_view : \t{}", seq);
		return session.selectOne(NS+"gonggo_bidding_view", seq);
	}

	@Override
	public List<DTO_Bidding> gonggo_biddinger(String seq) {
		log.info("dao impl gonggo_biddinger : \t{}", seq);
		return session.selectList(NS+"gonggo_biddinger", seq);
	}

	@Override
	public String gonggo_fileox(String seq) {
		log.info("dao impl gonggo_fileox : \t{}", seq);
		return session.selectOne(NS+"gonggo_fileox", seq);
	}

	@Override
	public DTO_File gonggo_upload_file_view(String seq) {
		log.info("dao impl gonggo_upload_file_view : \t{}", seq);
		return session.selectOne(NS+"gonggo_upload_file_view", seq);
	}

	@Override
	   public List<DTO_Gonggo> BoardListRowM(DTO_Criteria cri) {
		log.info("Welcome BoardListRowM DAO 진입 : \t{}", cri);
	      return session.selectList(NS+"BoardListRowM",cri);
	   }
	   @Override
	   public List<DTO_Gonggo> BoardListRowT(DTO_Criteria cri) {
		   log.info("Welcome BoardListRowT DAO 진입 : \t{}", cri);
		   return session.selectList(NS+"BoardListRowT",cri);
	   }
	   @Override
	   public List<DTO_Gonggo> BoardListRowA(DTO_Criteria cri) {
		   log.info("Welcome BoardListRowA DAO 진입 : \t{}", cri);
		   return session.selectList(NS+"BoardListRowA",cri);
	   }
	
	 @Override
	   public int BoardListTotalA() {
		 log.info("Welcome BoardListTotalA DAO 진입 : \t{}");
	      return session.selectOne(NS+"BoardListTotalA");
	   }
	 @Override
	 public int BoardListTotalM() {
		 log.info("Welcome BoardListTotalM DAO 진입 : \t{}");
		 return session.selectOne(NS+"BoardListTotalM");
	 }
	 @Override
	 public int BoardListTotalT() {
		 log.info("Welcome BoardListTotalT DAO 진입 : \t{}");
		 return session.selectOne(NS+"BoardListTotalT");
	 }
	
}
