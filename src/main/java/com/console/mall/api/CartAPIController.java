package com.console.mall.api;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.CartItem;
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
        String memberId = (String)session.getAttribute("id");
        if(memberId==null){
            return "check";
        }
        cartService.updateCart(itemId, memberId);

        return null;
    }
    @PostMapping("/delete")
    public String delItem(HttpSession session, @RequestParam("id") Long cartItemId, Model model) {
        String loginId = (String)session.getAttribute("id");
        Member member = memberService.findId(loginId);
        Cart cart = cartService.deleteCart(cartItemId, member);
        model.addAttribute("cart", cart);

        return null;
    }


}
