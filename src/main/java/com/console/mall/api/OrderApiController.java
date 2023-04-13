package com.console.mall.api;

//
//import com.console.mall.entitiy.Order;
//import com.console.mall.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/orders")
//public class OrderApiController {
//
//
//    private OrderService orderService;
//
//    @GetMapping("/{userId}")
//    public List<Order> getOrdersByUserId(@PathVariable("userId") Long userId) {
//        return orderService.getOrdersByUserId(userId);
//    }
//
//    @PostMapping("/")
//    public Order createOrder(@RequestBody Order order) {
//        return orderService.createOrder(order);
//    }
//
//    @PutMapping("/{orderId}")
//    public Order updateOrder(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
//        return orderService.updateOrder(orderId, order);
//    }
//
//    @DeleteMapping("/{orderId}")
//    public void deleteOrder(@PathVariable("orderId") Long orderId) {
//        orderService.deleteOrder(orderId);
//    }
//
//}
//
//
