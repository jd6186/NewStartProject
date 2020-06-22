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

	<%-- ${lists } --%>
	<div class="container">
		<form action="#" method="get" id="userPage">
			<input class="form-control" id="myInput" type="text"
				placeholder="Search..">
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
							<th>인증 요청 날짜</th>
							<th>인증 허가 날짜</th>
							

							<th>상세보기</th>
						</tr>
					</thead>
					<tbody id="myTable">
						<c:forEach items="${lists }" var="list" varStatus="vs">
							<tr>
								<td><input type="checkbox" name="seq"
									value="${list.user_seq}"></td>
								<td>${list.user_seq }</td>
								<td>${list.user_email }</td>
								<td>${list.user_name }</td>
								<td>${list.user_nickname }</td>
								<td>${list.user_regdate }</td>
								<td>${list.tea_date }</td>
								<td>${list.tea_handate }</td>
								<td><button onclick="moveDetail(${list.user_seq})">상세보기</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<input type="button" value="문자보내기" onclick=""> <input
					type="button" value="강사 요청 유저 보기" onclick=""> <input
					type="button" value="" onclick="">
			</div>
		</form>
	</div>
</body>
</html>