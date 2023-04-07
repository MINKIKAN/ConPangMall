package com.console.mall.controller;

import com.console.mall.entitiy.Review;
import com.console.mall.service.MemberService;
import com.console.mall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberService memberService;

    @PostMapping("/review/show/{id}")
    public String showReview(@PathVariable Long id, Model model) {
        Review review = reviewService.findOneReview(id);
        model.addAttribute("review", review);
        return null;
    }
}
