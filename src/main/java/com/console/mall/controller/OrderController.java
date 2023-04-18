package com.console.mall.controller;

import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Order;
import com.console.mall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/list")
    public String showOrderList(Model model) {
        List<Order> orderList = orderService.getAllOrder();
        model.addAttribute("orderList", orderList);
        return "orderList";
    }
    @GetMapping("/orderedItem/form")
    public String form(HttpSession session, Model model) {
        String id=(String) session.getAttribute("id");
        List<Item> itemList = orderService.getOneByUserId(id);
        model.addAttribute("itemList", itemList);
        return "/orderedItem/form";
    }


}
