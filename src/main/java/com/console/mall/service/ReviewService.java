package com.console.mall.service;

import com.console.mall.entitiy.Review;
import com.console.mall.respository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAllReview() {
        List<Review> list = reviewRepository.findAll();
        return list;
    }
    public Review findOneReview(Long id) {
        Review review = reviewRepository.findOne(id);
        return review;
    }
    public void saveReview(Review review) {
        reviewRepository.writeReivew(review);
    }
}
