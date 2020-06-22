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
<div class="panel panel-default">
  <div class="panel-body">휴면회원계정입니다. 이메일 인증을 받으셔야 로그인이 가능합니다</div>
</div>
<button class='btn btn-default' onclick="location.href='loginForm.do'">메인화면</button>
</body>
</html>