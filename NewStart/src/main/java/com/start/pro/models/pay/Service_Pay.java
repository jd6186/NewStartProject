package com.start.pro.models.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Credit;
import com.start.pro.dto.DTO_Refund_Pay;
import com.start.pro.models.user.IDao_User;

@Service
public class Service_Pay implements IService_Pay {

   private Logger logger = LoggerFactory.getLogger(this.getClass());
   
   @Autowired
   private IDao_Pay dao;
   
   @Autowired
   private IDao_User dao1;
   
   @Override
   public boolean createPay(DTO_Pay dto) {
      logger.info("createPay, {}", dto);
      return dao.createPay(dto);
   }

//   @Override
//   public List<DTO_Pay> selectPay(DTO_Criteria cri) {
//      logger.info("selectPay, {}", cri);
//      return dao.selectPay(cri);
//   }

   @Override
   public boolean refundPay(DTO_Refund_Pay ref) {
      logger.info("refundPay, {}", ref);
      return dao.refundPay(ref);
   }

//   @Override
//   public List<DTO_Refund_Pay> selectRef(DTO_Criteria cri) {
//      logger.info("selectRef, {}", cri);
//      return dao.selectRef(cri);
//   }

   @Override
   public boolean createCredit(DTO_Credit dto) {
      logger.info("createCredit, {}", dto);
      return dao.createCredit(dto);
   }

   @Override
   public int refundCredit(int seq) {
      logger.info("refundCredit, {}", seq);
      return dao.refundCredit(seq);
   }
   
   @Override
   public boolean payChk(int seq) {
      logger.info("payChk, {}", seq);
      return dao.payChk(seq);
   }

   @Override
   public List<DTO_Credit> selectCredit(int rowStart, int rowEnd, String user_seq) {
      logger.info("selectCredit, {}", new Date());
      return dao.selectCredit(rowStart, rowEnd, user_seq);
   }


   @Override
   public List<DTO_Refund_Credit> selectCreRef(String seq) {
      logger.info("selectCreRef, {}", seq);
      return dao.selectCreRef(seq);
   }

   @Override
   public int selectMax() {
      logger.info("selectMax, {}", new Date());
      return dao.selectMax();
   }

   @Override
   public boolean updateRef(int seq) {
      logger.info("updateRef, {}", seq);
      return dao.updateRef(seq);
   }
   
   @Override
   public int listCount(int seq) {
      logger.info("listCount 전체조회, {}", seq);
      return dao.listCount(seq);
   }
   
   @Override
   public int listAdCount() {
      logger.info("listAdCount 전체조회, {}", new Date());
      return dao.listAdCount();
   }

   @Override
   public int refListCount(int seq) {
      logger.info("refListCount 전체조회, {}", new Date());
      return dao.refListCount(seq);
   }
   @Override
   public int refListAdCount() {
      logger.info("refListAdCount 전체조회, {}", new Date());
      return dao.refListAdCount();
   }
   @Override
   public int creListCount(int seq) {
      logger.info("creListCount 전체조회, {}", new Date());
      return dao.creListCount(seq);
   }

   @Override
   public List<DTO_Pay> selectPay(int rowStart, int rowEnd, String user_seq) {
      logger.info("selectPay 전체조회, {}", new Date());
      return dao.selectPay(rowStart, rowEnd, user_seq);
   }
   
   @Override
   public List<DTO_Refund_Pay> selectRef(int rowStart, int rowEnd, String user_seq) {
      logger.info("selectRef, {}", new Date());
      return dao.selectRef(rowStart, rowEnd, user_seq);
   }

   @Override
   public List<DTO_Pay> selectAdPay(int rowStart, int rowEnd) {
      logger.info("selectAdPay 전체조회, {}", new Date());
      return dao.selectAdPay(rowStart, rowEnd);
   }

   @Override
   public List<DTO_Refund_Pay> selectAdRef(int rowStart, int rowEnd) {
      logger.info("selectAdRef 전체조회, {}", new Date());
      return dao.selectAdRef(rowStart, rowEnd);
   }

   @Override
   public int updateCredit(int seq) {
      logger.info("updateCredit 전체조회, {}", seq);
      String cnt = dao.updateCredit(seq);
      return (cnt == null) ? 0 : Integer.parseInt(cnt);
   }

   @Override
   public boolean updateTpost(int seq) {
      logger.info("updateTpost 전체조회, {}", seq);
      return dao.updateTpost(seq);
   }

   @Override
   public boolean updateMpost(int seq) {
      logger.info("updateMpost 전체조회, {}", seq);
      return dao.updateMpost(seq);
   }

   @Override
   public boolean updateMregi_BN(int seq) {
      logger.info("updateMregi_BN 전체조회, {}", seq);
      return dao.updateMregi_BN(seq);
   }

   @Override
   public boolean updateMregi_selT(int seq) {
      logger.info("updateMregi_selT 전체조회, {}", seq);
      return dao.updateMregi_selT(seq);
   }

   @Override
   public boolean updateTregi_selM(int seq) {
      logger.info("updateTregi_selM 전체조회, {}", seq);
      return dao.updateTregi_selM(seq);
   }

   @Override
   public boolean updateTregi_selT(int seq) {
      logger.info("updateTregi_selT 전체조회, {}", seq);
      return dao.updateTregi_selT(seq);
   }

   @Override
   public List<DTO_Credit> selectAdCredit(int rowStart, int rowEnd) {
      logger.info("selectAdCredit, {}", new Date());
      return dao.selectAdCredit(rowStart, rowEnd);
   }
   
   @Override
   public DTO_Pay selectOnePay(int seq) {
      logger.info("selectOnePay");
      return dao.selectOnePay(seq);
   }


}