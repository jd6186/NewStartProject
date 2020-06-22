<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${newstart.user_name}님의 마이페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>
	<script type="text/javascript" src="./js/mypage.js"></script>


<h1>${newstart.user_name}님의mypage</h1>

<div class="container">
  <form action="./updateMyPage.do" method="post">
  <input type="hidden" name="user_seq" id="user_seq" value="${newstart.user_seq }">
    <div class="form-group">
      <label for="usr">이름 :</label>
      <input type="text" class="form-control" id="usr" value="${newstart.user_name }">
    </div>
    <div class="form-group">
      <label for="user_nickname">닉네임 :</label>
      <input type="text" class="form-control" name="user_nickname" id="user_nickname" value="${newstart.user_nickname }">
    </div>
    <div class="form-group">
      <label for="user_phone">전화번호 :</label>
      <input type="text" class="form-control" name="user_phone" id="user_phone" value="${newstart.user_phone }">
    </div>
    <div class="form-group">
    	광고성 수신 여부 : 
     	<input type="checkbox"  name="user_adchk" id="user_adchk" onchange="adchk()">
    </div>

    <div class="form-group">
      <label for="user_tchk">강사 인증 :</label>
      <c:choose>
      	<c:when test="${newstart.user_tchk eq 'N' &&newstart.user_treq eq 'N' }">
	    	<input type="button" name="user_tchk" id="user_tchk" data-toggle="modal" data-target="#myModal" value="강사 인증하기" >
      	</c:when>
      	<c:when test="${newstart.user_tchk eq 'N' && newstart.user_treq eq 'Y' }">
      	인증 확인 중
      	</c:when>
      	<c:otherwise>
      	인증 완료${newstart.user_treq }
      	<br>${newstart.user_tchk }
      	</c:otherwise>
      </c:choose>
    </div>
    
    <input type="submit"value="수정하기">
</form>

    <div id="myModal" class="modal fade" role="dialog">
  		<div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">강사 인증</h4>
      </div>
      <div class="modal-body">
        <form action="./teacherReq.do" method="post" id="form1" enctype="multipart/form-data" onsubmit='return fileChk(this.file.files);'>
			<input type="file" name="file" id="file" multiple="multiple">
			<input type="submit" value="등록"> 
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" onclick="requestTeacher(${newstart.user_tchk })" class="btn btn-default" data-dismiss="modal">Close</button>

      </div>
    </div>

  </div>
</div>
</div>
</body>
</html>