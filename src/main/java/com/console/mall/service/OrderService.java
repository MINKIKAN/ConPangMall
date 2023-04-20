package com.console.mall.service;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Member;
import com.console.mall.entitiy.Order;
import com.console.mall.respository.CartRepository;
import com.console.mall.respository.MemberRepository;
import com.console.mall.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;


    public void saveOrder(Long memberId) {
        Cart cart = cartRepository.findByIdCart(memberId);
        Member member = memberRepository.findOne(memberId);
        Order order = new Order();
        order.setMember(member);


//        List<OrderItem> orderItemList = new ArrayList<>();
//        for (CartItem ci : cart.getList()) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setItem(ci.getItem());
//            orderItem.setOrder(order);
//            orderItemList.add(orderItem);
//        }
//        order.setList(orderItemList);
//        order.setTotalPrice(cart.getTotalPrice());
//        List<CartItem> cartItemList = new ArrayList<>();
//        cart.setList(cartItemList);
        cartRepository.deleteAll(cart.getId());
        orderRepository.save(order);
    }

    public List<Order> getAllOrder() {

        return orderRepository.findAll();
    }

    public List<Item> getOneByUserId (String id){
        Long memberId = memberRepository.findById(id);
        return orderRepository.findByMemberId(memberId);
    }

    public void deleteOrder(Long orderId) {
        System.out.println("service"+orderId);
        orderRepository.deleteById(orderId);
    }

}
