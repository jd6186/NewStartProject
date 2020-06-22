
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
<script type="text/javascript" src="./js/boardListBidding.js"></script>
<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

	<div id="container">
		<br>
		<form action="#" method="get" id="frm" name="frm"
			onsubmit="return chkbox();">
			<div class="panel-group" id="accordion">
			
  				<input class="form-control" id="myInput" type="text" placeholder="Search..">
				<table class="table table-bordered">
				<tbody id='myTable'>
					<tr>
						<th><input type="checkbox" onclick="checkAll(this.checked)">
						</th>
						<th>썸네일</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>파일유무</th>
					</tr>
					<jsp:useBean id="format" class="com.start.pro.models.gonggo.inputListT"
						scope="page" />
					<jsp:setProperty property="pageMaker" name="format" value="${pageMaker}" />
					<jsp:setProperty property="lists" name="format" value="${lists}" />
					<jsp:setProperty property="flists" name="format" value="${flists}" />
					<jsp:setProperty property="users" name="format" value="${users}" />
					<jsp:setProperty property="listTotal" name="format" value="${listTotal}" />
					<jsp:getProperty property="listForm" name="format" />
					</tbody>
					
				</table>
			</div>
		</form>
	</div>
	
	<!-- TODO -->
	<!-- 만약 강사가 아니면 글작성이 불가능하게 controller로부터 값을 받아오는 과정이 있어야한다. -->
	<c:if test="${users.user_grade eq 'T'}">
		<input class='btn btn-primary btn-center' type='button' id="T" value='글 작성' onclick='GonggoCreate()'>
	</c:if>
			
	 
	  
	
 <div>
      <ul class="pagination">
           <c:if test="${pageMaker.prev}">
             <li>
                <a href="t_main.do${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
             </li>
          </c:if> 
      
          <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
             <li>
                <a href="t_main.do${pageMaker.makeQuery(idx)}">${idx}</a>
             </li>
          </c:forEach>
      
          <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
             <li>
                <a href="t_main.do${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
             </li>
          </c:if> 
      </ul>
   </div> 
	
	 
	 
	 
<script type="text/javascript">
// 크롬 뚫어주는 친구
(function(){
    var url = document.getElementById("iframeId").src;            
    var result = encodeURI(decodeURI(url));    
    document.getElementById("iframeId").src = result;
}());
</script>
	 
<script>
// 필터 만들어주기
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
</body>
</html>