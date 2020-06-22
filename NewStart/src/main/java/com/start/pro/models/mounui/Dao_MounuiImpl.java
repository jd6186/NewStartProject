package com.start.pro.models.mounui;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Email;
import com.start.pro.dto.DTO_FAQ;
import com.start.pro.dto.DTO_Filter;
import com.start.pro.dto.DTO_Mounui;


@Repository
public class Dao_MounuiImpl implements IDao_Mounui {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.start.pro.mounui.";
	
	@Override
	public boolean insertBoard(DTO_Mounui dto) {
		return session.insert(NS+"insertBoard", dto) > 0 ? true : false;
	}

	@Override
	public List<DTO_Mounui> userBoard(Map<String,String> map) {
		return session.selectList(NS+"userBoard", map);
	}

	@Override
	public DTO_Mounui userBoardDetail(String seq) {
		return session.selectOne(NS+"userBoardDetail", seq);
	}

	@Override
	public boolean updateBoard(DTO_Mounui dto) {
		return session.update(NS+"updateBoard", dto) > 0 ? true : false;
	}

	@Override
	public boolean delBoard(Map<String, String[]> map) {
		return session.update(NS+"delBoard", map) > 0 ? true : false;
	}

	@Override
	public List<DTO_Mounui> adminBoard(DTO_Filter dto) {
		return session.selectList(NS+"adminBoard", dto);
	}

	@Override
	public DTO_Mounui adminBoardDetail(String seq) {
		return session.selectOne(NS+"adminBoardDetail", seq);
	}

	@Override
	public boolean replyMounui(String seq) {
		return session.update(NS+"replyMounui", seq) > 0 ? true : false;
	}

	@Override
	public boolean adminDelBoard(Map<String, String[]> map) {
		return session.delete(NS+"adminDelBoard", map) > 0 ? true : false;
	}

	@Override
	public List<DTO_FAQ> getCategory() {
		return session.selectList(NS+"getCategory");
	}

	@Override
	public DTO_Email getReply(String seq) {
		return session.selectOne(NS+"getReply",seq);
	}

	@Override
	public List<String> getTitle() {
		return session.selectList(NS+"getTitle");
	}

	@Override
	public int getAdminMounuiCnt(DTO_Filter dto) {
		return Integer.parseInt(session.selectOne(NS+"getAdminMounuiCnt", dto));
	}

	@Override
	public int getUserMounuiBoard(String seq) {
		return Integer.parseInt(session.selectOne(NS+"getUserMounuiBoard", seq));
	}

}
