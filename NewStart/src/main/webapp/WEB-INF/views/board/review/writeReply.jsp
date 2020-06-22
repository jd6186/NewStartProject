<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
받은 별점 : ${uDto.re_star }<br>
제목 : ${uDto.re_title }<br>
내용 : ${uDto.re_content }<br>

<form action="./insertReply.do" method="get" >
		<input type="hidden" id="re_seq" name="re_seq" value="${uDto.re_seq}">
		
		re_star : <input type="text"  name="re_star"><br>
<!-- 		re_title : <input type="text" name="re_title"><br> -->
		re_content : <textarea rows="5" cols="50" name="re_content"></textarea><br>
		
		<input type="submit" value="새글 쓰기">
		<input type="reset" value="다시쓰기">
	</form>
</body>
</html>