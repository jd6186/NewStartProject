<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>공지 게시판</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./../../css/gonggi.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myDIV *").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<input class="form-control" id="myInput" type="text" placeholder="Search..">
	<table class="table">
		<thead>
			<tr>
				<th>NO</th>
				<th>제목</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody id="myDIV">
			<c:set var="num" value="${pageMaker.totalCount - ((pageMaker.cri.page - 1) * 10)}"></c:set>
			<c:forEach var="dto" items="${lists}" varStatus="vs">
				<tr>
					<td> 
						<c:if test='${dto.gi_category == "Y"}'>
							<img style="height: 16px; width: 16px;" src="./img/important.jpg">
						</c:if>
						<c:if test='${dto.gi_category == "N"}'>
<%-- 							${fn:length(lists) - vs.index} --%>
							${num}
						</c:if>
					</td>
					<td>
						<a href="./gonggiOneSel.do?seq=${dto.gi_seq}&category=${dto.gi_category}">
							${dto.gi_title}
						</a>
					</td>
					<td>${dto.gi_regdate}</td>
				</tr>
				<c:set var="num" value="${num-1}"></c:set>
			</c:forEach>
		</tbody>
	</table>
	<c:if test='${user.user_grade == "A" }'>
		<div>
			<input class="btn btn-success" type="button" value="세글작성" onclick="writeForm()">
		</div>
	</c:if>
	<script type="text/javascript">
		function writeForm(){
			location.href="./write.do";
		}
	</script>
	<div>
		<ul class="pagination">
		     <c:if test="${pageMaker.prev}">
		    	<li>
		    		<a href="gongGiList.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
		    	</li>
		    </c:if> 
		
		    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
		    	<li>
		    		<a href="gongGiList.do${pageMaker.makeQuery(idx)}">${idx}</a>
		    	</li>
		    </c:forEach>
		
		    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		    	<li>
		    		<a href="gongGiList.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
		    	</li>
		    </c:if> 
		</ul>
	</div>
</body>
</html>