package com.console.mall.service;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.CartItem;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Member;
import com.console.mall.respository.CartRepository;
import com.console.mall.respository.ItemRepository;
import com.console.mall.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Cart cart = cartRepository.findByIdCart(id);
        cart.setTotalPrice();
        return cart;
    }

    public Cart findAllItem(Long memberId) {
        Cart cart = findByCart(memberId);
        List<Item> itemList = cartRepository.allItem(cart.getId());
        int idx = 0;
        List<CartItem> list = cart.getList();
        for (CartItem ci : list) {
            ci.setItem(itemList.get(idx));
            idx++;
        }
        cart.setList(list);
        System.out.println("totalPrice = " + cart.getTotalPrice());
        System.out.println("totalPrice = " + cart.getTotalPrice());
        System.out.println("totalPrice = " + cart.getTotalPrice());
        return cart;
    }

    public Cart deleteCart(Long cartItemId, Member member) {
        cartRepository.delete(cartItemId);
        Cart cart = cartRepository.findByIdCart(member.getId());
        return cart;
    }


}
