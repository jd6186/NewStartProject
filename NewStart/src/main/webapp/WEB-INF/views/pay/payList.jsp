<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 내역 페이지</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	function mov(val){
// 		var but = $('input[type=button]');
// 		var amount = $('input[name=amount]').val();
// 		var token = $('input[name=token]').val();
// 		var seq = $('.s').html();
		
		var ret = $('input[name = but]');
// 		ret.onclick()
		alert(ret);
		alert(val);
// 		<td><input onclick="mov('${pls}')" type="button" value="환불" name="but">&nbsp;&nbsp;</td>
		$.ajax({
			url : "./cancel.do",
			type : "post",
			data : {"seqq": val},
			success : function(data){
				alert(data);
				if (data == "false") {
					seq.hide();
				}
			},
			error : function(request, status, error){
				alert("code: " + request.status + "\n message: " + request.responseText + "\n error: " + error);
			}
		});
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
		<div id="first">
<!-- 		<form action="./cancel.do" method="post"> -->
			<table class="table" id="cash">
				<thead>
					<tr>
						<th>NO</th>
						<c:if test='${user.user_grade == "A" }'>
							<th>유저 번호</th>
						</c:if>
						<th>결제 금액</th>
						<th>결제일</th>
						<c:if test='${user.user_grade == "A" }'>
							<th>토큰</th>
							<th>환불여부</th>
							<th>환불</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:set var="num" value="${pageMaker.totalCount - ((pageMaker.cri.page - 1) * 10)}"></c:set>
					<c:forEach var="list" items="${lists}" varStatus="vs">
						<c:set var="pls" value="${list.pay_seq}"/>
						<tr>
							<td>${num}&nbsp;&nbsp;</td>
							<c:if test='${user.user_grade == "A" }'>
								<td>
									<input type="hidden" name="seq" value="${list.pay_seq}">${list.pay_seq}
								</td>
							</c:if>	
<%-- 							<c:choose> --%>
								<c:if test='${user.user_grade == "A" }'>
									<td>
										<input type="hidden" name="ret" value="${vs.current.user_seq}">
										${list.user_seq}&nbsp;&nbsp;
									</td>
								</c:if>
<%-- 								<c:otherwise> --%>
<%-- 									<td>${user.user_seq }&nbsp;&nbsp;</td> --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
							<td><input type="hidden" name="amount" value="${vs.current.pay_amount}">${list.pay_amount }&nbsp;&nbsp;</td>
							<td>${list.pay_date }&nbsp;&nbsp;</td>
							<c:if test='${user.user_grade == "A" }'>
								<td><input type="hidden" name="token" value="${vs.current.pay_token}">${list.pay_token }</td>
								<td>${list.delrefund}&nbsp;&nbsp;</td>
								<c:if test='${list.delrefund == "Y" }'>
									<td><input onclick="mov('${pls}')" type="button" value="환불" name="but">&nbsp;&nbsp;</td>
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
<!-- 	</form>	 -->
		</div><!-- 결제 -->
</body>
</html>