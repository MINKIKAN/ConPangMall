package com.console.mall.respository;

import com.console.mall.entitiy.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;
    public Review findOne(Long id) {
        return em.find(Review.class, id);
    }

    public List<Review> findAll() {
        return em.createQuery("select r from Review r",Review.class).getResultList();

    }

    public void deleteReview(Long writer){
        Review review = em.find(Review.class, writer);
        em.remove(review);

    }

    public void writeReivew(Review review){
        em.persist(review);
    }



}
