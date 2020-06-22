package com.start.pro.models.push;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Push;


@Repository
public class Dao_PushImpl implements IDao_Push {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.start.pro.push.";
 	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean AlramInsert(DTO_Push dto) {
		log.info("DAO AlramInsert 진입 : \t {}", dto);
		int n = sqlSession.insert(NS+"AlramInsert",dto);
		return n > 0 ? true : false;
	}

	@Override
	public List<DTO_Push> AlramList() {
		log.info("DAO AlramList 진입 ");
		return sqlSession.selectList(NS+"AlramList");
	}

	@Override
	public List<DTO_Push> AccidentSearch(String push_accident) {
		log.info("DAO AccidentSearch 진입 : \t {}", push_accident);
		return sqlSession.selectList(NS+"AccidentSearch",push_accident);
	}

	@Override
	public DTO_Push AlramDetail(String push_seq) {
		log.info("DAO AlramDetail 진입 : \t {}", push_seq);
		return sqlSession.selectOne(NS+"AlramDetail", push_seq);
	}

	@Override
	public boolean AlramDelete(String push_seq) {
		log.info("DAO AlramDelete 진입 : \t {}", push_seq);
		int n = sqlSession.update(NS+"AlramDelete",push_seq);
		return  n > 0 ? true : false;
	}

	@Override
	public boolean AlramStop(String push_seq) {
		log.info("DAO AlramStop 진입 : \t {}", push_seq);
		int n = sqlSession.update(NS+"AlramStop",push_seq);
		return  n > 0 ? true : false;
	}

	@Override
	public boolean AlramRestart(String push_seq) {
		log.info("DAO AlramRestart 진입 : \t {}", push_seq);
		int n = sqlSession.update(NS+"AlramRestart", push_seq);
		return  n > 0 ? true : false;
	}

	@Override
	public List<DTO_Push> PushState_select(String push_state) {
		log.info("DAO PushState_select 진입 : \t {}", push_state);
		return sqlSession.selectList(NS+"PushState_select", push_state);
	}

	@Override
	public List<DTO_Push> AutoMenual_select(String push_am) {
		log.info("DAO AutoMenual_select 진입 : \t {}", push_am);
		return sqlSession.selectList(NS+"AutoMenual_select" ,push_am);
	}

	@Override
	public boolean AutoAlram_Update(DTO_Push dto) {
		log.info("DAO AutoAlram_Update 진입 : \t {}", dto);
		int n = sqlSession.update(NS+"AutoAlram_Update",dto);
		return  n > 0 ? true : false;
	}
	
	
	
}
