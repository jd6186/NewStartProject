package com.start.pro.models.file;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_File;

@Repository
public class Dao_FileImpl implements IDao_File {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.File.";
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public boolean insertFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@insertFile,{}",dto);
		int isc = session.insert(NS+"insertFile",dto);
		return isc>0?true:false;
	}

	@Override
	public DTO_File searchFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@searchFile,{}",dto);
		DTO_File fdto = session.selectOne(NS+"searchFile",dto);
		System.out.println("fdto : " +  fdto);
		return fdto;
	}

	@Override
	public boolean delFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@delFile,{}",dto);
		int isc = session.update(NS+"delFile",dto);
		return isc>0?true:false;
	}

	@Override
	public DTO_File getDown(String seq) {
		return session.selectOne(NS+"getDown", seq);
	}

	@Override
	public List<DTO_File> searchFile2(DTO_File dto) {
		return session.selectList(NS+"searchFile2", dto);
	}

	@Override
	public boolean ReviewinsertFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@ReviewinsertFile,{}",dto);
		int isc = session.insert(NS+"ReviewinsertFile",dto);
		return isc>0?true:false;
	}

	@Override
	public boolean TeacherinsertFile(DTO_File dto) {
		log.info("DAO@@@@@@@@@@@@@@@@@TeacherinsertFile,{}",dto);
		int isc = session.insert(NS+"TeacherinsertFile",dto);
		return isc>0?true:false;
	}

	@Override
	public DTO_File downFile(String user_seq) {
		log.info("DAO@@@@@@@@@@@@@@@@@downFile,{}",user_seq);
		return session.selectOne(NS+"downFile",user_seq);
	}


	
}
