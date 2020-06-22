<%@page import="com.start.pro.dto.DTO_Gonggi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
</head>
<script type="text/javascript">
	var isThrow = true;

	window.onbeforeunload = function(e){
		if (isThrow) return "작성하신 글이 저장되지 않습니다.";
	}
	
	function validateForm(){
		isThrow = false;
		//var title = $('form[name=title]').val();
		//var title = document.getElementsByName("title")[0].value;
		var formVal = document.forms[0]["title"].value;
		var content = document.getElementsByName("content")[0];
		//alert(formVal);
		if (formVal == "") {
			isThrow = true;
			alert("제목은 필수 값 입니다.");
			return false;
		} 
		if (content.textContent.indexOf("<") != -1) {
			var temp = content.replace(/</gim, '&lt;');
			temp = temp.replace(/>/gim, '&gt;');
			temp = temp.replace(/(?:\r\n|\r|\n)/,'<br>');
			// g 모든 패턴 검색, i 대소문자 , m 여러줄
			content.value = temp;
		}
// 		return false;
	}
</script>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<form action="./modifyForm.do" method="post" onsubmit="return validateForm()">
	<div>
		<input type="hidden" id="seq" name="seq" value="${dto.gi_seq}">
		<input type="hidden" name="category" id="category" value="${dto.gi_category}">
	<table class="table">
		
		<tr>
			<th>제목</th>
			<td><input class="form-control" id="title" type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea class="form-control" rows="5" cols="50" name="content"></textarea></td>
		</tr>
		<tr><th>날짜</th><td>${dto.gi_regdate}</td></tr>
	<tr>
		<td colspan="2" style="text-align: center; padding: 10px">
			<input class="btn btn-success" type="submit" value="수정완료">
			<input class="btn btn-danger" type="reset" value="초기화">
		</td>
	</tr>
	</table>
	</div>
	</form>
</body>
</html>