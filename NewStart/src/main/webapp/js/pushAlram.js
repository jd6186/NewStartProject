/*
var stompClient = null;

var connect_callback = function() {
    // called back after the client is connected and authenticated to the STOMP server
	alert("클라이언트와 연결이 되었습니다.");
  };



function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
	// 열려있는 SockJS 소켓의 핸들러 위치를 java에 만들어놓은 WebSocketConfig를 통해서 잡아준다.
    // registerStompEndpoints와 연결된다.
	var socket = new SockJS('/pushInsert');
	alert("socket : " + socket);
    stompClient = Stomp.over(socket);
    alert("stompClient : " + stompClient);
    stompClient.connect({}, function(frame) {
    	alert("frame" + frame);
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function(greeting){
            console.log("Message received");
            console.log(greeting.body);
            connect_callback;
            showGreeting(JSON.parse(greeting.body).content);
        });
        alert("showGreeting : " + showGreeting);
    });
}
function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    var admin_seq = $("#admin_seq").val();
    var push_title = $("#push_title").val();
    var push_content = $("#push_content").val();
    var push_accident = $("#push_accident").val();
    var push_am = $("#push_am").val().trim();
    var push_touser = $("#push_touser").val();
    alert("push_content 발송 시작 : " + push_content);
    
    $.ajax({
    	url:"./pushInsert.do",
    	dataType:"json",
    	data: {"admin_seq":admin_seq, "push_title":push_title, "push_content":push_content, "push_accident":push_accident, "push_am":push_am, "push_touser":push_touser}, 
    	// #{admin_seq}, #{push_title}, #{push_content}, #{push_accident}, SYSDATE, #{push_am}, 'U', #{push_touser}
    	success:function(){
    		alert("정상 작동");
    	},
    	error:function(){
    		alert("비정상 작동");
    	},
    });
    stompClient.send("/push/pushpage.do", {}, JSON.stringify({ 'push_content': push_content }));
    alert("pushAlram 발송 시작 : " + stompClient);
    showGreeting(stompClient);
}

function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}

// 발송한 내역 보기
function sendList(){
	location.href = "./sendList.do";
}
*/