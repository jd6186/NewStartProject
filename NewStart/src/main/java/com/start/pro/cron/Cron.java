package com.start.pro.cron;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.start.pro.dto.DTO_User;
import com.start.pro.models.user.IService_User;

@Component
public class Cron {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IService_User userService;
	
	//하루에 한번 실행 
	@Scheduled(cron = "0 0 0 * * *")
	public void run() {
		log.info("cron 실행, 하루에 한번씩, {}",new Date());
		boolean isc = userService.updateHuman();
		if(isc) {
			log.info("휴면 업데이트 성공");
		}else {
			log.info("휴면 업데이트 실패");
			
		}
	}

}
