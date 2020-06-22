<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function pwchk(val){
	 var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
	 
	if(regExp.test(val)){
		$('#pw').css('color','blue');
		$('#pw').html('사용 가능한 비밀번호 입니다!');
		$('input[name=newPW]').attr('id','true');
	}else{
		$('#pw').css('color','red');
		$('#pw').html('8 ~ 10자 영문, 숫자 조합이여야 합니다.');
		$('input[name=newPW]').attr('id','false');
	}

}

function pwchk2(val){
	if($('[name=newPW]').val() != val){
		$('#pw2').css('color','red');
		$('#pw2').html('비밀번호가 일치하지 않습니다.');
		$('input[name=newPW2]').attr('id','false');
	}else{
		$('#pw2').html('');
		$('input[name=newPW2]').attr('id','true');
	}
}

function changePW(){
	
	var pw = $('input[name=newPW]').attr('id');
	var pw2 = $('input[name=newPW2]').attr('id');
	
	//alert(pw);
	//alert(pw2);
	if(pw && pw2){
		alert('비밀번호가 변경되었습니다!');
		$('#cpw').submit();
	}else{
		alert('정확한 값을 입력해주세요');
	}
}

</script>
</head>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div class="container">
  <h2>비밀번호 변경</h2>
	<form action="./ChangePW.do" method="POST" id="cpw">
	<input type="hidden" name="email" value="${email}">
    <div class="form-group">
      <label for="usr">비밀번호:</label>
      <input type="password" class="form-control" name="newPW" id="usr" onchange="pwchk(this.value)">
      <div id="pw" style="margin-left: 10px; margin-top: 10px">8 ~ 10자 영문, 숫자 조합이여야 합니다.</div>
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호 확인:</label>
      <input type="password" class="form-control" id="ph" name="newPW2" onchange="pwchk2(this.value)">
		<div id="pw2" style="margin-left: 10px; margin-top: 10px"></div>
	<input class="btn btn-default" type="button" onclick="changePW()" value="변경하기">
    </div>
  </form>
</div>





</body>
</html>