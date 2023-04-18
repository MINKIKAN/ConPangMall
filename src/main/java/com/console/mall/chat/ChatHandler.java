//package com.console.mall.chat;
//
//import com.console.mall.entitiy.Chat;
//import com.console.mall.entitiy.ChatRoom;
//import com.console.mall.service.ChatService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.bridge.Message;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//@Component
//@RequiredArgsConstructor
//public class ChatHandler extends TextWebSocketHandler {
//    // 사용자 세션 관리를 위한 Map
//    private final ChatService chatService;
//    private final ObjectMapper objectMapper;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws  Exception {
//        String payload = message.getPayload();
//        System.out.println("payload = " + payload);
//
//        Chat chat = objectMapper.readValue(payload, Chat.class);
//        ChatRoom room = chatService.findById(chat.getRoomId());
//        room.handleActions(session, chat, chatService);
//    }
//
//}
