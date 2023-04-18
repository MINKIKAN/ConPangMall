package com.console.mall.respository;

import com.console.mall.entitiy.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;
    public void save(Item item) {
        em.persist(item);
    }
    public Item findOne(Long id) {
//        return em.createQuery("select i from Item i where i.id =:id", Item.class)
//                .setParameter("id", id)
//                .getResultList();
        return em.find(Item.class,id);
    }

    public List<Item> getList(Long id, int start, int recordSize) {
        List<Item> itemList = em.createQuery("select i from Item i Join i.category c where c.id = :id", Item.class)
                .setParameter("id", id)
                .setFirstResult(start)
                .setMaxResults(recordSize)
                .getResultList();
        return itemList;
    }

//    public Long allCount(Long id) {
//        return em.createQuery("SELECT COUNT(i) FROM Item i Join i.category c where c.id =:id", Long.class)
//                .setParameter("id", id)
//                .getSingleResult();
//    }
public Long allCount(Long id) {
    return em.createQuery("SELECT COUNT(i) FROM Item i WHERE i.category.id =:id", Long.class)
            .setParameter("id", id)
            .getSingleResult();
}


}
