//---------------------------------------관리자 자동 이메일 게시판 상세보기----------------------------




function gosubmit(){
	
	if(document.getElementById('title').value.trim() == ''){
		alert('제목을 입력해주세요');
	}else if(document.getElementById('content').value.trim() == ''){
		alert('내용을 입력해주세요');
	}else{
		document.getElementsByName('gosubmit')[0].style.display = "none";
		document.forms[0].submit();
	}
}



//---------------------------------------관리자 이메일 기록 게시판----------------------------
function pageList(){
	function pageList(){
		 alert("dd");
			var index = document.getElementById('index'); // 페이지 번호
			var pageNum = document.getElementById('pageNum'); // 페이지 목록
			var listNum = document.getElementById('listNum'); // 뿌려지는 게시글 수
			
			index.value = 0;
			pageNum.value = 1;
			listNum.value = document.getElementById('list').value;
		// alert(index.value + ":" + pageNum.value + ":" + listNum.value);
			
			//var selList = document.getElementById('list');
		// alert(selList.selectedIndex);
//			selList.options[selList.selectedIndex].setAttribute("selected", "selected");
//			selList.reload();

			
			pageAjax();
		}
}



function setSearchFilter(){
	
	var user_grade = document.getElementsByName('user_grade');
	var successchk = document.getElementsByName('successchk');
	var succhkidx = [];
	var filter = [];
	var firstDate = document.getElementsByName('firstDate')[0].value;
	var lastDate = document.getElementsByName('lastDate')[0].value;
	
	if(!user_grade[0].checked){
		for (var i = 1; i < user_grade.length; i++) {
			if(user_grade[i].checked){
				filter.push(user_grade[i].value);	
			}
		}
	}
	
	for (var i = 1; i < successchk.length; i++) {
		if(successchk[i].checked){
			succhkidx.push(successchk[i].value);			
		}
	}
	
	
	document.getElementById('user_grade_result').value = filter;
	document.getElementById('successchk_result').value = succhkidx;
	document.getElementById('firstDate_result').value = firstDate;
	document.getElementById('lastDate_result').value =lastDate;
	
	document.getElementById('index').value = '0';
	document.getElementById('pageNum').value = '1';
	document.getElementById('listNum').value = document.getElementById('list').value;
	
	pageAjax();
}



var pageAjax = function(){
	

	var filter = document.getElementById('user_grade_result').value;
	var successchkval = document.getElementById('successchk_result').value;
	var firstDate = document.getElementById('firstDate_result').value;
	var lastDate = document.getElementById('lastDate_result').value;
	//페이징 값 가져오기
	var index = document.getElementById('index').value;
	var pageNum = document.getElementById('pageNum').value;
	var listNum = document.getElementById('listNum').value;
	
	var success = successchkval.split(',');
	
	
	
	$.ajax({
		url : "./emailPaging.do",
		type: "post",
		async: true,
		traditional : true,
		data:  {
			"filter":filter,
			"successchk" : success,
			"firstDate":firstDate,
			"lastDate":lastDate,
			"index":index,
			"pageNum":pageNum,
			"listNum":listNum,
			"index":index,
			"pageNum":pageNum,
			"listNum":listNum
			}, // 키 = 값
		dataType: "json",
		success: function(msg){
			//alert('dd');
			  $.each(msg,function(key,value){
				  
				if(key == "lists"){
					var html = '';
					if(value.length > 0){
				 $.each(value,function(k,v){
					//alert(v.email_title);
					html+=	"<tr>";
					html+=	"<td><input type='checkbox' name='seq' value='"+v.email_seq+"' onclick='chk(this.name)'></td>";
					html+=	"<td>"+v.email_seq+"</td>";
					html+=	"<td>";
					if(v.category_code == '1'){
					html+=	"광고성";
					}
					html+=	"</td>";
					html+=	"<td><a href=\"./ASelMailDetail.do?seq="+v.email_seq+"\">"+v.email_title+"</a></td>";
					html+=	"<td>";
					switch(v.successchk){
					case 'S' : html+="대기 중"; break;
					case 'F' : html+="실패"; break;
					case 'Y' : html+="성공"; break;
					}
					html+=	"</td>";
					html+=	"<td>";
					html += v.user_email;
					html+=	"</td>";
					html+=	"<td>"+v.regdate+"</td>";
					html+=	"</tr>";
				 	});
					}else{
						html+=	"<tr>";
						html+=	"<td>글이 존재하지 않습니다...</td>";
						html+=	"</tr>";
					}
					$('#tbody').html(html);
				 }else{
					 var html = '';
					html +="<li><a href='#'onclick='pageFirst()'  >&laquo;</a></li>";
				    html +="<li><a href='#' onclick = 'pagePre("+value.pageNum+","+value.pageList+")'  >&lsaquo;</a></li>";
				    for (var i = value.pageNum; i<= value.count; i++) {
				    html +="<li><a href='#' onclick='pageIndex("+i+")'>"+i+"</a></li>";
				    }
				    html +="<li><a href='#' onclick='pageNext("+value.pageNum+","+value.total+","+value.listNum+","+value.pageList+")'>&rsaquo;</a></li>";
				    html +="<li><a href='#' onclick='pageLast("+value.pageNum+","+value.total+","+value.listNum+","+value.pageList+")'>&raquo;</a></li>";
					 
					$('.pagination').html(html); 
	} 	});
		
		},
		error: function(){
			alert('에러');	
		}
		});

}

