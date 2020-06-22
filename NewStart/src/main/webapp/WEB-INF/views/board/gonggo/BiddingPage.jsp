<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<script type="text/javascript" src="./js/boardListBidding.js"></script>
<!-- 수정폼 -->
   <div id="container">
      <div class="">
         <!-- Modal content-->
         <div class="">
            <div class="">
               <button type="button" class="close">&times;</button>
               <h4 class="">입찰 하기</h4>
               <br>
               <br>
            </div>
            <div id="modifyForms">
                  <form action="./biddingInsert.do" id="frmModify" onsubmit="return biddingPage()" method="post">
                 <input type='hidden' id='gonggo_seq' name='gonggo_seq' value="${gonggo_seq}">
                 <input type='hidden' id='biddinger' name='biddinger' value="${user_seq}">
                 
                 
                 <div class='form-group'>
                 <label for='content'>입찰 지원서</label>
                 <textarea class='form-control' id='bo_content' name='biddinger_w' rows='5' required="required"></textarea>
                 </div>
                 <br>
                 <label for='content'>입찰 금액</label>
                 <input type='text' id='bidding_cost' name='bidding_cost' placeholder="입찰금액을 입력해주세요" required="required">
                 
                 <div class='form-group'>
                   <input class='btn btn-success' type="submit" value='입찰하기'  >
                   <input class='btn btn-info' type='reset' value='내용 초기화'>
                   <button type='button' class='btn btn-default' data-dismiss='modal' >Close</button>
                   </div>
               </form>
            </div>
         </div>
      </div>
   </div>
   <script type="text/javascript">
      $(function(){
         CKEDITOR.replace('bo_content',{
            filebrowserUploadUrl: './adm/fileupload.do'
         });
      });
   </script>
</body>
</html>