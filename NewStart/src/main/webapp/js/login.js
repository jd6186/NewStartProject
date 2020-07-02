// --------------------------------로그인 화면 ---------------------------------------------------
//로그인 화면 유효성 체크
function ccchk(){

	if($.trim($('input[name=username').val()) == ''){
		alert('아이디를 입력해주세요');
	}else if($.trim($('input[name=password]').val()) == ''){
		alert('비밀번호를 입력해주세요');
	}

	var key = $('input[name=key]').val();
	var chk = $('input[name=chk]').val();

	if(typeof key == 'undefined'){
		$('form').submit();
	}else{
		getkey(key, chk);
	}
}
//캡챠 확인
function getkey(key, chk){
	
	$.ajax({
		url : "./valchk.do",
		type : "post",
		data : {"key":key,"chk":chk},
		dataType : "json",
		success: function(data){
			if(data.result == true){
				$('form').submit();
			}else{
				alert('캡챠를 확인해주세요');
				location.href='./loginForm.do?id='+$('input[name=username').val();
				return;
			}
		},
		error: function(){
			alert('캡차 오류');
			return false;
		}
	});
}

//--------------------------------회원가입 화면 ---------------------------------------------------
function checkAll(bool){
	
	var chks = document.getElementsByName('user_adchk');

	for (var i = 0; i < chks.length; i++) {
		chks[i].checked = bool;
	}
}

function chk(){
	var chkbool = document.getElementsByName('user_adchk');
	var allcheck = document.getElementById('i_agreeA');
	var cnt = 0;
	for (var i = 0; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt++;
		}
	}
	
	if(cnt == chkbool.length){
		allcheck.checked = true;
	}else{
		allcheck.checked = false;
	}
	
}

function gosignUp(){
	var chkbool = document.getElementsByName('user_adchk');
	if(chkbool[0].checked == false || chkbool[1].checked == false){
		alert('필수항목에 모두 동의해주세요');
		return;
	}else{
		 document.forms["formchk"].submit();
	}
}



function idchk(val){
	
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	if(regExp.test(val.value)){
		Multichk(val.name,val.value);
	}else{
		alert('올바른 이메일 형식이 아닙니다.');
	}
	
}

function nickchk(val){
	Multichk(val.name,val.value);
}


var Multichk = function(url,val){
	
	$.ajax({
		url : "./"+url+"/MultiChk.do",
		type : "post",
		data : {"val":val},
		dataType : "text",
		success: function(data){
			if(data == 'false'){
				alert("사용 가능");
				$('input[name='+url+']').attr("id",val);
			}else{
				alert("사용 불가");
			}
		},
		error: function(){
			alert('오류');
		}
	});
}

 function efChk(){
	
	 $('input').each(function(i){
		 
		 //입력하지 않았을때
		if($.trim($(this).val()) == ''){
			alert('모두 입력하셔야 회원가입이 가능합니다.');
			$(this).focus();
			return false;
		}
		// 중복검사하지 않았을 때
		if (i == 0 || i == 1) {
			if ($.trim($(this).attr('id')) != $.trim($(this).val())) {
				alert('중복검사를 완료해주세요');
				$(this).focus();
				return false;
				}
		}else{
			if($(this).attr('id') != 'true'){
				alert('올바른 값을 입력해주세요');
				$(this).focus();
				return false;
			}
		}
		if(i == 7){
			alert('이메일이 전송되었습니다. 인증을 하시면 회원가입이 완료됩니다.');
				$('#signUp').submit();
			
		}

	 });
	 
}

 function pwchk(val){
	 var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,10}$/; //  8 ~ 10자 영문, 숫자 조합
	 
	if(regExp.test(val)){
		$('#pw').css('color','blue');
		$('#pw').html('사용 가능한 비밀번호 입니다!');
		$('input[name=user_pw]').attr('id','true');
	}else{
		$('#pw').css('color','red');
		$('#pw').html('8 ~ 10자 영문, 숫자 조합이여야 합니다.');
		$('input[name=user_pw]').attr('id','false');
	}

 }


function phonechk(val){
	var regExp = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
		
	if(!regExp.test(val)){
		$('#phone').css('color','red');
		$('#phone').html('올바른 핸드폰 번호가 아닙니다 \'-\'는 제외한 번호만 입력해주세요');
		$('input[name=user_phone]').attr('id','false');
	}else{
		$('#phone').css('color','black');
		$('#phone').html('\'-\'는 제외한 번호만 입력해주세요');
		$('input[name=user_phone]').attr('id','true');
	}

}

function pwchk2(val){
	if($('[name=user_pw]').val() != val){
		$('#pw2').css('color','red');
		$('#pw2').html('비밀번호가 일치하지 않습니다.');
		$('input[name=user_pw2]').attr('id','false');
	}else{
		$('#pw2').html('');
		$('input[name=user_pw2]').attr('id','true');
	}
}

//-------------------아이디 찾기----------------
function idsubmitchk(){
	if($('input[name=user_phone]').attr('id') == 'true'){
		$('#idfind').submit();
	}else{
		alert('정확히 입력해주세요');
	}
}
//-----------------비밀번호 찾기-------------
function findidchk(val){
	
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	if(regExp.test(val)){
		$('#emailchk').html(' ');
		$('input[name=email]').attr('id','true');
	}else{
		$('#emailchk').css('color','red');
		$('#emailchk').html('올바른 이메일 형식이 아닙니다.');
		$('input[name=email]').attr('id','false');
	}
	
}

function findPwSubmit(){
	if($('input[name=email]').attr('id') == 'true' && $.trim($('#usr').val()) != ''){
		$('#findPwForm').submit();
	}else{
		alert('정확한 정보를 입력해주세요');
	}
}
