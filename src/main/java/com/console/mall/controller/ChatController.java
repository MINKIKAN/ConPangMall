package com.console.mall.controller;

import com.console.mall.entitiy.Member;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
        private final MemberService memberService;

    @GetMapping("/chat/list")
    public String chatList(Model model) {
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("memberList", memberList);

        return "chat_list";
    }
}
