package com.console.mall.respository;
import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Item;
import lombok.RequiredArgsConstructor;
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

    public void save(Cart cart) {
        em.persist(cart);
    }

    public List<Item> findById(Long id) {
        return em.createQuery("select c.item from Cart c where c.member.id =:id")
                .setParameter("id", id)
                .getResultList();
    }
}
