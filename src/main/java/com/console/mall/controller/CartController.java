package com.console.mall.controller;

import com.console.mall.entitiy.Cart;
import com.console.mall.service.CartService;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CartController {

        private final CartService cartService;
        private final MemberService memberService;

    @GetMapping("/cart")
    public String cartList(HttpSession session, Model model) {
        String id = (String)session.getAttribute("id");
        if(id == null){
            model.addAttribute("message", "로그인이 필요한 서비스입니다.");
            return "members/loginForm";
        }
        Long memberId = memberService.getId(id);
        Cart cart = cartService.findByCart(memberId);
        model.addAttribute("cart", cart);
        return "cartList";
    }


}

