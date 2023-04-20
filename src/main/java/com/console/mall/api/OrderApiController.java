package com.console.mall.api;


import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.CartItem;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Order;
import com.console.mall.service.CartService;
import com.console.mall.service.ItemService;
import com.console.mall.service.MemberService;
import com.console.mall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;
    private final MemberService memberService;

    @PostMapping("/add")
    public String addOrder(@RequestParam("memberId") Long memberId, Model model) {
        orderService.saveOrder(memberId);
        return null;
    }

}
