package com.console.mall.controller;

import com.console.mall.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class NoticeConteroller {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String showNotice() {
        return "notice";
    }
}
