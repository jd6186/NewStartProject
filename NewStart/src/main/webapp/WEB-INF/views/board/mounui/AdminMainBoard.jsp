<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/checkbox.js"></script>
<script type="text/javascript" src="./js/mounui.js"></script>
<body>

<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div style="margin-left: 280px;">
	검색 필터 :
	<div id="searchfilter">
		카테고리 : <!-- jstl써서 카테고리만큼 추가  -->
		<input type="checkbox" name="category_title" value="A" onclick="checkAll(this.checked, this.name)"> 전체 
		<c:forEach items="${title}" var="title" varStatus="vs">
			<input type="checkbox" name="category_title" value="${title}" onclick="chk(this.name)"> ${title}
		</c:forEach>
		<br>
		회원 등급 : <input type="checkbox" name="user_grade" value="A" onclick="checkAll(this.checked, this.name)"> 전체
				 <input type="checkbox" name="user_grade" value="M" onclick="chk(this.name)"> 멘티
				 <input type="checkbox" name="user_grade" value="T"	onclick="chk(this.name)"> 강사 <br>
		처리 여부 : <input type="checkbox" name="replychk" value="A" onclick="checkAll(this.checked, this.name)"> 전체
		 <input type="checkbox" name="replychk" value="Y" onclick="chk(this.name)"> 답변 완료
		  <input type="checkbox" name="replychk" value="N" onclick="chk(this.name)"> 답변 대기 <br>
		삭제 여부 : <input type="checkbox" name="delchk" value="A" onclick="checkAll(this.checked, this.name)"> 전체
		 <input type="checkbox" name="delchk" value="N" onclick="chk(this.name)"> 존재
		  <input type="checkbox" name="delchk" value="Y" onclick="chk(this.name)"> 삭제 <br>
		   날짜 검색 : <input type="date" name="firstDate"> ~ <input type="date" name="lastDate"> 
		<button onclick="setsearchFilter()">검색</button>
</div>
		 <input type="hidden" id="category_title_result">
		 <input type="hidden" id="user_grade_result">
		 <input type="hidden" id="replychk_result">
		 <input type="hidden" id="delchk_result">
		 <input type="hidden" id="firstDate_result">
		 <input type="hidden" id="lastDate_result">
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
<form action="./AdminMBoardDel.do" method="get" id="del">
  <h2>문의 조회 게시판</h2>
  <table class="table">
    <thead>
      <tr>
      	<th><input type="checkbox" name='seq' onclick="checkAll(this.checked,this.name)"><th>
        <th>카테고리</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>답변 처리 여부</th>
        <th>글 삭제 여부</th>
      </tr>
    </thead>
    <tbody id="boardlist">
		<c:forEach items="${dtos}" var="dto" varStatus="idx">
		      <tr>
		      	 <td><input type="checkbox" name="seq" value="${dto.mounui_seq}" onclick="chk(this.name)"></td>
       			 <td>${fn:length(dtos)-idx.index}</td>
       			 <td>${dto.category_title}</td>
        		 <td><a href="./AdminMBoardDetail.do?seq=${dto.mounui_seq}">${dto.title}</a></td>
        		 <td>${dto.category_seq}&lt;${dto.board_code}&gt;</td>
        		 <td>${dto.regdate}</td>
        		 <td>
        		 	<c:choose>
						<c:when test="${dto.replychk eq 'N'}">답변 처리 중</c:when>
						<c:when test="${dto.replychk eq 'Y'}">답변 완료</c:when>
					</c:choose>
        		 </td>
        		 <td>
        		 	<c:choose>
						<c:when test="${dto.delchk eq 'N'}">존재</c:when>
						<c:when test="${dto.delchk eq 'Y'}">삭제</c:when>
					</c:choose>
        		 </td>
      		</tr>
		</c:forEach>
    </tbody>
  </table>
  <div class="center">
		<ul class="pagination">
			<li><a href="#" onclick="pageFirst()">&laquo;</a></li>
			<li><a href="#" onclick = "pagePre(${adminMounuiBoardrow.pageNum},${adminMounuiBoardrow.pageList})">&lsaquo;</a></li>
				<c:forEach var="i" begin="${adminMounuiBoardrow.pageNum}" end="${adminMounuiBoardrow.count}" step="1">
					<li><a href="#" onclick="pageIndex(${i})">${i}</a></li>
				</c:forEach>
			<li><a href="#" onclick='pageNext(${adminMounuiBoardrow.pageNum},${adminMounuiBoardrow.total},${adminMounuiBoardrow.listNum},${adminMounuiBoardrow.pageList})'>&rsaquo;</a></li>
			<li><a href="#" onclick='pageLast(${adminMounuiBoardrow.pageNum},${adminMounuiBoardrow.total},${adminMounuiBoardrow.listNum},${adminMounuiBoardrow.pageList})'>&raquo;</a></li>
		</ul>
	<input type="hidden" name="index" id="index" value="${adminMounuiBoardrow.index}">
	<input type="hidden" name="pageNum" id="pageNum" value="${adminMounuiBoardrow.pageNum}">
	<input type="hidden" name="listNum" id="listNum" value="${adminMounuiBoardrow.listNum}">
	</div>
  <button type="button" onclick="multiDelChk()">삭제</button>
  </form>
</div>
	<script type="text/javascript">
		var listNum = document.getElementById('listNum').value;
		var list = document.getElementById('list');
		list.options[listNum/5-1].selected = "selected";
	</script>

</body>
</html>