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
        chatService.save(chatMessage);
        messagingTemplate.convertAndSend("/topic/chatting/" + roomId, chatMessage);
    }

//    @MessageMapping("/chatting/{roomId}")
//    public void subscribe(@DestinationVariable("roomId") String roomId, ChatMessage chatMessage) {
//        messagingTemplate.convertAndSend("/topic/" + roomId, chatMessage);
//    }
//    @MessageMapping("/chat/admin/{userId}")
//    public void joinChatRoom(@PathVariable String userId, @Payload Chat chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        if (chatRooms.get(userId).size() == 0) {
//            List<String> userList = new ArrayList<>();
//            userList.add("admin");
//            chatRooms.put(userId, userList); // 채팅방 ID 생성
//        } else {
//            chatRooms.get(userId).add("admin");
//            Chat joinMessage = new Chat();
//            joinMessage.setType(Chat.MessageType.JOIN);
//            joinMessage.setRoomId(chatMessage.getRoomId());
//            joinMessage.setSender(chatMessage.getSender());
//            joinMessage.setContent("관리자 님이 채팅방에 참여하였습니다.");
//            messagingTemplate.convertAndSendToUser(userId, "/queue/chat", joinMessage);
//        }
//        headerAccessor.getSessionAttributes().put("userId", userId);
//
//
//        // WebSocket 세션에 사용자 ID를 등록합니다.
//
//        // 채팅방에 사용자를 추가합니다.
//
//        // 모든 참여자에게 채팅방에 새로운 사용자가 참여했음을 알립니다.
//
//    }

}

