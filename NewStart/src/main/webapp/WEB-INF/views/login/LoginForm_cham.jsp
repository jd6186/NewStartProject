<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script type="text/javascript" src="./js/login.js"></script>
<script type="text/javascript">
function enterkey() {
    if (window.event.keyCode == 13) {
    	ccchk();
    }
}




</script>
</head>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div class="container">
  <h2>로그인</h2>
  <form action="./logingo.do" method="post" id='login'>
    <div class="form-group">
      <label for="email">아이디:</label>
     <input type="text" class="form-control" name="username" value="${id}" onkeyup="enterkey();">
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호:</label>
     <input type="password" class="form-control" name="password" value="${password}" onkeyup="enterkey();">
    </div>
    <div class="checkbox">
      <label><input id="remember_me" name ="remember_me" type = "checkbox"/> 자동 로그인</label>
    </div>
    <button type="button" class="btn btn-default" onclick="ccchk()">로그인</button>
     <c:if test="${not empty key}"> 
		<br>
	<img  src="https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=${key}">
		<br>
	<input type="hidden" name="key" value="${key}">
	<label for="key">입력 :</label> 
	<input type="text" class="form-control" name="chk">
</c:if> 
  </form>
  <div style="color:red;">${error}</div>
  <div style="margin-top: 50px;">
<label for="signUp" style="margin-right: 10px;"><a href="./singUpform1.do">회원가입</a> </label> 
<label for="findId" style="margin-right: 10px;"><a href="./goFId.do">아이디찾기</a></label>
<label for="findPw" style="margin-right: 10px;"><a href="./goFPW.do">비밀번호 찾기</a></label>
</div>
</div>





</body>
</html>