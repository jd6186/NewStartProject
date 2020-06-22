<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<script type="text/javascript" src="./js/email.js"></script>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>



<div class="container">
  <form action="./AManyMailSend.do" method="post">
    <div class="form-group">
      <label for="usr">제목:</label>
      <input type="text" class="form-control" id="usr" name="email_title" >
    </div>
    <div class="form-group">
      <label for="pwd">내용:</label>
      <textarea class="form-control" id="pwd" name="email_content" rows="30" cols="50" ></textarea>
    </div>
    <div style="margin: auto;">
	<p>도움말 : 사용자의 이름은  \${name}, 닉네임은 \${nickname}로 표기하시면 자동으로 설정됩니다.</p>
	</div>
      수신자 필터 :<br>
      회원 등급 :  <input type="checkbox" name="Allfilter" value="'A'" onclick="checkAll(this.checked)"> 전체
			<input type="checkbox"  name="filter" value="'M'" onclick="chk()"> 멘티
 			<input type="checkbox"  name="filter" value="'T'" onclick="chk()"> 강사 <br>

  			<input type="hidden" name ="user_email">
  			<input type="hidden" name ="filechk" value="N">
  <div id="sendCnt">총 발송 건 수 : 0통</div>
      <input type="button" class="btn" value="발송" onclick="gosubmit_email()">
      <input type="button" class="btn" value="목록" onclick="location.href='AcheckMailSave.do'">
  </form>
</div>

<script type="text/javascript">
		$(function(){
			CKEDITOR.replace('pwd',{
				filebrowserUploadUrl: './adm/fileupload.do'
			});
		});
		
</script>

</body>
</html>