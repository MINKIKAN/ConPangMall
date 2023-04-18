package com.console.mall.controller;

import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Review;
import com.console.mall.service.ItemService;
import com.console.mall.service.MemberService;
import com.console.mall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberService memberService;
    private final ItemService itemService;

    @PostMapping("/review/show/{id}")
    public String showReview(@PathVariable Long id, Model model) {
        Review review = reviewService.findOneReview(id);
        model.addAttribute("review", review);
        return null;
    }
    @PostMapping("/reviews")
    public String addReview(
            @RequestParam("title")String title,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer,
            @RequestParam("itemId") Long itemId,
            HttpSession session,
            Model model
    ) {
        Review review = new Review();
        review.setTitle(title);
        review.setContent(content);
        review.setWriter(writer);
        System.out.println("itemID = " + itemId);
        System.out.println("itemID = " + itemId);
        System.out.println("itemID = " + itemId);
        System.out.println("itemID = " + itemId);
        System.out.println("itemID = " + itemId);
        System.out.println("itemID = " + itemId);
        Item item = itemService.findOneItem(itemId);
        List<Review> reviewList = item.getList();
        reviewList.add(review);
        item.setList(reviewList);
        itemService.saveItem(item);
        review.setItem(item);
        System.out.println("item= " + item);
        System.out.println("item= " + item);
        System.out.println("item= " + item);
        System.out.println("item= " + item);
        reviewService.saveReview(review);
        return "redirect:/orderedItem/form";
    }



    @GetMapping("/reviews/new")
    public String showReviewForm() {
        return "Cart";
    }

}
