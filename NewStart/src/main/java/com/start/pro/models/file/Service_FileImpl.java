package com.start.pro.models.file;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_File;

@Service
public class Service_FileImpl implements IService_File{

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IDao_File dao;
	
	@Override
	public boolean insertFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@insertFile,{}",dto);
		return dao.insertFile(dto);
	}

	@Override
	public DTO_File searchFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@searchFile,{}",dto);
		return dao.searchFile(dto);
	}

	@Override
	public boolean delFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@delFile,{}",dto);
		return dao.delFile(dto);
	}

	@Override
	public DTO_File getDown(String seq) {
		return dao.getDown(seq);
	}

	@Override
	public List<DTO_File> searchFile2(DTO_File dto) {
		return dao.searchFile2(dto);
	}

	@Override
	public boolean ReviewinsertFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@ReviewinsertFile,{}",dto);
		return dao.ReviewinsertFile(dto);
	}

	@Override
	public boolean TeacherinsertFile(DTO_File dto) {
		log.info("Service@@@@@@@@@@@TeacherinsertFile,{}",dto);
		return dao.TeacherinsertFile(dto);
	}

	@Override
	public DTO_File downFile(String user_seq) {
		log.info("Service@@@@@@@@@@@downFile,{}",user_seq);
		return dao.downFile(user_seq);
	}



}
