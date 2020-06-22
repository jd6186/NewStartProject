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

<div class="container">
<form action="./ljmailchk.do" method="post">
  <div class="form-group">
    <label for="email">이메일 인증번호:</label>
    <input type="text" class="form-control" id="email" name="key" style="width: 300px;">
    <input type="hidden" name="email" value="${email}">
  </div>
  <button type="submit" class="btn btn-default">확인</button>
</form>
</div>



</body>
</html>