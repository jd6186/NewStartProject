package com.start.pro.models.pay;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Credit;
import com.start.pro.dto.DTO_Refund_Pay;

public interface IService_Pay {

   /**
    * 결제 생성
    * @param dto
    * @return true
    */
   public boolean createPay(DTO_Pay dto);
   
   /**
    * 결제 내역 조회
    * @param seq
    * @return List<DTO_Pay>
    */
//   public List<DTO_Pay> selectPay(DTO_Criteria cri);
   public List<DTO_Pay> selectPay(int rowStart, int rowEnd, String user_seq);
   
   /**
    * 관리자 결제 내역 조회
    * @param seq
    * @return List<DTO_Pay>
    */
   public List<DTO_Pay> selectAdPay(int rowStart, int rowEnd);
   
   /**
    * 결제 상세 정보 가져오기
    * @param seq
    * @return DTO_Pay
    */
   public DTO_Pay selectOnePay(int seq);
   
   /**
    * 처음 결제인지 판단 여부
    * @param seq
    * @return int
    */
   public boolean payChk(int seq);
   
   /**
    * 상품 번호 조회
    * @param num
    * @return int
    */
   public int selectMax();
   
   /**
    * 결제 환불
    * @param ref
    * @return true
    */
   public boolean refundPay(DTO_Refund_Pay ref);
   
   /**
    * 환불 여부 N으로 바꾸기
    * @param seq
    * @return   true
    */
   public boolean updateRef(int seq);
   
   /**
    * 환불내역 조회
    * @param i
    * @return List<DTO_Refund_Pay>
    */
//   public List<DTO_Refund_Pay> selectRef(DTO_Criteria cri);
   public List<DTO_Refund_Pay> selectRef(int rowStart, int rowEnd, String user_seq);
   /**
    * 관리자 환불내역 조회
    * @param i
    * @return List<DTO_Refund_Pay>
    */
//   public List<DTO_Refund_Pay> selectRef(DTO_Criteria cri);
   public List<DTO_Refund_Pay> selectAdRef(int rowStart, int rowEnd);
   
   /**
    * 크레딧 생성
    * @param dto
    * @return true
    */
   public boolean createCredit(DTO_Credit dto);
   
   /**
    * 크래딧 
    * @param seq
    * @return true
    */
   public int updateCredit(int seq);
   /**
    * 크레딧 환불
    * @param dto
    * @return true
    */
   public int refundCredit(int seq);
   
   /**
    * 크레딧 내역 조회
    * @param seq
    * @return List<DTO_Credit>
    */
   public List<DTO_Credit> selectCredit(int rowStart, int rowEnd, String user_seq);
   
   /**
    * 크레딧 내역 조회
    * @param seq
    * @return List<DTO_Credit>
    */
   public List<DTO_Credit> selectAdCredit(int rowStart, int rowEnd);
   
   
   /**
    * 크레딧 환불 내역 조회
    * @param seq
    * @return List<DTO_Refund_Credit>
    */
   public List<DTO_Refund_Credit> selectCreRef(String seq);
   
   /**
    * 게시물 총 갯수
    * @return
    */
   public int listCount(int seq);
   /**
    * 관리자게시물 총 갯수
    * @return
    */
   public int listAdCount();
   /**
    * 환불 게시물 총 갯수
    * @return
    */
   public int refListCount(int seq);
   /**
    * 환불 게시물 총 갯수
    * @return
    */
   public int refListAdCount();
   /**
    * 크레딧 게시물 총 갯수
    * @return
    */
   public int creListCount(int seq);
   /**
    * user_seq 가 user_grade 가 T 일 때 공고글 올릴 때 크레딧 소모가 10개
    * @param seq
    * @return
    */
   public boolean updateTpost(int seq);
   
   /**
    * user_seq 가 user_grade 가 M 일 때 공고글 올릴 때 크레딧 소모가 6개
    * @param seq
    * @return
    */
   public boolean updateMpost(int seq);
   
   /**
    * user_seq가 user_grade 가 M 일 때 공고글을 올렸는데 공고의 bidding 이 N 일 때 크레딧 3개 환불
    * @param seq
    * @return
    */
   public boolean updateMregi_BN(int seq);
   
   /**
    * user_seq가 user_grade 가 M 일 때 강사를 콕찝어서 매칭 됐을때 크레딧 소모가 15개
    * @param seq
    * @return
    */
   public boolean updateMregi_selT(int seq);
   
   /**
    * user_seq 가 user_grade 가 T 일 때 멘티에게 매칭 신청시 크레딧 소모가 10개
    * @param seq
    * @return
    */
   public boolean updateTregi_selM(int seq);
   
   /**
    * user_seq가 user_grade 가 T 일 때  멘티안테 매칭 신청했는데 안됏을 때 크레딧 5개 환불
    * @param seq
    * @return
    */
   public boolean updateTregi_selT(int seq);
}