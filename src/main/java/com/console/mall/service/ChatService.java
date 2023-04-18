package com.console.mall.service;

import com.console.mall.entitiy.ChatMessage;
import com.console.mall.respository.ChatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
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
@Transactional
public class ChatService {

    private final ChatRepository chatRepository;
    public List<ChatMessage> findById(String roomId) {
        return chatRepository.findById(roomId);
    }

    public void save (ChatMessage chatMessage) {
        chatRepository.save(chatMessage);
    }



}
