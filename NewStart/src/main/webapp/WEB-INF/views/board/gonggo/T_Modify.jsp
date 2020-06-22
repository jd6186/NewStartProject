<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<script type="text/javascript" src="./js/boardListGonggo.js"></script>
<script type="text/javascript">
	
</script>
<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<!-- 수정폼 -->
	<div id="container">
		<div class="">
			<!-- Modal content-->
			<div class="">
				<div class="">
					<button type="button" class="close">&times;</button>
					<h4 class="">게시글 수정</h4>
				</div>
				<div id="modifyForms">
						<form action="#" method="get" id="frmModify">
					  <input type='hidden' id='Gonggo_seq' name='Gonggo_seq' value='${Gonggo_seq}'>
					  
					  <div class='form-group'>
					  <label for='title'>제목</label>
					  <input type='text' class='form-control' id='title' name='title' value='${title}' required='required'>
					  </div>
					  
					  <div class='form-group'>
					  <label for='content'>내용</label>
					  <textarea class='form-control' id='bo_content' name='content' rows='5'>${content}</textarea>
					  </div>
					  
					  <div class='form-group'>
			          <input class='btn btn-success' type='button' value='글수정 완료' onclick='update()'>
			          <input class='btn btn-info' type='reset' value='내용 초기화'>
			          <button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>
			          </div>
			          
					
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			CKEDITOR.replace('bo_content',{
				filebrowserUploadUrl: './adm/fileupload.do'
			});
		});
	</script>
</body>
</html>