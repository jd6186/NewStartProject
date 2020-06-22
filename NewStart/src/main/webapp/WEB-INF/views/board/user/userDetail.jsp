<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function a(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader(); // FIleApi
		reader.onload = function(e) {
			var img = document.getElementById("image");
			img.src = e.target.result;
			img.style.width = '100%';
			img.style.height = 'auto';
		}
		reader.readAsDataURL(input.files[0]);
		$("#image").show();
	}
}
</script>
<body>
	<%@ include file="/WEB-INF/views/boardTopMenu.jsp"%>


${dto }
<img id="image" src="#" style="display: none;">




</body>
</html>