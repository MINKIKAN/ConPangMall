package com.console.mall.respository;
import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository  {
    private final EntityManager em;

    Cart findByCartIdAndItemId(int cartId, int itemId) {
        return null;
    }

    Cart findCartItemById(int id) {
        return null;
    }

    List<Cart> findCartItemByItemId(int id) {
        return null;
    }
}
