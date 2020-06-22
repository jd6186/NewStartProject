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
			function delClick(re_seq){
				location.href = "./reviewDelete.do?re_seq="+re_seq;
			}
			
			//답글 달기
			function insertClick(re_seq){
				location.href = "./moveReply.do?re_seq="+re_seq;
			}
			
			//수정하기
			function modifyClick(re_seq){
				location.href = "./moveModify.do?re_seq="+re_seq;
			}
			
			$(function(){
				if (input.files && input.files[0]) {
					var reader = new FileReader(); // FIleApi
					var url = ${dto.fileox};
					reader.onload = function(e) {
						var img = document.getElementById("image");
						img.src = e.target.result;
						img.style.width = '100%';
						img.style.height = 'auto';
					}
					reader.readAsDataURL(url);
					$("#image").show();
				}
				
			});
		
			//신고하기
			function insertDec(board_seq,dec_suspect,dec_victim){
		// 		alert("suspect"+dec_suspect+",dec_victim"+dec_victim);
				alert("전송됩니다.");
				location.href = "./insertDec.do?board_seq="+board_seq+"&dec_suspect="+dec_suspect+"&dec_victim="+dec_victim;
		
			}
			
		</script>
		
		<div class="container">
			<input type="hidden" name="board_seq" value="${dto.re_seq}">
			<input type="hidden" name="dec_suspect" value="${dto.user_seq}">
			<input type="hidden" name="dec_victim" value="${newstart.user_seq}">
			<img id="image" src="./img/${dto.fileox }" ><br>
			 글번호 :${dto.re_seq}<br>
			<%--    제목 : ${dto.re_title }<br> --%>
			   내용 : ${dto.re_content }<br>
			   작성일 : ${dto.re_regdate }<br>
			   별점 : ${dto.re_star }<br>
			   강사 : ${dto.re_teacher }<br>
			   파일 : ${dto.fileox }<br>
			
			   
		   	<c:if test="${newstart.user_seq eq dto.user_seq }">
			   	<input type="button" value="글 수정하기" onclick="modifyClick(${dto.re_seq})">
			   	<input type="button" value="글 삭제하기" onclick="delClick(${dto.re_seq})">
		   	</c:if>
		   	<c:if test="${newstart.user_seq eq dto.re_teacher }">
		   		<input type="button" value="답글 달기" onclick="insertClick(${dto.re_seq})">
		   	</c:if>
		   	<br><br>
		   	<input type="button" value="신고하기" onclick="insertDec(${dto.re_seq},${dto.user_seq},${newstart.user_seq })">
		   	
		</div>
	</body>

</html>



