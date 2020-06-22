<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공 페이지</title>
</head>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
로그인 성공!
${dto}

<a href="./logout.do">로그아웃</a>
</body>
</html>