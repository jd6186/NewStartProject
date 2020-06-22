
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 메인 화면</title>
<link type="text/css" rel="stylesheet" href="./css/boardList.css">
</head>
<script type="text/javascript" src="./js/boardListGonggo.js"></script>
<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

	<div id="container">
		<br>
		<form action="#" method="post" id="frm" name="frm"
			onsubmit="return chkbox();">
			<div class="panel-group" id="accordion">
				
				<input class="form-control" id="myInput" type="text" placeholder="Search..">
				<table class="table table-bordered">
					<tr>
						<th><input type="checkbox" onclick="checkAll(this.checked)">
						</th>
						<th>썸네일</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>파일유무</th>
						<c:if test="${fn:contains(lists, user=A)}">
							<th>삭제 여부</th>
						</c:if>
					</tr>
					<jsp:useBean id="format" class="com.start.pro.models.gonggo.inputList"
						scope="page" />
					<jsp:setProperty property="pageMaker" name="format" value="${pageMaker}" />
					<jsp:setProperty property="lists" name="format" value="${lists}" />
					<jsp:setProperty property="flists" name="format" value="${flists}" />
					<jsp:setProperty property="users" name="format" value="${users}" />
					<jsp:setProperty property="listTotal" name="format" value="${listTotal}" />
					<jsp:getProperty property="listForm" name="format" />


				</table>
			</div>
		</form>
	</div>
			
 <div>
      <ul class="pagination">
           <c:if test="${pageMaker.prev}">
             <li>
                <a href="a_main.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
             </li>
          </c:if> 
      
          <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
             <li>
                <a href="a_main.do${pageMaker.makeQuery(idx)}">${idx}</a>
             </li>
          </c:forEach>
      
          <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
             <li>
                <a href="a_main.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
             </li>
          </c:if> 
      </ul>
   </div> 
	


</body>
</html>