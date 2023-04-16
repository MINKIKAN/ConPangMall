package com.console.mall.respository;

import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.CartItem;
import com.console.mall.entitiy.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {
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

    public Cart findByIdCart(Long id) {
        return (Cart) em.createQuery("select c from Cart c where c.member.id =:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Item> allItem(Long id) {
        return em.createQuery("SELECT i FROM Cart c JOIN c.list ci JOIN Item i ON ci.item.id = i.id WHERE c.id = :cartId", Item.class)
                .setParameter("cartId", id)
                .getResultList();
    }

    public void delete(Long cartItemId) {
        em.createQuery("DELETE FROM CartItem ci WHERE ci.id =:cartItemId")
                .setParameter("cartItemId", cartItemId)
                .executeUpdate();
    }
    public void deleteAll(Long cartId) {
        em.createQuery("DELETE FROM CartItem ci where ci.cart.id = :cartId")
                .setParameter("cartId", cartId)
                .executeUpdate();
    }
}
