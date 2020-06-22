<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<script type="text/javascript" src="./js/mounui.js"></script>
<script type="text/javascript">


function reply(){
	document.domain = 'localhost';
	window.open("./AMBoardReply.do?seq=${dto.mounui_seq}&email=${dto.board_code}","답장보내기","width=1000px, height=1000px, left=300");
}
function del(){
	
	if(confirm('정말로 삭제하시겠습니까?')){
		location.href='./AdminMBoardDel.do?seq=${dto.mounui_seq}';
	}
	
}
</script>
<body>

<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div class="container">
  <div class="form-group">
      <label for="usr">카테고리 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.category_title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="usr">작성자 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.category_seq}&lt;${dto.board_code}&gt;" readonly="readonly">
    </div>
    
    <c:if test="${!empty files}">
    <div class="form-group">
      <label for="usr">파일 :</label><br>
      <c:forEach items="${files}" var="file">
	      <a href='./fileDownload.do?seq=${file.file_seq}'>${file.filerealname}</a><br/>
      </c:forEach>
    </div>
	</c:if>
    
    
    <div class="form-group">
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="5" id="comment" name="content" readonly="readonly" style="resize: none">${dto.content}</textarea>
    </div>
    <div class="form-group">
      <label for="usr">등록일 :</label>
      <input type="text" class="form-control" name="regdate" id="usr" value="${dto.regdate}" readonly="readonly">
    </div>
      <button type="button" class='btn'  onclick="location.href='./AdminMBoard.do'">목록</button>
      <button type="button" class='btn'  onclick="del()">삭제</button>
      <c:if test="${dto.delchk ne 'Y'}">
	      <c:if test="${dto.replychk eq 'N'}">
	    	  <button type="button" class='btn' id="reply" onclick="reply()">답변보내기</button>
    	  </c:if>
      </c:if>
		<c:if test="${dto.replychk eq 'Y'}">
			<button type="button" class='btn' id='replyview'
				onclick='replyview()'>답변 보기</button>
			
		<div class="container" id="replydiv" style="display: none;">
			<div class="form-group">
				<label for="usr">제목 :</label> <input type="text"
					class="form-control" name="title" id="usr" value="${edto.email_title}"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="comment">내용:</label>
				<textarea class="form-control" rows="5" id="commentreply" name="content"
					readonly="readonly">${edto.email_content}</textarea>
			</div>
			<div class="form-group">
				<label for="usr">발송일 :</label> <input type="text"
					class="form-control" name="regdate" id="usr" value="${edto.regdate}"
					readonly="readonly">
			</div>
		</div>
		</c:if>

	</div>

<script type="text/javascript">
$(function(){
	CKEDITOR.replace('commentreply',function(ev){
		filebrowserUploadUrl: './adm/fileupload.do'
	});
}); 


</script>
</body>
</html>