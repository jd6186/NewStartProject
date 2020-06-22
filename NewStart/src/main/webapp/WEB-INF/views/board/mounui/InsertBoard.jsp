<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function chkchk(){
	var title = document.getElementById('title').value;
	var comment = document.getElementById('comment').value;
	var file = document.getElementById('filechk');
	
	
	if(title.trim() == '' || comment.trim() == ''){
		alert('제목과 내용을 모두 입력해주세요');
	}else if(filechk(file.files)){
		alert('문의글이 등록되었습니다.');
		document.forms[0].submit();
	}
	
}

function filechk(files){
	
	
	var mimeChk = /application\/pdf|text\/plain|image\/gif|image\/png|image\/jpeg|audio|video|application\/vnd.mspowerpoint|application\/xml/;
	for (var i = 0; i < files.length; i++) {
		alert(files[i].type);
		if(!mimeChk.test(files[i].type) || files[i].type == ""){
			alert("업로드 할 수 없는 확장자가 있습니다. 다시 확인해주세요");
			return false;
		}
		
		}
		return true;
}


window.onbeforeunload = function(e) {
	    var dialogText = 'Dialog text here';
    	e.returnValue = dialogText;
    	return dialogText;
};

</script>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div class="container">
  <form action="./UinsertBoard.do" method="post" enctype="multipart/form-data">
    <div class="form-group">
     <label for="sel1">Select list (select one):</label>
      <select class="form-control" name="category_seq" id="sel1">
        <c:forEach items="${Fdto}" var="fdto">
	        <option  value="${fdto.category_seq}">${fdto.category_title}</option>
        </c:forEach>
      </select>
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="title">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="5" id="comment" name="content" style="resize: none"></textarea>
    </div>
      <input type="file" class="form-control" id='filechk' multiple="multiple" name="file">
      <button type="button" class="btn" onclick="chkchk()">등록</button>
      <button type="button" class="btn" onclick="location.href='./UserMBoard.do'" >취소</button>
  </form>
</div>

</body>
</html>