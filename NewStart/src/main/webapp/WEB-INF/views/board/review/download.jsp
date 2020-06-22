<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<div>

	saveFileName=${saveFileName}<br>
	fileName=${filename}
	<h1>파일 다운로드 테스트</h1>

	파일첨부:<a href="download.do?saveFileName=${saveFileName}&fileName=${filename}">${saveFileName}</a><br>

</div>



</body>
</html>
