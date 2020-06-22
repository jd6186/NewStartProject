<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
<script type="text/javascript">

function pageList(){
// 	var a = document.getElementById("selectBox").value;
	var a = $("#selectBox option:selected").val();
	alert(a);
	$("#re_star").html("value",a);
}
		

</script>
	<form action="./insertReview.do" method="post" enctype="multipart/form-data">
		<input type="hidden" id="user_seq" name="user_seq" value="${newstart.user_seq}">
		
		re_star : 
		<select id="selectBox" onchange="pageList()">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
		<br>
		<input type="hidden" name="re_star" value="3">
		re_teacher : <input type="text"  name="re_teacher"><br>
		${teacher }<br>
<!-- 		re_title : <input type="text" name="re_title"><br> -->
		re_content : <textarea rows="5" cols="50" name="re_content"></textarea><br>
		<input multiple="multiple" type="file" name="reviewFile" id="reviewFile" >
		<br>
		
		<input type="submit" value="새글 쓰기">
		<input type="reset" value="다시쓰기">
	</form>

</body>
</html>