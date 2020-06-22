<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<script type="text/javascript" src="./js/mounui.js"></script>
<script type="text/javascript">
window.onbeforeunload = function(e) {
	if(document.getElementById('modi').value != 'success'){
	    var dialogText = 'Dialog text here';
    	e.returnValue = dialogText;
    	return dialogText;
	}
};
</script>
<body>

<div class="container">
	<form action="./AReplySend.do" method="post" id="form">
    <input type="hidden" class="form-control" name="mounui_seq" id="modi" value="${seq}">
  <div class="form-group">
      <label for="usr">수신자 :</label>
      <input type="text" class="form-control" name="user_email" id="dusr" value="${email}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="usr">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea  class="form-control" rows="5" id="comment" name="content" style="resize: none"></textarea>
    </div>

      <input type="hidden" class="form-control" name="filechk" value="N">
      </form>
	      <button type="button" class='btn' id="reply" onclick="sendreply()">답변보내기</button>
	      <button type="button" class='btn' id="cancel" onclick="window.close()">취소</button>
</div>


</body>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace('comment',function(ev){
		filebrowserUploadUrl: './adm/fileupload.do'
	});
}); 

function sendreply(){
	document.domain = 'localhost';
	opener.name='boarddetail';
	var f = document.getElementById('form');
	var mounui_seq = document.getElementsByName('mounui_seq')[0].value;
	var user_email = document.getElementsByName('user_email')[0].value;
	var title = document.getElementsByName('title')[0].value;
	//var content = document.getElementById('comment').value;
	var filechk = document.getElementsByName('filechk')[0].value;
	
	//alert(CKEDITOR.instances.comment.getData());
	
	
	//opener.document.location.href='';
	//f.target = opener.name;
	//f.submit();
//	data : {"mounui_seq":mounui_seq, "user_email" : user_email,"title":title,"content":content,"filechk":filechk},
	$.ajax({
		url : "./AReplySend.do",
		type : "post",
		traditional : true,
		data :{
			"mounui_seq" : mounui_seq,
			"user_email" : user_email,
			"title" : title,
			"content" : CKEDITOR.instances.comment.getData(),
			"filechk" : filechk
			
		},
		dataType : "text",
		success: function(data){
			if(data == 'true'){
				alert('메일이 발송되었습니다!');
				opener.document.location.href='./AdminMBoardDetail.do?seq='+mounui_seq;
				document.getElementById('modi').value = 'success';
				self.close();
			}else{
				alert('메일 발송이 실패되었습니다.');
			}
		},
		error : function(){
			alert('오류');
			}
	});
		
	
}

	
</script>
</html>