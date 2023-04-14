package com.console.mall.service;

import com.console.mall.entitiy.*;
import com.console.mall.respository.CartRepository;
import com.console.mall.respository.ItemRepository;
import com.console.mall.respository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;




    public void updateCart(Long itemId, String memberId) {
        Long id = memberRepository.findById(memberId);
        Cart cart = findByCart(id);
        Item item = itemRepository.findOne(itemId);
        CartItem cartItem = CartItem.createCartItem(cart, item);
        cart.addCartItem(cartItem);
        cartRepository.save(cart);
    }

    public Cart findByCart(Long id) {
        return cartRepository.findByIdCart(id);
    }

    public List<Item> findAllItem(Long memberId) {
        Cart cart = findByCart(memberId);
        return cartRepository.allItem(cart.getId());
    }
}
