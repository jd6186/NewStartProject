<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 상세 조회</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<table class="table">
		<tr><th>제목</th><td>${dto.gi_title}</td></tr>
		<tr><th>내용</th><td>${dto.gi_content}</td></tr>
		<tr><th>날짜</th><td>${dto.gi_regdate}</td></tr>
	</table>
	
	<c:if test='${user.user_grade == "A" }'>
		<input class="btn btn-info" type="button" onclick="upd()" id="upd" value="수정">
		<input class="btn btn-success" type="button" onclick="insert()" id="modifyForm" value="카테고리바꾸기">
		<input class="btn btn-danger" type="button" onclick="deleteGi()" id="deleteBoard" value="삭제">
	</c:if>
	
	<script type="text/javascript">
		function upd(){
			location.href='upa.do?seq=${dto.gi_seq}&category=${dto.gi_category}&title=${dto.gi_title}&content=${dto.gi_content}&regdate=${dto.gi_regdate}';
		}
	
		function insert(){
			location.href='modify.do?seq=${dto.gi_seq}&category=${dto.gi_category}&title=${dto.gi_title}&content=${dto.gi_content}&regdate=${dto.gi_regdate}';
		}
		
		function deleteGi(){
			location.href='delete.do?seq=${dto.gi_seq}';
		}
				
		
	</script>
	
</body>
</html>