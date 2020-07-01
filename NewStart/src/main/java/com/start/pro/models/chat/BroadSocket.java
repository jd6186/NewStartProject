package com.start.pro.models.chat;

	import java.io.IOException;

	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import javax.websocket.OnClose;
	import javax.websocket.OnMessage;
	import javax.websocket.OnOpen;
	import javax.websocket.Session;
	import javax.websocket.server.ServerEndpoint;
	
	
	@ServerEndpoint("/broadsocket")
	public class BroadSocket {
	// WebSocket 호스트 설정
		
	// 접속 된 클라이언트 WebSocket session 관리 리스트
	private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	
	// 메시지에서 유저 명을 취득하기 위한 정규식 표현(테스트 때에 필요)
	// 나중에 유저 고유정보를 받아서 작업하게 될 경우에는 필요가 없다. 식별이 가능해야된다는 뜻
	// 여기서 나온 Pattern이라는 객체는 Regex라는 객체로 자바 Regex, 
	//  Regular Expression ( 정규 표현식 )은 검색이나, 문자열을 처리하기 위한 패턴을 정의하는 API 입니다. 
	//  이것은 패스워드나 이메일 확인 같은 문자열의 제한조건을 나타내기 위해 넓게 사용됩니다.
	private static Pattern pattern = Pattern.compile("^\\{\\{.*?\\}\\}");
	
	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void handleOpen(Session userSession) {
	// 클라이언트가 접속하면 WebSocket세션을 리스트에 저장한다.
	sessionUsers.add(userSession);
	// 콘솔에 접속 로그를 출력한다.
	System.out.println("client is now connected...");
	}
	
	
	// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
			// 메시지 내용을 콘솔에 출력한다.
			System.out.println(message);

			// 초기 유저 명
			String name = "새출발";

			// 메시지로 유저 명을 추출한다.
			Matcher matcher = pattern.matcher(message);

			// 메시지 예: {{유저명}}메시지
			// 연결된 사람들이 있는지 판단해서 '새출발' 지정한 이름으로 부여해준다.
			if (matcher.find()) {
				name = matcher.group();
			}
			
			
			// 클로져를 위해 변수의 상수화
			final String msg = message.replaceAll(pattern.pattern(), "");
			
			// 지금 이름을 가져와서 정규식표현을 거쳐 username을 지정한 name으로 변경한다.
			// 시큐리티를 통해 유저가 정해져 있다면 그냥 받아주시면 되는 부분
			final String username = name.replaceFirst("^\\{\\{", "").replaceFirst("\\}\\}$", "");
			
			
			// session관리 리스트에서 Session을 취득한다.(람다식 표현 방법)
			sessionUsers.forEach(session -> {
				
				// 리스트에 있는 세션과 메시지를 보낸 세션이 같으면 메시지 송신할 필요없다.
				if (session == userSession) {
					return;
				}
			
				try {
					// 리스트에 있는 모든 세션(메시지 보낸 유저 제외)에 메시지를 보낸다. (형식: 유저명 => 메시지)
					session.getBasicRemote().sendText(" => " + msg);
				} catch (IOException e) {
					// 에러가 발생하면 콘솔에 표시한다.
						e.printStackTrace();
				}
			}
		);
	}
	
	
	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose(Session userSession) {
		// session 리스트로 접속 끊은 세션을 제거한다.
		sessionUsers.remove(userSession);
		// 콘솔에 접속 끊김 로그를 출력한다.
		System.out.println("사용자님이 채팅방을 나가셨습니다....");
	}
}
