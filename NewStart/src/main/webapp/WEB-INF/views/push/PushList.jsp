<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div class="container mt-3" style="margin-top: 100px;">
  <h2>푸시 알림 발송 내역</h2>
  <input class="form-control" id="myInput" type="text" placeholder="Search..">
  <br>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>글번호</th>
        <th>자동/수동</th>
        <th>발송 분류</th>
        <th>발송 제목</th>
        <th>발송 내용</th>
        <th>발송 시간</th>
      </tr>
    </thead>
    <tbody id="myTable">
	<c:forEach var="list" items="${lists}" varStatus="vs">
		<tr>
			<c:if test="${vs.last eq true}">
			<td id="lastCount">${vs.count}</td>
			</c:if>
			<c:if test="${vs.last eq false}">
			<td class = "count">${vs.count}</td>
			</c:if>
			<td>${list.push_am}</td>
			<td>${list.push_accident}</td>
			<td>${list.push_title}</td>
			<td>${list.push_content}</td>
			<td>${list.push_date}</td>
		</tr>
	</c:forEach>
    </tbody>
  </table>
				<input onclick="left_Click()" id='left' class="btn btn-success" value="&lt" readonly="readonly">		
				<input id='first' class="btn btn-success" value="1" readonly="readonly">		
				<input id='second' class="btn btn-success" value="2" readonly="readonly">	
				<input id='third' class="btn btn-success" value="3" readonly="readonly">	
				<input id='fourth' class="btn btn-success" value="4" readonly="readonly">			
				<input id='fiveth' class="btn btn-success" value="5" readonly="readonly"> 
				<input onclick="right_Click()" id='right' class="btn btn-success" value="&gt"> 
				<br/><br/>
	<a href="./pushpage.do" type="button" class="btn btn-success">알림 발송</a>  
	<a href="./pushpage.do" type="button" class="btn btn-info">알림 삭제</a>  
</div>

<script type="text/javascript">
// 페이징 버튼 로직 짜주는 기계
var first = document.getElementById("first").value;
var second = document.getElementById("second").value;
var third = document.getElementById("third").value;
var fourth = document.getElementById("fourth").value;
var fiveth = document.getElementById("fiveth").value;
var lastCount = document.getElementById("lastCount").value;

function right_Click(){
alert("시작합니다.");
$.ajax({
	url: "./push_pageCal_R.do",
	data: {"first":first, "second":second, "third":third, "fourth":fourth, "fiveth":fiveth, "lastCount":lastCount},
	dataType: 'json',
	success: function(map){
		alert('버튼값이 변경됩니다.');
		first = map.first;
		second = map.second;
		third = map.third;
		fourth = map.fourth;
		fiveth = map.fiveth;
	},
	error: function(){
		alert("계산 시 잘못된 접근입니다.");
	}
});
}

function left_Click(){
	alert("시작합니다.");
$.ajax({
	url: "./push_pageCal_L.do",
	type:'get',
	data: {"first":first,"second":second,"third":third,"fourth":fourth,"fiveth":fiveth, "lastCount":lastCount},
	dataType: 'json',
	success: function(map){
		alert('버튼값이 변경됩니다.');
		first = map.first;
		second = map.second;
		third = map.third;
		fourth = map.fourth;
		fiveth = map.fiveth;
		if (first == 'X'){
			
		}
		alert("first"+first+"second"+second+"third"+third+"fourth"+fourth+"fiveth"+fiveth
				);
	},
	error: function(){
		alert("계산 시 잘못된 접근입니다.");
	}
});
}
</script>


<script>
// 검색 필터 설정하기
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