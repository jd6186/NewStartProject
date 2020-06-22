<%@page import="com.start.pro.dto.DTO_Profile"%>
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
	var career_company=document.getElementById("career_company").value;
	var career_dept=document.getElementById("career_dept").value;
	var career_job=document.getElementById("career_job").value;
	var career_term=document.getElementById("career_term").value;
	
//	alert(name+" : "+id+" : "+password+" : "+passOk+" : "+i_agree2+" : "+chkId);
	if (career_company=="") {
		swal("회사","대학교를 확인해 주세요");
		return false;
	}else if(career_dept == ""){
		swal("부서","부서를 확인해 주세요");
		return false;
	}else if(career_job == "" ){
		swal("직급","직급을 입력해주세요");
		return false;
	}else if(career_term == "" ){
		swal("기간","기간를 작성해주세요");
		return false;
	}else{
		document.frm.submit();
	}
	
}
</script>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div class="container">
  <h2>Form control: input</h2>
  <p>The form below contains two input elements; one of type text and one of type password:</p>
  <form action="./writeCareer.do" method="post" name="frm">
  
  <input type="hidden" name="user_seq" value="${newstart.user_seq }">
    <div class="form-group">
      <label for="career_company">회사 :</label>
      <input type="text" class="form-control" name="career_company" id="career_company">
    </div>
    <div class="form-group">
      <label for="career_dept">부서 :</label>
      <input type="text" class="form-control" name="career_dept" id="career_dept">
    </div>
    <div class="form-group">
      <label for="career_job">직습 :</label>
      <input type="text" class="form-control" name="career_job" id="career_job">
    </div>
    <div class="form-group">
      <label for="career_term">기간(ex)10년 4개월) :</label>
      <input type="text" class="form-control" name="career_term" id="career_term">
    </div>
    
    <input type="button" value="프로필  등록 완료" onclick="check()">
    <input type="reset" value="다시쓰기">
  </form>
</div>

</body>
</html>