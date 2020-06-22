<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<form action="./modifyReview.do" method="get" >
		<input type="hidden" id="re_seq" name="re_seq" value="${dto.re_seq}"><br>
		
		re_title : <input type="text" name="re_title" value="${dto.re_title} "><br>
		re_content :<textarea rows="5" cols="50" name="re_content">${dto.re_content }</textarea> <br>
		re_teacher : <input type="text"  name="re_teacher" value="${dto.re_teacher }"><br>
		re_star : <input type="text"  name="re_star" value="${dto.re_star }"><br>
		
		<input type="submit" value="수정하기">
		<input type="reset" value="다시쓰기">
	</form>
</body>
</html>