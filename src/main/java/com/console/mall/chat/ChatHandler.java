package com.console.mall.chat;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatHandler extends TextWebSocketHandler {
    // 사용자 세션 관리를 위한 Map
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    // 클라이언트와 연결되었을 때 실행되는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 세션 ID를 사용자의 이름으로 설정하여 Map에 저장
        String username = (String) session.getAttributes().get("username");
        sessions.put(username, session);

        // 관리자와의 1:1 채팅 시작
        if ("admin".equals(username)) {
            session.sendMessage(new TextMessage("관리자와의 1:1 채팅을 시작합니다."));
        } else {
            WebSocketSession adminSession = sessions.get("admin");
            if (adminSession != null && adminSession.isOpen()) {
                adminSession.sendMessage(new TextMessage(username + "님이 1:1 채팅을 요청합니다."));
            } else {
                session.sendMessage(new TextMessage("현재 관리자가 온라인이 아닙니다."));
            }
        }
    }

    // 클라이언트가 서버로 메시지를 보냈을 때 실행되는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지를 보낸 사용자 이름과 메시지 내용 추출
        String username = (String) session.getAttributes().get("username");
        String payload = message.getPayload();

        // 관리자에게 메시지 전송
        WebSocketSession adminSession = sessions.get("admin");
        if (adminSession != null && adminSession.isOpen()) {
            adminSession.sendMessage(new TextMessage(username + ": " + payload));
        }

        // 보낸 사용자에게 메시지 전송
        session.sendMessage(new TextMessage("Me: " + payload));
    }

    // 클라이언트와 연결이 끊겼을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 사용자 세션 제거
        String username = (String) session.getAttributes().get("username");
        sessions.remove(username);
    }
}
