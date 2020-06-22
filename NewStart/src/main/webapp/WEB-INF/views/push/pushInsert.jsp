<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%-- 
<head>
    <title>Hello WebSocket</title>
    <script src="./js/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <script type="text/javascript" src="./js/pushAlram.js"></script>
    
</head>

<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div>
    <div>
        <button id="connect" onclick="connect();">서버 연결</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">서버 연결 해제</button>
    </div>
    <div>
    	<label>발송 사건 선택</label>
    	<select id="push_accident">
    		<option>경고</option>
    		<option>이벤트</option>
    	</select>
    	<label>자동/수동 선택</label>
    	<select id="push_am">
    		<option>A</option>
    		<option>M</option>
    	</select>
    </div>
    <div id="conversationDiv">
    	<label>보내는 관리자 번호</label><br/>
    	<input id="admin_seq" type="text" value="${admin}" readonly="readonly"><br/>
    	<label>받을 사용자 선택</label><br/>
    	<input type="text" id="push_touser"><br/>
    	<label>푸시 알림 제목</label><br/>
    	<input id="push_title" type="text"><br/>
        <label>보낼 알람 내용을 입력하세요</label><br/>
        <input type="text" id="push_content" /><br/>
        <button id="sendName" onclick="sendName();">푸시 알림 발송</button>
        <button id="sendName" onclick="sendList();">발송 내역 보기</button>
		<textarea rows="" cols="" id="messages">
		<
		</textarea>
        <p id="response"></p>
    </div>
</div>
</body>
 --%>
</html> 