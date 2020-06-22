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
  <h2>비밀번호 찾기</h2>
	<form action="./goFPW1.do" method="post" id='findPwForm'>
    <div class="form-group">
      <label for="usr">이름:</label>
      <input type="text" class="form-control" id="usr" style="width:300px;">
    </div>
    <div class="form-group">
      <label for="pwd">아이디:</label>
      <input type="text" class="form-control" name='email'  style="width:300px;" onchange="findidchk(this.value)">
      <div id='emailchk'></div>
	<input class="btn btn-default" type="button" value="확인" onclick="findPwSubmit()">
    </div>
  </form>
</div>



</body>
<script type="text/javascript">
function findidchk(val){
	
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	if(regExp.test(val)){
		$('#emailchk').html(' ');
		$('input[name=email]').attr('id','true');
	}else{
		$('#emailchk').css('color','red');
		$('#emailchk').html('올바른 이메일 형식이 아닙니다.');
		$('input[name=email]').attr('id','false');
	}
	
}
function findPwSubmit(){
	if($('input[name=email]').attr('id') == 'true' && $.trim($('#usr').val()) != ''){
		$('#findPwForm').submit();
	}else{
		alert('정확한 정보를 입력해주세요');
	}
}


</script>
</html>