<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 등록 페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<form action="./writeForm.do" method="post">
	<table class="table">
		<tr>
			<th>카테고리</th>
			<td>
				<select id="category" name="category">
					<option value="Y">중요공지</option>
					<option value="N" selected="selected">일반공지</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input class="form-control"  type="text" id="title" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea class="form-control" rows="5" cols="50" id="content" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="3"><button onclick="save()">글 등록</button></td>
		</tr>
	</table>
	</form>
	<script type="text/javascript">
		function save(){
			document.forms[0].submit();
		}
	</script>
	
</body>
</html>