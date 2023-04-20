package com.console.mall.controller;

import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Order;
import com.console.mall.service.OrderService;
import kr.co.bootpay.Bootpay;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;


@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final Bootpay bootpay;

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
        return "orderedItem/form";
    }

    @PostMapping("/orders/delete")
    @ResponseBody
    public void deleteOrder(@RequestParam("id") Long orderId) {
        System.out.println(orderId);
        orderService.deleteOrder(orderId);
    }

}
