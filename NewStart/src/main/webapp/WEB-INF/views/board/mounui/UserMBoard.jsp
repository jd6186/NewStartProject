<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/checkbox.js"></script>
<script type="text/javascript" src="./js/usermounui.js"></script>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div class="container">
<form action="./UserMBoardDel.do" method="post" id="del">
  <h2>문의 게시판</h2>
  
  
  <span style="margin-left: 1300px;">
		<select class="btn btn-default" id="list" name="list" onchange="pageList()">
				<option value="5" >5</option>
				<option value="10" >10</option>
				<option value="15">15</option>
				<option value="20">20</option>
		</select>
</span>
  
  
  
  
  
  <table class="table">
    <thead>
      <tr>
      	<th><input type="checkbox" name='seq' onclick="checkAll(this.checked,this.name)"><th>
        <th>카테고리</th>
        <th>제목</th>
        <th>등록일</th>
        <th>답변 처리 여부</th>
      </tr>
    </thead>
    <tbody id="boardlist">
		<c:forEach items="${dtos}" var="dto" varStatus="idx">
		      <tr>
		      	 <td><input type="checkbox" name="seq" value="${dto.mounui_seq}" onclick="chk(this.name)"></td>
       			 <td>${fn:length(dtos)-idx.index}</td>
       			 <td>${dto.category_title}</td>
        		 <td><a href="./UserMBoardDetail.do?seq=${dto.mounui_seq}">${dto.title}</a></td>
        		 <td>${dto.regdate}</td>
        		 <td>
        		 	<c:choose>
						<c:when test="${dto.replychk eq 'N'}">답변 처리 중</c:when>
						<c:when test="${dto.replychk eq 'Y'}">답변 완료</c:when>
					</c:choose>
        		 </td>
      		</tr>
		</c:forEach>
    </tbody>
  </table>
  
  <div class="center">
		<ul class="pagination">
			<li><a href="#" onclick="pageFirst()">&laquo;</a></li>
			<li><a href="#" onclick = "pagePre(${userMounuiBoardrow.pageNum},${userMounuiBoardrow.pageList})">&lsaquo;</a></li>
				<c:forEach var="i" begin="${userMounuiBoardrow.pageNum}" end="${userMounuiBoardrow.count}" step="1">
					<li><a href="#" onclick="pageIndex(${i})">${i}</a></li>
				</c:forEach>
			<li><a href="#" onclick='pageNext(${userMounuiBoardrow.pageNum},${userMounuiBoardrow.total},${userMounuiBoardrow.listNum},${userMounuiBoardrow.pageList})'>&rsaquo;</a></li>
			<li><a href="#" onclick='pageLast(${userMounuiBoardrow.pageNum},${userMounuiBoardrow.total},${userMounuiBoardrow.listNum},${userMounuiBoardrow.pageList})'>&raquo;</a></li>
		</ul>
	<input type="hidden" name="index" id="index" value="${userMounuiBoardrow.index}">
	<input type="hidden" name="pageNum" id="pageNum" value="${userMounuiBoardrow.pageNum}">
	<input type="hidden" name="listNum" id="listNum" value="${userMounuiBoardrow.listNum}">
	</div>
  
  
  <button type="button" class="btn" onclick="multiDelChk()">삭제</button>
  <button type="button" class="btn" onclick="location.href='Umounuiboard.do'">문의하기</button>
  </form>
</div>

<script type="text/javascript">
		var listNum = document.getElementById('listNum').value;
		var list = document.getElementById('list');
		list.options[listNum/5-1].selected = "selected";
</script>

</body>
</html>