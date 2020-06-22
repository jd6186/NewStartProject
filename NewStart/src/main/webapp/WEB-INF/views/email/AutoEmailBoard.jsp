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

<div class="container">
  <h2>자동 메일 설정</h2>
  <div class="table-responsive">          
  <table class="table">
    <thead>
      <tr>
        <th>#</th>
        <th>제목</th>
        <th>사용 여부</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="dto" items="${dtos}" varStatus="vs">
      <tr>
        <td>${fn:length(dtos)-vs.index}</td>
        <td><a href="./AutomailBD.do?seq=${dto.category_code}">${dto.email_title}</a></td>
        <td>${dto.use_chk}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <button class="btn" onclick="location.href='./AcheckMailSave.do'">이메일 기록 확인</button>
  </div>
</div>
</body>
</html>