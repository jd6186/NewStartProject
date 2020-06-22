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

<script type="text/javascript">
// 	function modifyProfile(){
// 		location.href="./modifyProfile.do";
// 	}
// 	function modifyCareer(){
// 		location.href="./modifyCareer.do";
// 	}
	
	function modifyProfile(){
		var pro_school = $("#pro_school").val();
		var pro_major = $("#pro_major").val();
		var pro_tech = $("#pro_tech").val();
		var pro_info = $("#pro_info").val();
		
// 		alert(user_nickname+":   "+user_phone);
		$.ajax({
			url :"modifyProfile.do",
			method : "post",
			dataType: {"pro_school":pro_school,"pro_major":pro_major,"pro_tech":pro_tech,"pro_info":pro_info},
			success : function(dto){
				alert("수정 성공");
				alert(dto);
			},
			error : function(){
				alert("수정 실패");
			}
		});
	}
	function modifyCareer(){
		var user_nickname = $("#user_nickname").val();
		var user_phone = $("#user_phone").val();
		
// 		alert(user_nickname+":   "+user_phone);
		$.ajax({
			url :"updateMyPage.do",
			method : "post",
			dataType: {"user_nickname":user_nickname,"user_phone":user_phone},
			success : function(dto){
				alert("수정 성공");
				alert(dto);
			},
			error : function(){
				alert("수정 실패");
			}
		});
	}
	
	
</script>
<div class="container">
${newstart }<br>
${profileDto }
${careerDto }

  <h2>${newstart.user_name }님의 경력 보기</h2>
  <!-- Trigger the modal with a button -->
  
<div class="container">
  <form action="#" method="post">
    <div class="form-group">
      <label for="usr">학교 :</label>
      <input type="text" class="form-control" name="pro_school" id="pro_school" value="${profileDto.pro_school }">
    </div>
    <div class="form-group">
      <label for="user_nickname">전공 :</label>
      <input type="text" class="form-control" name="pro_major" id="pro_major" value="${profileDto.pro_major }">
    </div>
    <div class="form-group">
      <label for="user_phone">기술 :</label>
      <input type="text" class="form-control" name="pro_tech" id="pro_tech" value="${profileDto.pro_tech}">
    </div>
    <div class="form-group">
      <label for="user_phone">소개 :</label>
      <input type="text" class="form-control" name="pro_info" id="pro_info" value="${profileDto.pro_info }">
    </div>

    <div class="form-group">
      <label for="user_tchk">별점 :</label>
      <input type="text" class="form-control" id="user_tchk" value="${profileDto.pro_star }" readonly="readonly">
    </div>
    
  	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myCareer">경력 보기</button>
    <input type="button" id="modifyProfile" value="프로필 수정하기" onclick="modifyProfile()">
  </form>
</div>


  <!-- Modal -->
  <div class="modal fade" id="myCareer" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Modal Header</h4>
        </div>
        <div class="modal-body">
          	<div class="form-group">
	   	  		<label for="user_nickname">전공 :</label>
	     		<input type="text" class="form-control" name="career_company" id="career_company" value="${careerDto.career_company }">
	  		</div>
          	<div class="form-group">
	   	  		<label for="user_nickname">부서 :</label>
	     		<input type="text" class="form-control" name="career_dept" id="career_dept" value="${careerDto.career_dept }">
	  		</div>
          	<div class="form-group">
	   	  		<label for="user_nickname">직급 :</label>
	     		<input type="text" class="form-control" name="career_job" id="career_job" value="${careerDto.career_job }">
	  		</div>
          	<div class="form-group">
	   	  		<label for="user_nickname">기간 :</label>
	     		<input type="text" class="form-control" name="career_term" id="career_term" value="${careerDto.career_term }">
	  		</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="modifyCareer()">경력 수정하기</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>
</body>
</html>