function chk(){
	var name = document.getElementById("name").value;
	var id = document.getElementById("id").value;
	var password = document.getElementById("password").value;
	var passOk = document.getElementById("passOk").value;
	var i_agree = document.getElementById("i_agree").checked;
	var chkId = document.getElementById("chkval").value;
	//alert(name +" / "+ id  +" / "+ password  +" / "+ passOk  +" / "+ i_agree  +" / "+ chkId);
	if(name == ""){
		swal("회원가입 오류", "성명을 확인해주세요");
		return false;
	} else if(password != passOk){
		swal("회원가입 오류", "비밀번호 일치하지 않습니다.");
		return false;
	} else if(chkId=="0"){
		swal("회원가입 오류", "아이디를 사용할 수 없습니다.");
		return false;
	} else if(i_agree == false){
		swal("회원가입 오류", "개인정보 수집동의(필수) 해주세요");
		return false;
	}
	
}


$(document).ready(function(){
	$("#id").keyup(function(){
//		alert("키 눌렀다 땠음?");
		var inputLength = $(this).val().length;
		var id = $(this).val();
		if(id.indexOf(" ")!= -1){
			$("#result").css("color", "red");
			$("#result").html("공백이 포함된 아이디는 입력하 실 수 없습니다.");
			$("#chkval").val("0");
			$("#id").val(""); // 초기화 시켜버리기
		} else if(inputLength < 5){
			$("#result").css("color", "red");
			$("#result").html("6자리 이상의 아이디는 입력하세요");
			$("#chkval").val("0");
		} else if(inputLength > 5){ // 공백이 없고 6자리 이상이라면 ajax를 통해서 유효성 검증
			$("#result").html("");
			jQuery.ajax({
				type:"post",
				url:"./idCheck.do",
				data: "id="+$(this).val(),
				async: true,
				success : function(msg){
//					alert(msg.isc);	
					if(msg.isc == "false"){
						$("#result").css("color", "blue");
						$("#result").html("사용가능한 아이디 입니다.");
						$("#chkval").val("1");
					} else {
						$("#result").css("color", "red");
						$("#result").html("사용가능한 불가능한 아이디 입니다.");
						$("#chkval").val("");
						$(this).val("");
					}
				},
				error: function(){
					alert("잘못된 요청입니다.");
				}
			});
			
		}
	});
});




// 이메일 인증 문장 정규화 하기~~~~~~~~~~~~~``
function emailChk(){
	var email = document.getElementById('email').value;
	var regEx = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/; // 이메일 형식 정규화해주기~ 자바스크립트에서 정규화하기
	if(!regEx.test(email)){ // 이메일 형식에 맞지 않다면(test는 그냥 예약어로서 regEx정규화를 가지고 판단을 실행하는 메서드이다.)~~~
		alert("이메일 형식이 아닙니다.");
	}
	alert("사용가능한 이메일입니다.");
	
	
}
















































