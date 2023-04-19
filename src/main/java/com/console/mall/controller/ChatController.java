package com.console.mall.controller;

import com.console.mall.entitiy.ChatMessage;
import com.console.mall.entitiy.Member;
import com.console.mall.service.ChatService;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MemberService memberService;
    private final ChatService chatService;
    private final Map<String, List<String>> chatRooms = new HashMap<>() {
    }; // 채팅방을 저장할 맵

    private final SimpMessagingTemplate messagingTemplate;
    @GetMapping("/chat/list")
    public String chatList(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("memberList", memberList);
        return "chat_list";
    }
    @GetMapping("/chat/{roomId}")
    public String enterChatRoom(@PathVariable("roomId") String roomId, HttpSession session, Model model) {
        List<ChatMessage> list = chatService.findById(roomId);



        model.addAttribute("list", list);
        model.addAttribute("roomId", roomId);
        model.addAttribute("memberId", (String)session.getAttribute("id"));
        return "chat";
    }

    @MessageMapping("/chatting/{roomId}")
    public void send(@DestinationVariable("roomId") String roomId, ChatMessage chatMessage) {

        if (chatMessage.getType() == ChatMessage.MessageType.JOIN) { // 새로운 사용자가 접속했을 경우
            ChatMessage joinMessage = new ChatMessage();
            joinMessage.setType(ChatMessage.MessageType.CHAT);
            joinMessage.setSenderId(chatMessage.getSenderId());
            joinMessage.setContent(chatMessage.getSenderId() + "님이 들어왔습니다.");
            messagingTemplate.convertAndSend("/topic/chatting/" + roomId, joinMessage);
        } else if (chatMessage.getType() == ChatMessage.MessageType.EXIT){ // 일반적인 채팅 메시지인 경우
            ChatMessage leaveMessage = new ChatMessage();
            leaveMessage.setType(ChatMessage.MessageType.CHAT);
            leaveMessage.setSenderId(chatMessage.getSenderId());
            leaveMessage.setContent(chatMessage.getSenderId() + "님이 나갔습니다.");
            messagingTemplate.convertAndSend("/topic/chatting/" + roomId, leaveMessage);
        } else {
            chatService.save(chatMessage);
            messagingTemplate.convertAndSend("/topic/chatting/" + roomId, chatMessage);
        }
    }


}

