package com.console.mall.service;

import com.console.mall.entitiy.Order;
import com.console.mall.entitiy.OrderItem;
import com.console.mall.exception.OrderNotFoundException;
import com.console.mall.respository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

//    private final OrderRepository orderRepository;
//
//
//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public List<Order> getOrdersByUserId(Long userId) {
//        return orderRepository.findByUserId(userId);
//    }
//
//    public Order getOrderById(Long orderId) {
//        return orderRepository.findById(orderId)
//                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + orderId));
//    }
//
//    public Order createOrder(Order order) {
//
//        return orderRepository.save(order);
//    }
//
//    public Order updateOrder(Long orderId, Order order ) {
//        // check if the order exists
//        Order existingOrder = getOrderById(orderId);
//
//        // update the order
//        existingOrder.setMember(order.getMember());
//        existingOrder.setOrderItems(order.getOrderItems());
//        existingOrder.setStatus(order.getStatus());
//        return orderRepository.save(existingOrder);
//    }
//
//    public void deleteOrder(Long orderId) {
//        // check if the order exists
//        getOrderById(orderId);
//
//        orderRepository.deleteById(orderId);
//    }
}