function pageIndex(idx){
//	alert(idx);
	var index = document.getElementById('index');
	index.value = idx-1;
	
	pageAjax();
}


//< 버튼
function pagePre(num, pageList){
//	alert(pageNum+":"+pageList);
	if(0<(num - pageList)){
		num -= pageList;
		var index = document.getElementById('index');
		var pageNum = document.getElementById('pageNum');
		pageNum.value = num;
		index.value  = num -1;
	}
	pageAjax();
}


//>
function pageNext(num, total, listNum, pageList){
//	alert(num);
//	alert(total);
//	alert(listNum);
//	alert(pageList);
	var index = Math.ceil(total/listNum); // 30/5 6개의 페이지가 있음 123456
	var max = Math.ceil(index/pageList); // 6/5 두 그룹으로 나눌 수 있음 12345 6
	
	if(max*pageList > num+pageList){
		num += pageList;
		
		var index = document.getElementById('index');
		var pageNum = document.getElementById('pageNum');
		
		pageNum.value = num;
		index.value = num-1;
	}
	pageAjax();
}

function pageLast(num, total, listNum, pageList){
	var idx = Math.ceil(total/listNum);
	var max = Math.ceil(idx/pageList);
	
	while(max*pageList > num+pageList){
		num += pageList;
	}
	
	var index = document.getElementById('index');
	var pageNum = document.getElementById('pageNum');
	pageNum.value = num;
	index.value = idx-1;
	
	pageAjax();
}



function pageList(){
// alert("dd");
	var index = document.getElementById('index'); // 페이지 번호
	var pageNum = document.getElementById('pageNum'); // 페이지 목록
	var listNum = document.getElementById('listNum'); // 뿌려지는 게시글 수
	
	index.value = 0;
	pageNum.value = 1;
	listNum.value = document.getElementById('list').value;
// alert(index.value + ":" + pageNum.value + ":" + listNum.value);
	
	var selList = document.getElementById('list');
// alert(selList.selectedIndex);
//	selList.options[selList.selectedIndex].setAttribute("selected", "selected");
//	selList.reload();
	
	pageAjax();
}

function pageFirst(){
//	alert("작동");
	var index = document.getElementById("index");
	var pageNum = document.getElementById("pageNum");
	index.value = 0;
	pageNum.value = 1;
	pageAjax();
}

//---------------------------------------대량 메일 보내기----------------------------
function checkAll(bool){
	//alert(name);
	var chkVals = document.getElementsByName('filter');
	//Allfilter
	document.getElementsByName('Allfilter').checked = bool;
	for (var i = 0; i < chkVals.length; i++) {
		chkVals[i].checked = bool;
		}

	if(bool){
	var user_grade = ["'M'","'T'"];
	alert(user_grade);
	chkCnt(user_grade);
	}else{
		document.getElementById('sendCnt').innerHTML = '총 발송 건 수 : 0통';
	}
	
	
}

function chk(){
	var chkbool = document.getElementsByName('filter');


	var user_grade = [];
	var cnt = 0;
	
	for (var i = 0; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt++;
			user_grade.push(chkbool[i].value);
		}else{
			document.getElementsByName('Allfilter')[0].checked = false;
		}
	}
		if(cnt == chkbool.length){
			document.getElementsByName('Allfilter')[0].checked = true;
			//alert(user_grade);
			chkCnt(user_grade);
		}else if(cnt > 0){
			chkCnt(user_grade);
		}else{
			document.getElementById('sendCnt').innerHTML = '총 발송 건 수 : 0통';
		}
	
		
		
		
}

function chkCnt(user_grade){
	var emailList = document.getElementsByName('user_email')[0];
	var sendCnt = document.getElementById('sendCnt');

	$.ajax({
		url : "./getuserEmails.do",
		type : "post",
		traditional : true,
		data : {"user_grade":user_grade},
		dataType : "text",
		success: function(data){
			//alert(data);
			//alert(data.split(',').length);
			emailList.value = data;
			sendCnt.innerHTML = '총 발송 건 수 : '+data.split(',').length+'통';
		},
		error: function(){
			alert('오류');
		}
	}); 
}

function gosubmit_email(){
	
	var chkbool = document.getElementsByName('filter');
	var email_title = document.getElementsByName('email_title')[0];
	//var email_content = document.getElementsByName('email_content')[0];
	var email_content = CKEDITOR.instances['pwd'].getData();
	alert(email_content);
	var cnt = 0;

	if(email_title.value.trim() == '' || email_content.trim() == ''){
		alert('제목 및 내용을 작성해주세요');
		return;
	}
	
	for (var i = 0; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt ++;
		}
	}
	
	if(cnt == 0){
		alert('수신자 필터를 설정해 주세요');
		return;
	}else{
		alert('전송');
		document.forms[0].submit();
	}
	
}





