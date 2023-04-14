package com.console.mall.controller;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Member;
import com.console.mall.service.CartService;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequiredArgsConstructor
public class CartController {

        private final CartService cartService;
        private final MemberService memberService;

    @GetMapping("/cart")
    public String cartList(HttpSession session, Model model) {
        String id = (String)session.getAttribute("id");

        Member member = memberService.findId(id);

        List<Item> itemList = cartService.findAllItem(member.getId());

        int totalPrice = getTotalPrice(itemList);

        model.addAttribute("itemList", itemList);
        model.addAttribute("member", member);
        model.addAttribute("totalPrice", totalPrice);
        return "cartList";
    }

    private int getTotalPrice(List<Item> itemList) {
        AtomicInteger totalPrice = new AtomicInteger();
        itemList.forEach(i -> {
            totalPrice.addAndGet(i.getPrice());
        });
        return totalPrice.get();
    }
}

