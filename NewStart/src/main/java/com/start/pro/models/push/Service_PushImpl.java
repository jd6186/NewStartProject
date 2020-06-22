package com.start.pro.models.push;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Push;


@Service
public class Service_PushImpl implements IService_Push {

	@Autowired
	IDao_Push dao;
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean AlramInsert(DTO_Push dto) {
		log.info("Service AlramInsert 진입 : \t {}", dto);
		return dao.AlramInsert(dto);
	}

	@Override
	public List<DTO_Push> AlramList() {
		log.info("Service AlramInsert 진입 ");
		return dao.AlramList();
	}

	@Override
	public List<DTO_Push> AccidentSearch(String push_accident) {
		log.info("Service AlramInsert 진입 : \t {}", push_accident);
		return dao.AccidentSearch(push_accident);
	}

	@Override
	public DTO_Push AlramDetail(String push_seq) {
		log.info("Service AlramInsert 진입 : \t {}", push_seq);
		return dao.AlramDetail(push_seq);
	}

	@Override
	public boolean AlramDelete(String push_seq) {
		log.info("Service AlramInsert 진입 : \t {}", push_seq);
		return dao.AlramDelete(push_seq);
	}

	@Override
	public boolean AlramStop(String push_seq) {
		log.info("Service AlramInsert 진입 : \t {}", push_seq);
		return dao.AlramStop(push_seq);
	}

	@Override
	public boolean AlramRestart(String push_seq) {
		log.info("Service AlramInsert 진입 : \t {}", push_seq);
		return dao.AlramRestart(push_seq);
	}

	@Override
	public List<DTO_Push> PushState_select(String push_state) {
		log.info("Service AlramInsert 진입 : \t {}", push_state);
		return dao.PushState_select(push_state);
	}

	@Override
	public List<DTO_Push> AutoMenual_select(String push_am) {
		log.info("Service AlramInsert 진입 : \t {}", push_am);
		return dao.AutoMenual_select(push_am);
	}

	@Override
	public boolean AutoAlram_Update(DTO_Push dto) {
		log.info("Service AlramInsert 진입 : \t {}", dto);
		return dao.AutoAlram_Update(dto);
	}
	
	
}
