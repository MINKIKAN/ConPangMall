package com.console.mall.service;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Member;
import com.console.mall.entitiy.Order;
import com.console.mall.respository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;




    public void saveCart(Member member, Item item) {
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItem(item);
        cartRepository.save(cart);
    }

    public List<Item> findByCart(Long id) {
        return cartRepository.findById(id);
    }
}
