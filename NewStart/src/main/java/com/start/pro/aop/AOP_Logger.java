package com.start.pro.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AOP에서 사용하는 Advice 메소드 
 * @author nobrand
 *
 */
public class AOP_Logger {
	
	
//	
	// pointCut에서 @Before
	public void before(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.debug("################## 시작 ##################");
		Object[] obj = j.getArgs();
		if(obj != null || obj.length != 0) {
			logger.debug("Method명 : " + j.getSignature().getName());
			for (int i = 0; i < obj.length; i++) {
				logger.debug(i+"번째:\t"+obj[i]);
			}
			logger.debug("Method명 : " + j.getSignature().getName());			
		}
	}
	
	
	// pointCut에서 @AfterReturing
	public void afterReturing(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.debug("################## 반환 완료 ##################");
	}
	
	
	// pointCut에서 @AfterThrowing
	public void daoError(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.debug("에러 :\t" + j.getArgs());
		logger.debug("에러 :\t" + j.toString());
	}
	
	
	
}
