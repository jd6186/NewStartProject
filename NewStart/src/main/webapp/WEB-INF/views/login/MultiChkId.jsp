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
<form action="./user_email/MultiChk.do" method="POST">
	아이디 중복검사 :
	<input type="text" name="val">
	<input type="submit">
</form>

<form action="./user_nickname/MultiChk.do" method="POST">
	닉네임 중복검사 :
	<input type="text" name="val">
	<input type="submit">
</form>
</body>
</html>