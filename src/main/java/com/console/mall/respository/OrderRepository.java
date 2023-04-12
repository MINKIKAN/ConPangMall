package com.console.mall.respository;

import com.console.mall.entitiy.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository

public class OrderRepository {

    private final EntityManager em;


    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public List<Order> findByUserId(Long userId) {
        return em.createQuery("SELECT o FROM Order o WHERE o.member = :member", Order.class)
                .setParameter("member", userId)
                .getResultList();
    }

    public Optional<Order> findById(Long orderId) {
        Order order = em.find(Order.class, orderId);
        return Optional.ofNullable(order);
    }

    public Order save(Order order) {
        em.persist(order);
        return order;
    }

    public void deleteById(Long orderId) {
        Order order = em.find(Order.class, orderId);
        em.remove(order);
    }
}
