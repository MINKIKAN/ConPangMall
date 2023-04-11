package com.console.mall.controller;

import com.console.mall.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {


    private final ItemService itemService;
    @GetMapping("/admin/item_add")
    public String itemAdd() {
        return "item_admin";
    }
}
