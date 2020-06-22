// 채팅방 리스트 오픈
function chatListOpen(){
	$.ajax({
		url:"./chatConnect.do",
		type:"get",
		dataType:"json",
		data:{"gonggo_seq":""},
		success:function(map_L){
			alert("기능작동 시작합니다.");
			var list = map_L.chatRoom_list;
			html = "<div style='width:300px; height:600px; position:absolute; left:50%; top:50%; margin-left:-150px; margin-top:-150px;'>"
			html = "<button id='exit'>채팅방 나가기</button>"
			for (var i = 0; i < list.length; i++) {
				html += "<input type='text' value='"+ list[i]['chattitle'] +"' readonly='readonly' style='width:100%' onclick='chatRoom_Con("+list[i]['chat_seq']+")'>";		
			}
			html += "</div>"
			$("body").html(html);
			//location.href = "./gonggo.do";
			$('#exit').click(function(){
				setTimeout(function(){
					// 1초 후 작동해야할 코드
					window.location.reload();
				}, 1000);
			});
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});
}




function chatRoom_Con(chat_seq) {
	location.href="./broadsocket.do?chat_seq="+chat_seq;
}