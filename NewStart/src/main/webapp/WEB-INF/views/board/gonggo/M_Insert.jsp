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
<script type="text/javascript" src="./js/boardListGonggo.js"></script>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
   
<div id="container">
   <form action="./boardInsert.do" method="post" enctype="multipart/form-data" onsubmit="return CreditUseing()">
      <input type="file" name="myfile" onchange="fileCheck()"  required="required" >
      <b>유저 번호</b><input class="ipt" type="text" name="user_seq" id="user_seq" value="${user_seq}" readonly="readonly" required="required">
      <b>공고 제목</b><input class="ipt" type="text" name="gonggo_title" id="gonggo_title" required="required">
      <b>공고 비용</b><input class="ipt" type="text" name="gongo_cost" id="gongo_cost" required="required" numberOnly>원
      <br>
      <br>
      <br>
      <textarea id="bo_content" class="bo_content" name="gonggo_content" rows="30" cols="30"></textarea><br>
      <input type="button" class="btn btn-info" value="뒤로가기" onclick="javascript:history.back(-1)">
      <input type="submit" class="btn btn-success">
   </form>
</div>


   <script type="text/javascript">
      $(function(){
         CKEDITOR.replace('bo_content',{
            filebrowserUploadUrl: './adm/fileupload.do'
         });
      });
   </script>
   
   <script type="text/javascript">
   function fileCheck() {
      // < input type ="file" id="file" > 을 찾아갑니다. 
      var fileext = document.getElementById('myfile').value;   
      $("#bo_content").removeAttr("#bo_content");

      //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듭니다.
      fileext = fileext.slice(fileext.indexOf(".") + 1).toLowerCase(); 
      
      if(fileext != "jpg" && fileext != "png" &&  fileext != "gif" &&  fileext != "bmp"){    //확장자를 확인합니다.
         alert('이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
         window.location.reload(true);
      }

      
   }

   </script>
   

   
</body>
</html>