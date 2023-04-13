package com.console.mall.controller;

import com.console.mall.entitiy.Cart;
import com.console.mall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CartController {

        private final CartService cartService;

    @GetMapping("/cart")
    public String cartList(HttpSession session, Model model) {
        String id = (String)session.getAttribute("id");
        Cart cart = cartService.findByIdCart(id);
        model.addAttribute("cart", cart);
        return "cartList";
    }
}

