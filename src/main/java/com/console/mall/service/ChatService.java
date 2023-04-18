package com.console.mall.service;

import com.console.mall.entitiy.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

//package com.console.mall.service;
//
//import com.console.mall.entitiy.ChatRoom;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//
//import javax.annotation.PostConstruct;
//import java.util.*;
//
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatMessage> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public ChatMessage findById(String roomId) {
        return chatRooms.get(roomId);
    }

    public void save (String roomId, ChatMessage chatMessage) {
        chatRooms.put(roomId, chatMessage);
    }



}
