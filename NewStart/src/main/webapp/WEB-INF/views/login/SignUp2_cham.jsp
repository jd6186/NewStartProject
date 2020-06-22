<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.form-group{
	margin: 20px;
}

</style>
</head>
<script type="text/javascript" src="./js/login.js"></script>
<body>

<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>


<div class="container">
  <h2>회원가입</h2>
  <form class="form-inline" id="signUp" action="./singUpSc.do" method="post">
    <div class="form-group">
      <label for="email">아이디:</label>
      <input placeholder="Enter email"  class="form-control" type="text" name="user_email">
      <button type="button" class="btn btn-default" onclick="idchk(user_email)">중복확인</button>
    </div><br/>
    <div class="form-group">
      <label for="pwd">닉네임:</label>
	<input type="text" class="form-control"  name="user_nickname"  placeholder="Enter nickName">
	<button type="button" class="btn btn-default" onclick="nickchk(user_nickname)">중복확인</button>
    </div><br/>
    <div class="form-group">
      <label for="pwd">이름 :</label>
      <input type="text" class="form-control" id='true' name="user_name">
    </div><br/>
    <div class="form-group">
      <label for="pwd">비밀번호 :</label>
	<input type="password" class="form-control"  name="user_pw" onchange="pwchk(this.value)">
	<div id="pw" style="margin-left: 70px; margin-top: 10px">8 ~ 10자 영문, 숫자 조합이여야 합니다.</div>
    </div><br/>
    <div class="form-group">
      <label for="pwd">비밀번호 확인:</label>
      <input type="password" class="form-control" name="user_pw2" onchange="pwchk2(this.value)">
      <div id="pw2" style="margin-left: 70px; margin-top: 10px"></div>
    </div><br/>
    <div class="form-group">
      <label for="pwd">핸드폰 번호:</label>
      <input type="tel" class="form-control" name="user_phone" onchange="phonechk(this.value)">
      <div id='phone' style="margin-left: 90px; margin-top: 10px">-는 제외한 번호만 입력해주세요</div>
    </div><br/>
    <input type="hidden" id='true' name="user_adchk" value="${user_adchk}"><br/>
    <input type="button" class="btn btn-default" id='true' value="회원가입" onclick="efChk()">
  </form>
</div>


</body>
</html>