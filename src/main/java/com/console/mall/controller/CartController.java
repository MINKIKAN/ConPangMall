package com.console.mall.controller;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.CartItem;
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
        Long memberId = memberService.getId(id);
        Cart cart = cartService.findByCart(memberId);
        model.addAttribute("cart", cart);
        return "cartList";
    }


}

