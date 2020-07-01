<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Socket Example</title>
</head>
<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<script type="text/javascript" src="./js/chatting.js"></script>
<div id="modify">
	<form>
		<!-- 유저 명을 입력하는 텍스트 박스(기본 값은 anonymous(익명)) -->
		<input type="hidden" value="${chat_seq}" id="chat_seq">
		<div class='form-group'>
			<label for='content'>유저 이름</label>
			<br/>
			<input id="user" type="text" value="연결된 유저 : ${user_seq}" style="width: 100%" readonly="readonly">
		</div>
	<br/>
		<div class='form-group'>
			<!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.	) -->
			<label for='content'>채팅 내역</label>
			<textarea class="form-control"  id="messageTextArea" rows="10" cols="50" readonly="readonly" style="background-color: white;">${content}</textarea>
		</div>
		
		<!-- 송신 메시지를 작성하는 텍스트 박스 -->
		<div class='form-group'>
			<label for='content'>채팅 작성</label>
			<br/>
			<input id="textMessage" type="text" style="width: 100%">
			<!-- 메세지를 송신하는 버튼 -->
			<input onclick="sendMessage()" value="보내기" type="button" class="btn btn-success">
			<input onclick="disconnect()" value="Disconnect" type="button" class="btn btn-info" style="text-align: right;">
		</div>
	</form>
</div>	
	
	
<script type="text/javascript">
	// 「WebSocketEx」는 프로젝트 명
	// 「broadsocket」는 호스트 명
	
	// WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
	var webSocket = new WebSocket("ws://localhost:8093/NewStart/broadsocket");
	
	
	// 콘솔 텍스트 에리어 오브젝트
	var messageTextArea = document.getElementById("messageTextArea");
	
	
	// WebSocket 서버와 접속이 되면 호출되는 함수
	webSocket.onopen = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		messageTextArea.value += "\n...\n";
	};
	
	// WebSocket 서버와 접속이 끊기면 호출되는 함수
	webSocket.onclose = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		messageTextArea.value += "Server Disconnect...\n";
	};
	
	// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
	webSocket.onerror = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		messageTextArea.value += "error...\n";
	};
	
	/// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
	webSocket.onmessage = function(message) {
		// 콘솔 텍스트에 메시지를 출력한다.
		messageTextArea.value += message.data + "\n";
	};
	
	// Send 버튼을 누르면 호출되는 함수
	function sendMessage() {
		// 유저명 텍스트 박스 오브젝트를 취득
		var user = document.getElementById("user");
		// 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득
		var message = document.getElementById("textMessage");
		// 콘솔 텍스트에 메시지를 출력한다.(내가 보낸 메시지)
		messageTextArea.value += user.value + "(me) => " + message.value + "\n";
		
		// 유저가 작성한 내용 db에 업데이트하기
		// WebSocket 서버에 메시지를 전송(형식 「{{유저명}}메시지」)
		webSocket.send("{{" + user.value + "}}" + message.value);
		// 송신 메시지를 작성한 텍스트 박스를 초기화한다.
		var chat_seq = $("#chat_seq").val();
		var messageTextArea_val = $("#messageTextArea").val();
		alert("chat_seq : " + chat_seq);
		alert("messageTextArea_val : " + messageTextArea_val);
		$.ajax({
			url:"./chatUpdate.do",
			type:"post",
			dataType:"json",
			data:{"messageTextArea_val" : messageTextArea_val, "chat_seq" : chat_seq},
			success:function(){
				alert("전송에 성공하였습니다.");
			},
			error:function(){
				alert("전송에 실패하였습니다.");
			}
		});
	}
	
	// Disconnect 버튼을 누르면 호출되는 함수
	function disconnect() {
		// WebSocket 접속 해제
		webSocket.close();
	}
</script>

</body>
</html>