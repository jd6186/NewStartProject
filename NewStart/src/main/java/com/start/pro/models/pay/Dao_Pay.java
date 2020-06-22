package com.start.pro.models.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Credit;
import com.start.pro.dto.DTO_Refund_Pay;

@Repository
public class Dao_Pay implements IDao_Pay {

   private Logger logger = LoggerFactory.getLogger(this.getClass());
   private final String NS = "com.start.pro.pay.";
   
   @Autowired
   private SqlSessionTemplate sqlSession;

   
   @Override
   public boolean createPay(DTO_Pay dto) {
      logger.info("createPay");
      int a = sqlSession.insert(NS+"createPay", dto);
      return a > 0 ? true : false;
   }

//   @Override
//   public List<DTO_Pay> selectPay(DTO_Criteria cri) {
//      logger.info("selectPay");
//      return sqlSession.selectList(NS+"selectPay", cri);
//   }

   @Override
   public boolean refundPay(DTO_Refund_Pay ref) {
      logger.info("refundPay");
      int a = sqlSession.insert(NS+"refundPay", ref);
      return a > 0 ? true : false;
   }
   
   @Override
   public boolean payChk(int seq) {
      logger.info("payChk");
      int a = sqlSession.update(NS+"payChk", seq);
      return a > 0 ? true : false;
   }

//   @Override
//   public List<DTO_Refund_Pay> selectRef(DTO_Criteria cri) {
//      logger.info("selectRef");
//      return sqlSession.selectList(NS+"selectRef", cri);
//   }

   @Override
   public boolean createCredit(DTO_Credit dto) {
      logger.info("createCredit");
      int a = sqlSession.insert(NS+"createCredit", dto);
      return a > 0 ? true : false;
   }
   
   @Override
   public int refundCredit(int seq) {
      logger.info("refundCredit");
      return sqlSession.update(NS+"refundCredit", seq);
   }


   @Override
   public List<DTO_Credit> selectCredit(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd, @Param("user_seq") String user_seq) {
      logger.info("selectCredit");
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("rowStart", rowStart);
      map.put("rowEnd", rowEnd);
      map.put("user_seq", user_seq);
      return sqlSession.selectList(NS+"selectCredit", map);
   }


   @Override
   public List<DTO_Refund_Credit> selectCreRef(String seq) {
      logger.info("selectCreRef");
      return sqlSession.selectList(NS+"selectCreRef", seq);
   }

   @Override
   public int selectMax() {
      logger.info("selectMax");
      return sqlSession.selectOne(NS+"selectMax");
   }

   @Override
   public boolean updateRef(int seq) {
      logger.info("updateRef");
      int a = sqlSession.update(NS+"updateRef", seq);
      return a > 0 ? true : false;
   }
   
   @Override
   public int listCount(int seq) {
      logger.info("listCount");
      return sqlSession.selectOne(NS+"listCount", seq);
   }
   @Override
   public int listAdCount() {
      logger.info("listCount");
      return sqlSession.selectOne(NS+"listAdCount");
   }

   @Override
   public int refListCount(int seq) {
      logger.info("refListCount");
      return sqlSession.selectOne(NS+"refListCount", seq);
   }
   @Override
   public int refListAdCount() {
      logger.info("refListAdCount");
      return sqlSession.selectOne(NS+"refListAdCount");
   }
   @Override
   public int creListCount(int seq) {
      logger.info("creListCount");
      return sqlSession.selectOne(NS+"creListCount", seq);
   }

   @Override
   public List<DTO_Pay> selectPay(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd, @Param("user_seq") String user_seq) {
      logger.info("selectPay");
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("rowStart", rowStart);
      map.put("rowEnd", rowEnd);
      map.put("user_seq", user_seq);
      return sqlSession.selectList(NS + "selectPay", map);
   }
   
   @Override
   public List<DTO_Refund_Pay> selectRef(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd, @Param("user_seq") String user_seq) {
      logger.info("selectRef");
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("rowStart", rowStart);
      map.put("rowEnd", rowEnd);
      map.put("user_seq", user_seq);
      return sqlSession.selectList(NS+"selectRef", map);
   }

   @Override
   public List<DTO_Pay> selectAdPay(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd) {
      logger.info("selectAdPay");
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("rowStart", rowStart);
      map.put("rowEnd", rowEnd);
      return sqlSession.selectList(NS + "selectAdPay", map);
   }

   @Override
   public List<DTO_Refund_Pay> selectAdRef(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd) {
      logger.info("selectAdRef");
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("rowStart", rowStart);
      map.put("rowEnd", rowEnd);
      return sqlSession.selectList(NS+"selectAdRef", map);
   }

   @Override
   public String updateCredit(int seq) {
      logger.info("updateCredit");
      return sqlSession.selectOne(NS+"updateCredit", seq);
   }
   
   @Override
   public boolean updateTpost(int seq) {
      logger.info("updateTpost");
      int a = sqlSession.update(NS+"updateTpost", seq);
      return a > 0 ? true : false;
   }

   @Override
   public boolean updateMpost(int seq) {
      logger.info("updateMpost");
      int a = sqlSession.update(NS+"updateMpost", seq);
      return a > 0 ? true : false;
   }

   @Override
   public boolean updateMregi_BN(int seq) {
      logger.info("updateMregi_BN");
      int a = sqlSession.update(NS+"updateMregi_BN", seq);
      return a > 0 ? true : false;
   }

   @Override
   public boolean updateMregi_selT(int seq) {
      logger.info("updateMregi_selT");
      int a = sqlSession.update(NS+"updateMregi_selT", seq);
      return a > 0 ? true : false;
   }

   @Override
   public boolean updateTregi_selM(int seq) {
      logger.info("updateTregi_selM");
      int a = sqlSession.update(NS+"updateTregi_selM", seq);
      return a > 0 ? true : false;
   }

   @Override
   public boolean updateTregi_selT(int seq) {
      logger.info("updateTregi_selT");
      int a = sqlSession.update(NS+"updateTregi_selT", seq);
      return a > 0 ? true : false;
   }

   @Override
   public List<DTO_Credit> selectAdCredit(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd) {
      logger.info("selectAdCredit");
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("rowStart", rowStart);
      map.put("rowEnd", rowEnd);
      return sqlSession.selectList(NS+"selectAdCredit", map);
   }

   @Override
   public DTO_Pay selectOnePay(int seq) {
      logger.info("selectOnePay");
      return sqlSession.selectOne(NS+"selectOnePay", seq);
   }
   
   



}