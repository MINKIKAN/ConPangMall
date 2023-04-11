package com.console.mall.respository;

import com.console.mall.entitiy.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }
    public  Order findOne(long id){
        return em.find(Order.class, id);
    }
}
