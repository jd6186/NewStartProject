package com.start.pro.models.gonggi;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Gonggi;

@Repository
public class Dao_Gonggi implements IDao_Gonggi {

	private final String NS = "com.start.pro.gonggi.";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<DTO_Gonggi> GI_AllSelect(DTO_Criteria cri) {
		logger.info("GI_AllSelect");
		return sqlSession.selectList(NS+"GI_AllSelect", cri);
	}
	
	@Override
	public int listCount() {
		logger.info("listCount");
		return sqlSession.selectOne(NS+"listCount");
	}

	@Override
	public DTO_Gonggi GI_OneSelect(Map<String, Object> map) {
		logger.info("GI_OneSelect");
		return sqlSession.selectOne(NS+"GI_OneSelect", map);
	}

	@Override
	public Boolean GI_Imp_Insert(DTO_Gonggi dto) {
		logger.info("GI_Imp_Insert");
		int a = sqlSession.insert(NS+"GI_Imp_Insert", dto);
		return  (a > 0) ? true : false;
	}

	@Override
	public Boolean GI_UImp_Insert(DTO_Gonggi dto) {
		logger.info("GI_UImp_Insert");
		int a = sqlSession.insert(NS+"GI_UImp_Insert", dto);
		return  (a > 0) ? true : false;
	}

	@Override
	public Boolean GI_Update(DTO_Gonggi dto) {
		logger.info("GI_Update");
		int a = sqlSession.update(NS+"GI_Update", dto);
		return  (a > 0) ? true : false;
	}

	@Override
	public Boolean GI_Delete(String seq) {
		logger.info("GI_Delete");
		int a = sqlSession.delete(NS+"GI_Delete", seq);
		return  (a > 0) ? true : false;
	}

	@Override
	public Boolean N_update(DTO_Gonggi dto) {
		logger.info("N_update");
		int a = sqlSession.update(NS+"N_update", dto);
		return  (a > 0) ? true : false;
	}


}
