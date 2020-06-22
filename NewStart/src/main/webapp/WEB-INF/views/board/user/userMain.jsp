<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>

<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
<script type="text/javascript">
	function moveDetail(user_seq){
		location.href="./moveDetail.do?user_seq="+user_seq;
	}
	function waringDec(dec_seq){
		location.href="./waringDec.do?dec_seq="+dec_seq;
	}
	function download(user_seq){
		location.href="./download.do?user_seq="+user_seq;
	}
	function teaRespY(user_seq){
		location.href="./teaRespY.do?user_seq="+user_seq;
	}
	function teaRespN(user_seq){
		location.href="./teaRespN.do?user_seq="+user_seq;
	}
	$(document).ready(function(){
		  $("#myInput").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#myTable tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	$(document).ready(function() {
		$(window).scroll(function () {
			// 현재 위치의 높이
			//var curHeight = $(window).height() + $(window).scrollTop();
			var curHeight = $(window).scrollTop();
			// 문서의 높이
			var docHeight = $(document).height();

			// 어느 정도 조건이 만족하면 내용 생성
			if (curHeight > docHeight - 800) {
				$('<h1>Infinity Scroll</h1>').appendTo('body');
			}
		});
	});
</script>
${teaLists }
	<h1>관리자만 사용 가능</h1>
	<div class="container">
	 <ul class="nav nav-tabs nav-justified">
    <li class="active"><a data-toggle="tab" href="#userPageTab">회원 조회 페이지</a></li>
    <li><a data-toggle="tab" href="#teacherRepPageTab">강사 요청 회원 페이지</a></li>
    <li><a data-toggle="tab" href="#declarationPageTab">신고 관리 페이지</a></li>
  </ul>
   <div class="tab-content">
   
    <div id="userPageTab" class="tab-pane fade in active">
      <form action="#" method="get" id="userPage">
	 <input class="form-control" id="myInput" type="text" placeholder="Search..">
		<div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" onclick="checkAll(this.checked)"></th>
						<th>유저 번호</th>
						<th>이메일</th>
						<th>이름</th>
						<th>닉네임</th>
						<th>등급</th>
						<th>타입</th>
						<th>이메일 수신 여부</th>
						<th>강사 인증 여부</th>
						<th>가입일</th>
						
						<th>상세보기</th>
					</tr>
				</thead>
				<tbody id="myTable">
					<c:forEach items="${lists }" var="list" varStatus="vs">
						<tr>
							<td><input type="checkbox" name="seq" value="${list.user_seq}"> </td>
							<td>${list.user_seq }</td>
							<td>${list.user_email }</td>
							<td>${list.user_name }</td>
							<td>${list.user_nickname }</td>
							<td>${list.user_grade }</td>
							<td>${list.user_type }</td>
							<td>${list.user_adchk }</td>
							<td>${list.user_tchk }</td>
							<td>${list.user_regdate }</td>
							<td><input type="button" onclick="moveDetail(${list.user_seq})" value="상세보기"></button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>
    </div>
    
    <div id="teacherRepPageTab" class="tab-pane fade">
     <form action="#" method="get" id="teacherRepPageTab">
	 <input class="form-control" id="myInput" type="text" placeholder="Search..">
		<div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" onclick="checkAll(this.checked)"></th>
						<th>유저 번호</th>
						<th>이메일</th>
						<th>이름</th>
						<th>닉네임</th>
						<th>가입일</th>
						<th>신청일</th>
						
						<th>다운로드</th>
						<th>승인</th>
						<th>미승인</th>
					</tr>
				</thead>
				<tbody id="myTable">
					<c:forEach items="${teaLists }" var="teaList" varStatus="vs">
						<tr>
							<td><input type="checkbox" name="seq" value="${teaList.user_seq}"> </td>
							<td>${teaList.user_seq }</td>
							<td>${teaList.user_email }</td>
							<td>${teaList.user_name }</td>
							<td>${teaList.user_nickname }</td>
							<td>${teaList.user_regdate }</td>
							<td>${teaList.tea_date }</td>
							<td><input type="button" onclick="download(${teaList.user_seq })" value="다운로드"></button></td>
							<td><input type="button" onclick="teaRespY(${teaList.user_seq })" value="승인"></button></td>
							<td><input type="button" onclick="teaRespN(${teaList.user_seq })" value="미승인"></button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</form>
    </div>	
    
    <div id="declarationPageTab" class="tab-pane fade">
        <form action="./waringDec.do" method="post" id="declarationPageTab">
	 <input class="form-control" id="myInput" type="text" placeholder="Search..">
		<div>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" onclick="checkAll(this.checked)"></th>
						<th>신고 번호</th>
						<th>신고 한 사람</th>
						<th>신고 당한 사람</th>
						<th>게시판 코드</th>
						<th>글 번호</th>
						<th>신고 날짜</th>
						<th>처리일자</th>
						<th>경고하기</th>
					</tr>
				</thead>
				<tbody id="myTable">
					<c:forEach items="${decLists }" var="decList" varStatus="vs">
						<tr>
							<td><input type="checkbox" name="seq" value="${decList.dec_seq}"> </td>
							<td>${decList.dec_seq }</td>
							<td>${decList.dec_victim }</td>
							<td>${decList.dec_suspect }</td>
							<td>${decList.board_code }</td>
							<td>${decList.board_seq }</td>
							<td>${decList.dec_date }</td>
							<td>${decList.dec_prodate }</td>
							<td><input type="button" onclick="waringDec(${decList.dec_seq})" value="경고하기"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</form>
    </div>
   
  </div>
	
	
	
	
	</div>
</body>
</html>