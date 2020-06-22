package com.start.pro.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.start.pro.dto.DTO_File;
import com.start.pro.models.file.IService_File;

@Controller
public class Controller_File {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService_File service;
	
	
}
