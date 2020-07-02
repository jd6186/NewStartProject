var pageAjax = function(){
	
	
	var url = "./UserMBoardPaging.do";
	
	httpRequest = new XMLHttpRequest(); // 서버와 통신
	httpRequest.onreadystatechange = callback // 처리할 함수
	httpRequest.open("POST",url);	// 처리방식, url, 비동기여부(비동기면 true)(select제외 다 동기식으로 해라)
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.send(getParameterValues());			
	
}

function callback(){

	if(httpRequest.readyState == 4){
		//서버와의 통신을 통해 정상적인 작동으로 완료 됐다면 200을 반환
		if(httpRequest.status == 200){

			var result = JSON.parse(httpRequest.responseText);
			
			for(key in result){
				if(key == "lists"){
					var html = '';
					if(result[key].length > 0){
						for(v in result[key]){
						html += "<tr>";
					    html += "<td><input type='checkbox' name='seq' value='"+result[key][v].mounui_seq+"' onclick='chk(this.name)'></td>";
			       		html += "<td>"+result[key][v].mounui_seq+"</td>                                                         ";
			       		html += "<td>"+result[key][v].category_title+"</td>";
			        	html += "<td><a href='./UserMBoardDetail.do?seq="+result[key][v].mounui_seq+"'>"+result[key][v].title+"</a></td>               ";
			        	html += "<td>"+result[key][v].regdate+"</td>";
			        	html += "<td>";
			        	switch (result[key][v].replychk) {
						case 'N': html += '답변 처리 중';  break;
						case 'Y': html += '답변 완료'; break;
						}
			        	html += "</td>";
			      		html += "</tr>";
						}                                                                                                         
					}else{
						//글이 없습니당
						html+=	"<tr>";
						html+=	"<td>글이 존재하지 않습니다...</td>";
						html+=	"</tr>";
					}
					
					document.getElementById('boardlist').innerHTML = html;
					
				}else{
					var html = '';
					html +="<li><a href='#'onclick='pageFirst()'  >&laquo;</a></li>";
				    html +="<li><a href='#' onclick = 'pagePre("+result[key].pageNum+","+result[key].pageList+")'  >&lsaquo;</a></li>";
				    for (var i = result[key].pageNum; i<= result[key].count; i++) {
				    html +="<li><a href='#' onclick='pageIndex("+i+")'>"+i+"</a></li>";
				    }
				    html +="<li><a href='#' onclick='pageNext("+result[key].pageNum+","+result[key].total+","+result[key].listNum+","+result[key].pageList+")'>&rsaquo;</a></li>";
				    html +="<li><a href='#' onclick='pageLast("+result[key].pageNum+","+result[key].total+","+result[key].listNum+","+result[key].pageList+")'>&raquo;</a></li>";
				    
				    document.getElementsByClassName('pagination')[0].innerHTML=html;
				}
				
			}
			
			
		}else{
			alert("오류");
		}
	}
}


function getParameterValues(){
	
	var index = document.getElementById('index').value;
	var pageNum = document.getElementById('pageNum').value;
	var listNum = document.getElementById('listNum').value;
	
	
	return ("index="+index+"&pageNum="+pageNum+"&listNum="+listNum);
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


