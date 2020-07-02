// ---------------------------------관리자 문의 게시판------------------------------------------------
var pageAjax = function(){
	
	var categoryarray = document.getElementById('category_title_result').value;
	var user_grade = document.getElementById('user_grade_result').value;
	var replychk = document.getElementById('replychk_result').value;
	var delchk = document.getElementById('delchk_result').value;
	var firstDate = document.getElementById('firstDate_result').value;
	var lastDate = document.getElementById('lastDate_result').value;
	
	var index = document.getElementById('index').value;
	var pageNum = document.getElementById('pageNum').value;
	var listNum = document.getElementById('listNum').value;
	
	var category = categoryarray.split(',');
	
	
	$.ajax({
		url : "./AdminMBoardPaging.do",
		type: "post",
		async: true,
		traditional : true,
		data:  {
			"successchk":category,
			"user_grade" : user_grade,
			"replychk":replychk,
			"delchk":delchk,
			"firstDate":firstDate,
			"lastDate":lastDate,
			"index":index,
			"pageNum":pageNum,
			"listNum":listNum}, // 키 = 값
		dataType: "json",
		success: function(msg){
			
			$.each(msg,function(key,value){
				
				if(key == "lists"){
					var html = '';
					if(value.length > 0){
				    $.each(value,function(k,v){	
				   // alert(v.category_title);
					html +="<tr>";
				    html +="<td><input type='checkbox' name='seq' value='"+v.mounui_seq+"' onclick='chk(this.name)'></td>";
		       		html +="<td>"+v.mounui_seq+"</td>";
		       		html +="<td>"+v.category_title+"</td>";
		        	html +="<td><a href='./AdminMBoardDetail.do?seq="+v.mounui_seq+"'>"+v.title+"</a></td>";
		        	html +="<td>"+v.category_seq+"&lt;"+v.board_code+"&gt;</td>";
		        	html +="<td>"+v.regdate+"</td>";
		        	html +="<td>";
		        	switch (v.replychk) {
					case 'N':	html+="답변 처리 중"; break;
					case 'Y':	html+="답변 완료"; break;
					}
		        	html +="</td>";
		        	html +="<td>";
		        	switch (v.delchk) {
					case 'N':	html+="존재"; break;
					case 'Y':	html+="삭제"; break;
					}
		        	html +="</td>";
		      		html +="</tr>";
				    });
					}else{ // 글이 있을 때
						html+=	"<tr>";
						html+=	"<td>글이 존재하지 않습니다...</td>";
						html+=	"</tr>";
					}
					$('#boardlist').html(html);
				}else{ //key가 list일때
					var html = '';
					html +="<li><a href='#'onclick='pageFirst()'  >&laquo;</a></li>";
				    html +="<li><a href='#' onclick = 'pagePre("+value.pageNum+","+value.pageList+")'  >&lsaquo;</a></li>";
				    for (var i = value.pageNum; i<= value.count; i++) {
				    html +="<li><a href='#' onclick='pageIndex("+i+")'>"+i+"</a></li>";
				    }
				    html +="<li><a href='#' onclick='pageNext("+value.pageNum+","+value.total+","+value.listNum+","+value.pageList+")'>&rsaquo;</a></li>";
				    html +="<li><a href='#' onclick='pageLast("+value.pageNum+","+value.total+","+value.listNum+","+value.pageList+")'>&raquo;</a></li>";
					 
					$('.pagination').html(html);
					
				}
			}); // json 배열 for문
		},
		error: function(){
			alert('에러');
			}
	});
}

function setsearchFilter(){
	var firstDate = document.getElementsByName('firstDate')[0].value;
	var lastDate = document.getElementsByName('lastDate')[0].value;

	//모두 체크면 null , 하나만 선택하면 그 value리턴
	var user_grade = domchk("user_grade");
	var replychk = domchk("replychk");
	var delchk = domchk("delchk");
	var category = arraychk("category_title");

	document.getElementById('category_title_result').value = category;
	document.getElementById('user_grade_result').value = user_grade;
	document.getElementById('replychk_result').value = replychk;
	document.getElementById('delchk_result').value = delchk;
	document.getElementById('firstDate_result').value = firstDate;
	document.getElementById('lastDate_result').value = lastDate;
	
	pageAjax();
}

function pageIndex(idx){
	var index = document.getElementById('index');
	index.value = idx-1;
	
	pageAjax();
}

//< 버튼
function pagePre(num, pageList){
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

	var index = document.getElementById('index'); // 페이지 번호
	var pageNum = document.getElementById('pageNum'); // 페이지 목록
	var listNum = document.getElementById('listNum'); // 뿌려지는 게시글 수
	
	index.value = 0;
	pageNum.value = 1;
	listNum.value = document.getElementById('list').value;
	
	var selList = document.getElementById('list');
	
	pageAjax();
}

function pageFirst(){

	var index = document.getElementById("index");
	var pageNum = document.getElementById("pageNum");
	index.value = 0;
	pageNum.value = 1;
	pageAjax();
}


function domchk(name){
	var domname = document.getElementsByName(name);
	var idx = null;
	
	if(!domname[0].checked){
		for (var i = 1; i < domname.length; i++) {
			if(domname[i].checked){
				idx = domname[i].value;
			}
		}
	}
	return idx;
}

function arraychk(name){
	var domname = document.getElementsByName(name);
	var idx = [];
	
	if(!domname[0].checked){
		for (var i = 1; i < domname.length; i++) {
			if(domname[i].checked){
				idx.push(domname[i].value);
			}
		}
	}
	return idx;
	
	
}

//---------------------------------관리자 문의 게시판 상세보기------------------------------------------------


function replyview(){
	var div = document.getElementById('replydiv');
	var btn = document.getElementById('replyview');
	if(div.style.display == 'none'){
		div.style.display = 'block';
		btn.innerHTML = '접기';
	}else{
		div.style.display = 'none';
		btn.innerHTML = '답변 보기';
	}
}



//---------------------------------문의 답변------------------------------------------------



