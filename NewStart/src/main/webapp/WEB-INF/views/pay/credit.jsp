<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>크레딧 페이지</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<form action="./credit.do" method="post">
		<div id="first">
		<table class="table" id="cash">
			<thead>
				<tr>
					<th>NO</th>
					<th>유저 번호</th>
					<th>결제 크레딧</th>
					<th>남은 크레딧</th>
					<th>결제일</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="num" value="${pageMaker.totalCount - ((pageMaker.cri.page - 1) * 10)}"></c:set>
				<c:forEach var="list" items="${lists}" varStatus="vs">
					<tr>
						<td><input type="hidden" name="seq" value="${list.credit_seq }">${num}&nbsp;&nbsp;</td>
						<c:choose>
							<c:when test='${user.user_grade == "A" }'>
								<td>
									${list.user_seq}&nbsp;&nbsp;
									<input type="hidden" name="ret" value="${list.user_seq}">
								</td>
							</c:when>
							<c:otherwise>
								<td>${user.user_seq }&nbsp;&nbsp;</td>
							</c:otherwise>
						</c:choose>
						<td>${list.milg_credit }&nbsp;&nbsp;</td>
						<td>${list.remain_credit }&nbsp;&nbsp;</td>
						<td>${list.credit_date }&nbsp;&nbsp;</td>
					</tr>
					<c:set var="num" value="${num-1}"></c:set>
				</c:forEach>
				
			</tbody>
		</table>
		<div>
			<ul class="pagination">
			     <c:if test="${pageMaker.prev}">
			    	<li>
			    		<a href="payList.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
			    	</li>
			    </c:if> 
			
			    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			    	<li>
			    		<a href="payList.do${pageMaker.makeQuery(idx)}">${idx}</a>
			    	</li>
			    </c:forEach>
			
			    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			    	<li>
			    		<a href="payList.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
			    	</li>
			    </c:if> 
			</ul>
		</div>
		</div>
	</form>
		
</body>
</html>