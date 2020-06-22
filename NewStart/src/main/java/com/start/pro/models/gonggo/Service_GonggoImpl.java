package com.start.pro.models.gonggo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Bidding;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_File;
import com.start.pro.dto.DTO_Gonggo;

@Service
public class Service_GonggoImpl implements IService_Gonggo{

	@Autowired
	IDao_Gonggo dao;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<DTO_Gonggo> gonggo_View() {
		log.info("Service impl Gonggo_Show : \t{}");
		return dao.Gonggo_Show();
	}

	@Override
	public boolean gonggo_insert(DTO_Gonggo dto) {
		log.info("Service impl gonggo_insert : \t{}", dto);
		return dao.gonggo_insert(dto);
	}

	@Override
	public boolean gonggo_modify(DTO_Gonggo dto) {
		log.info("Service impl gonggo_modify : \t{}", dto);
		return dao.gonggo_modify(dto);
	}

	@Override
	public boolean gonggo_delete(String seq) {
		log.info("Service impl gonggo_delete : \t{}", seq);
		return dao.gonggo_delete(seq);
	}

	@Override
	public DTO_Gonggo gonggo_detail(String seq) {
		log.info("Service impl gonggo_detail : \t{}", seq);
		return dao.gonggo_detail(seq);
	}

	@Override
	public List<DTO_Bidding> gonggo_biddingerList(String seq) {
		log.info("Service impl gonggo_biddingerList : \t{}", seq);
		if(dao.gonggo_bidding_view(seq).equalsIgnoreCase("Y")) {
			return dao.gonggo_biddinger(seq);
		}
		return null;
	}

	@Override
	public String gonggo_fileox(String seq) {
		log.info("Service impl gonggo_fileox : \t{}", seq);
		return null;
	}

	@Override
	public List<DTO_File> gonggo_fileList(String seq) {
		log.info("Service impl gonggo_fileList : \t{}", seq);
		return null;
	}

	@Override
	public List<DTO_Gonggo> BoardListRowM(DTO_Criteria cri){
		log.info("Service impl BoardListRowM : \t{}", cri);
		return dao.BoardListRowM(cri);
	}
	@Override
	public List<DTO_Gonggo> BoardListRowT(DTO_Criteria cri){
		log.info("Service impl BoardListRowT : \t{}", cri);
		return dao.BoardListRowT(cri);
	}
	@Override
	public List<DTO_Gonggo> BoardListRowA(DTO_Criteria cri){
		log.info("Service impl BoardListRowA : \t{}", cri);
		return dao.BoardListRowA(cri);
	}

	@Override
	public int BoardListTotalA() {
		log.info("Service impl BoardListTotal : \t{}");
		return dao.BoardListTotalA();
	}
	@Override
	public int BoardListTotalT() {
		log.info("Service impl BoardListTotal : \t{}");
		return dao.BoardListTotalT();
	}
	@Override
	public int BoardListTotalM() {
		log.info("Service impl BoardListTotal : \t{}");
		return dao.BoardListTotalM();
	}

}