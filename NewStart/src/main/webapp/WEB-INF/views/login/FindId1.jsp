<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./js/login.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div class="container">
  <h2>아이디 찾기</h2>
	<form action="./FindId.do" method="get" id="idfind">
    <div class="form-group">
      <label for="usr">이름:</label>
      <input type="text" class="form-control" id="usr" style="width: 300px;">
    </div>
    <div class="form-group">
      <label for="pwd">핸드폰 번호:</label>
      <input type="text" class="form-control" name='user_phone' style="width: 300px;" onchange="phonechk(this.value)">
	 <div id='phone' style="margin-left: 10px; margin-top: 10px">-는 제외한 번호만 입력해주세요</div>
    </div><br/>
	<input class="btn btn-default" type="button" onclick="idsubmitchk()" value="확인">
  </form>
</div>

</body>
</html>