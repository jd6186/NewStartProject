<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
<h1>aaa</h1>${newstart }
	<h1>review Main</h1>
	<form action="./writeReview.do" method="get" id="frm">
		<div>
			<table class="table">
				<thead>
					<tr>
						<th>글번호</th>
						<th>유저 번호</th>
						<th>내용</th>
						<th>별점</th>
						<th>해당 강사</th>
						<th>작성일</th>
						<c:if test="${newstart.user_grade eq 'A' }">
							<th>삭제여부</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${pageMaker.totalCount - ((pageMaker.cri.page - 1) * 10)}"></c:set>
					<c:forEach var="list" items="${lists}" varStatus="vs">
						<tr>
						<c:if test="${list.re_delete ne 'Y' }">
							<td>${num}&nbsp;&nbsp;</td>
							<td>${list.user_seq }&nbsp;&nbsp;</td>
							<td><a href="./reviewDetail.do?re_seq=${list.re_seq}">
							<c:if test="${list.re_teacher eq 0}">&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
							${list.re_content }&nbsp;&nbsp;
							</a></td>
							<td>${list.re_star }&nbsp;&nbsp;</td>
							<td>${list.re_teacher }&nbsp;&nbsp;</td>
							<td>${list.re_regdate }&nbsp;&nbsp;</td>
							<c:if test="${newstart.user_grade eq 'A' }">
							<td>${list.re_delete }</td>
							</c:if>
						</c:if>
						</tr>
						<c:set var="num" value="${num-1}"></c:set>
					</c:forEach>
					
				</tbody>
			</table>
			<div>
		      <ul class="pagination">
		           <c:if test="${pageMaker.prev}">
		             <li>
		                <a href="reviewMain.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
		             </li>
		          </c:if> 
		      
		          <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
		             <li>
		                <a href="reviewMain.do${pageMaker.makeQuery(idx)}">${idx}</a>
		             </li>
		          </c:forEach>
		      
		          <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		             <li>
		                <a href="reviewMain.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
		             </li>
		          </c:if> 
		      </ul>
		   </div>
		</div>
		<input type="submit" class="btn btn-success" value="새글 쓰기">
	</form>
</body>
</html>























