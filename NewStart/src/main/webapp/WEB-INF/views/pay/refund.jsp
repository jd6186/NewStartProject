<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불 내역 페이지</title>
</head>
<body>
<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div id="second"> 
			<table class="table" id="ref">
				<thead>
					<tr>
						<th>NO</th>
						<th>유저 번호</th>
						<th>환불일</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${pageMaker1.totalCount - ((pageMaker1.cri.page - 1) * 10)}"></c:set>
					<c:forEach var="list" items="${listss}" varStatus="vs">
						<tr>
							<td><input type="hidden" name="seq" value="${list.refund_seq }">${num}&nbsp;&nbsp;</td>
							<c:choose>
								<c:when test='${user.user_grade == "A" }'>
									<td>${list.user_seq}&nbsp;&nbsp;</td>
								</c:when>
								<c:otherwise>
									<td>${user.user_seq }&nbsp;&nbsp;</td>
								</c:otherwise>
							</c:choose>
							<td>${list.refund_date }&nbsp;&nbsp;</td>
						</tr>
						<c:set var="num" value="${num-1}"></c:set>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<ul class="pagination">
				     <c:if test="${pageMaker1.prev}">
				    	<li>
				    		<a href="refList.do${pageMaker1.makeQuery(pageMaker1.startPage - 1)}">이전</a>
				    	</li>
				    </c:if> 
				
				    <c:forEach begin="${pageMaker1.startPage}" end="${pageMaker1.endPage}" var="idx">
				    	<li>
				    		<a href="refList.do${pageMaker1.makeQuery(idx)}">${idx}</a>
				    	</li>
				    </c:forEach>
				
				    <c:if test="${pageMaker1.next && pageMaker1.endPage > 0}">
				    	<li>
				    		<a href="refList.do${pageMaker1.makeQuery(pageMaker1.endPage + 1)}">다음</a>
				    	</li>
				    </c:if> 
				</ul>
			</div>
		</div>
</body>
</html>