<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/email.js"></script>
<script type="text/javascript" src="./js/checkbox.js"></script>
<body>

<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

	<jsp:useBean id="emailparser" class="com.start.pro.util.Util_JSON"
		scope="page" />
	<jsp:setProperty property="key" name="emailparser" value="user_email" />





	<form action="./AmailSaveDel.do" method="post" id="frm">
	<div style="margin-left: 280px;">
	유저 타입 :
	<div id="searchfilter">
		회원 등급 : <input type="checkbox" name="user_grade" value="'A'" onclick="checkAll(this.checked, this.name)"> 전체
				 <input type="checkbox" name="user_grade" value="'M'" onclick="chk(this.name)"> 멘티
				 <input type="checkbox" name="user_grade" value="'T'"	onclick="chk(this.name)"> 강사 <br>
		상태 여부 : <input type="checkbox" name="successchk" value="A" onclick="checkAll(this.checked, this.name)"> 전체
		 <input type="checkbox" name="successchk" value="Y" onclick="chk(this.name)"> 성공
		  <input type="checkbox" name="successchk" value="S" onclick="chk(this.name)"> 대기 
		  <input type="checkbox" name="successchk" value="F" onclick="chk(this.name)"> 실패 <br>
		   날짜 검색 : <input type="date" name="firstDate"> ~ <input type="date" name="lastDate"> 
		  <input type="hidden" id="user_grade_result">
		  <input type="hidden" id="successchk_result">
		  <input type="hidden" id="firstDate_result">
		  <input type="hidden" id="lastDate_result">
		<button type="button" onclick="setSearchFilter()">검색</button>
	</div>
	</div>

	<span style="margin-left: 1300px; ">
		<select class="btn btn-default" id="list" name="list" onchange="pageList()">
				<option value="5" >5</option>
				<option value="10" >10</option>
				<option value="15">15</option>
				<option value="20">20</option>
		</select>
	</span>


		
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th><input type="checkbox" name='seq' onclick="checkAll(this.checked,this.name)"><th>
					<th>카테고리</th>
					<th>제목</th>
					<th>상태</th>
					<th>수신자</th>
					<th>상태발송일</th>
				</tr>
			</thead>
			<tbody id='tbody'>
				<jsp:useBean id="format" scope="page" class="com.start.pro.bean.Email_InputList"/>
				<jsp:setProperty property="lists" name="format" value="${dtos}"/>
				<jsp:getProperty property="listForm" name="format"/>
			</tbody>
		</table>
			<div class="center">
		<ul class="pagination">
			<li><a href="#" onclick="pageFirst()">&laquo;</a></li>
			<li><a href="#" onclick = "pagePre(${row.pageNum},${row.pageList})">&lsaquo;</a></li>
				<c:forEach var="i" begin="${row.pageNum}" end="${row.count}" step="1">
					<li><a href="#" onclick="pageIndex(${i})">${i}</a></li>
				</c:forEach>
			<li><a href="#" onclick='pageNext(${row.pageNum},${row.total},${row.listNum},${row.pageList})'>&rsaquo;</a></li>
			<li><a href="#" onclick='pageLast(${row.pageNum},${row.total},${row.listNum},${row.pageList})'>&raquo;</a></li>
		</ul>
	<input type="hidden" name="index" id="index" value="${row.index}">
	<input type="hidden" name="pageNum" id="pageNum" value="${row.pageNum}">
	<input type="hidden" name="listNum" id="listNum" value="${row.listNum}">
	</div>
		<button type="button" class="btn" onclick="multiDelChk()">삭제</button>
		<button type="button" class="btn" onclick="location.href='./AmanymailForm.do'">메일 보내기</button>
		<button type="button" class="btn" onclick="location.href='./AutomailB.do'">자동 메일 설정</button>
	</div>
		</form>
		
	<script type="text/javascript">
		var listNum = document.getElementById('listNum').value;
		var list = document.getElementById('list');
		list.options[listNum/5-1].selected = "selected";
	</script>
	

</body>
</html>