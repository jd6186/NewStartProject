
function checkAll(bool,name){
	var chkVals = document.getElementsByName(name);
	for (var i = 0; i < chkVals.length; i++) {
		chkVals[i].checked = bool;
		}
	
}


function chk(name){
	var chkbool = document.getElementsByName(name);
	var cnt = 0;
	for (var i = 1; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt++;
		}else{
			chkbool[0].checked = false;
			return;
		}
	}
	
	if(cnt == chkbool.length-1){
		chkbool[0].checked = true;
	}
}


function multiDelChk(){
	var chks = document.getElementsByName('seq');
	var cntChecked = 0;
	for (var i = 1; i < chks.length; i++) {
		if(chks[i].checked){
			cntChecked ++;
		}
	}
	if(cntChecked>0){
		if(confirm('정말로 삭제하시겠습니까?')){
		 document.forms[0].submit();
		}
	}else{
		alert("선택된 글이 없습니다.");
		return;
	}
}
