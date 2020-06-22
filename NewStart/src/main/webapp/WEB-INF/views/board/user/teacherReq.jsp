<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

	
function fileChk(files){
	//var files = document.getElementById('file').files;

	// 확장자 검사. 후에는 더 추가해야징
	var expChk = /docx|ppt|txt|pdf|hwp/;
	if(files.length == 0){
		return false;
	}
	for (var i = 0; i < files.length; i++) {
	var fileName = files[i].name;
	var exp;
	if(fileName.indexOf('.')>=0){
		exp = fileName.substring(fileName.lastIndexOf('.')+1, fileName.length);
	}else{
		alert("파일명이 잘못 되었습니다. 확장자를 확인해주세요");
		return false;
	}
	//alert('파일 값은 : '+name+'파일 명은 : '+fileName+'확장자는 : '+exp);
	// 확장자 검사
	if(!expChk.test(exp.toLowerCase())){
		alert("업로드 할 수 없는 확장자가 있습니다. 다시 확인해주세요");
		return false;
	}
	}
	return true;
}

</script> 
<body>
<form action="./file.do" method="post" id="form1" enctype="multipart/form-data" onsubmit='return fileChk(this.file.files);'>
내용 <input type="text" name="name">
파일 <input type="file" name="file" id="file" multiple="multiple">
<input type="submit" value="등록"> 
</form>

</body>
</html>