package com.console.mall.respository;

import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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
        return em.createQuery("SELECT i FROM Member m, Order o, OrderItem oi, Item i WHERE m.id = o.member.id AND o.id = oi.order.id AND oi.item.id = i.id AND m.id = :memberId", Item.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
                                   //"SELECT i FROM Order o JOIN o.list oi JOIN oi.item i WHERE o.member.id = :memberId"


//    System.out.println("repo = "+orderId);

    public void deleteById(Long orderId){
        // 오더 아이템 삭제 쿼리
        String deleteOrderItemQuery = "delete from OrderItem oi where oi.order.id = :id";
        em.createQuery(deleteOrderItemQuery)
                .setParameter("id", (int)(long)orderId)
                .executeUpdate();

        // 오더 삭제 쿼리
        String deleteOrderQuery = "delete from Order o where o.id = :id";
        em.createQuery(deleteOrderQuery)
                .setParameter("id", (int)(long)orderId)
                .executeUpdate();
    }

//
}
