package com.console.mall.api;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Member;
import com.console.mall.service.CartService;
import com.console.mall.service.ItemService;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/carts")
public class CartAPIController {

    private final ItemService itemService;
    private final CartService cartService;
    private final MemberService memberService;

    @PostMapping("/item")
    public String addItem(HttpSession session, @RequestParam("id") Long itemId, Model model) {
        String id = (String)session.getAttribute("id");
        Item item = itemService.findOneItem(itemId);
        Member member = memberService.findId(id);
        cartService.saveCart(member, item);

        return null;
    }



}
