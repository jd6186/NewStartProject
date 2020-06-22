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
<div>
	<h1>멘티들은 강사공고가 메인으로 뜨게 지정</h1>
	<a href="./t_main.do">강사 공고 메인페이지 </a>
</div>

<div>
	<h1>강사들은 멘티 공고가 메인으로 뜨게 지정 페이지 접속</h1>
	<a href="./m_main.do">메인페이지 접속</a>
</div>
<div>
	<h1>관리자는 전체가 다보이게 지정 관련 페이지 접속</h1>
	<a href="./a_main.do">메인페이지 접속</a>
</div>
<div>
	<h1>푸시알림 작성페이지 페이지 접속</h1>
	<a href="./pushpage.do">메인페이지 접속</a>
</div>
</body>
</html>