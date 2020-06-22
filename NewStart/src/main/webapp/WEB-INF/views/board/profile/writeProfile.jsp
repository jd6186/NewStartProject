<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

function check() {
	var pro_school=document.getElementById("pro_school").value;
	var pro_major=document.getElementById("pro_major").value;
	var pro_tech=document.getElementById("pro_tech").value;
	var pro_info=document.getElementById("pro_info").value;
	
// 	alert(pro_school+" : "+pro_major+" : "+pro_tech+" : "+pro_info);
	if (pro_school=="") {
		swal("대학교","대학교를 확인해 주세요");
		return false;
	}else if(pro_major == ""){
		swal("pro_major","전공을를 확인해 주세요");
		return false;
	}else if(pro_tech == "" ){
		swal("pro_tech","보유기술을 사용할 수 없습니다");
		return false;
	}else if(pro_info == "" ){
		swal("pro_info","소개를 작성해주세요");
		return false;
	}else{
		document.frm.submit();
	}
}
</script>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>

<h1>WriteForm</h1>

<div class="container">
  <h2>Form control: input</h2>
  <p>The form below contains two input elements; one of type text and one of type password:</p>
  
  <form action="./writeProfile.do" method="post" name="frm">
  <input type="hidden" name="user_seq" value="${newstart.user_seq }">
    <div class="form-group">
      <label for="pro_school">대학교 :</label>
      <input type="text" class="form-control" name="pro_school" id="pro_school">
    </div>
    <div class="form-group">
      <label for="pro_major">전공 :</label>
      <input type="text" class="form-control" name="pro_major" id="pro_major">
    </div>
    <div class="form-group">
      <label for="pro_tech">보유 기술 :</label>
      <input type="text" class="form-control" name="pro_tech" id="pro_tech">
    </div>
    <div class="form-group">
      <label for="pro_info">자기 소개 :</label>
      <input type="text" class="form-control" name="pro_info" id="pro_info">
    </div>
    
    <input type="button" value="다음으로 가기" onclick="check()">
    <input type="reset" value="다시쓰기">
  </form>
</div>
</body>
</html>