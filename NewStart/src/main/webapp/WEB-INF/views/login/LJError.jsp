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
  <div class="panel-body">만료된 인증입니다. 다시 시도해주세요</div>
</div>
<button class='btn btn-default' onclick="location.href='loginForm.do'">메인화면</button>
</body>
</html>