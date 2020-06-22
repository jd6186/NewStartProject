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
<script type="text/javascript">

//---------------------------------------관리자 메일 상세보기----------------------------
function multiDelChk(){
	if(confirm('정말로 삭제하시겠습니까?')){
		location.href='./AmailSaveDel.do?seq=${dto.email_seq}';
	}
}

</script>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div class="container">
  <h2>Form control: input</h2>
  <p>The form below contains two input elements; one of type text and one of type password:</p>
    <div class="form-group">
      <label for="usr">수신자 :</label>
      <input type="text" class="form-control" id="wusr" name="email_title" readonly="readonly" 
      	value="${dto.user_email}">
    </div>
    <div class="form-group">
      <label for="usr">제목:</label>
      <input type="text" class="form-control" id="rusr" name="email_title" readonly="readonly" value="${dto.email_title}">
    </div>
    <div class="form-group">
      <label for="pwd">내용:</label>
      <textarea class="form-control" id="pwd" name="email_content" readonly="readonly" rows="30" cols="50" style="resize: none">${dto.email_content}</textarea>

	<button type="button" class="btn" onclick="location.href='./AcheckMailSave.do'">목록</button>
<c:if test="${dto.successchk eq 'F'}">
	<button onclick="location.href='./Aresend.do?seq=${dto.email_seq}'">재전송</button>
</c:if>
	<button type="button" class="btn" onclick="multiDelChk()">삭제</button>
    </div>
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