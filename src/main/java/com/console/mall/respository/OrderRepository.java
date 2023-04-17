package com.console.mall.respository;

import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;
    public void save(Order order) {
        em.persist(order);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o",Order.class).getResultList();
    }

    public List<Item> findByMemberId(Long memberId) {
        return em.createQuery("SELECT i FROM Order o JOIN o.list oi JOIN oi.item i  WHERE o.member.id = :memberId", Item.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
                                   //"SELECT i FROM Order o JOIN o.list oi JOIN oi.item i WHERE o.member.id = :memberId"


//
}
