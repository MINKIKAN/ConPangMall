package com.console.mall.api;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Item;
import com.console.mall.service.CartService;
import com.console.mall.service.ItemService;
import com.console.mall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartAPIController {

    private final ItemService itemService;
    private final CartService cartService;

    @PostMapping("/item")
    public String addItem(HttpSession session, @RequestParam("id") Long itemId, Model model) {
        String id = (String)session.getAttribute("id");
        Cart cart = cartService.findByIdCart(id);
        Item item = itemService.findOneItem(itemId);
        cart.getItemList().add(item);
        cartService.saveCart(cart);

        return null;
    }



}
