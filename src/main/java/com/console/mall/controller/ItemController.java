package com.console.mall.controller;

import com.console.mall.entitiy.Category;
import com.console.mall.entitiy.Item;
import com.console.mall.entitiy.Review;
import com.console.mall.respository.ItemRepository;
import com.console.mall.service.CategoryService;
import com.console.mall.service.ItemService;
import com.console.mall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final ReviewService reviewService;
    @GetMapping("/item/id={id}")
    public String itemShow(@PathVariable("id") Long id, Model model) {

        Category category = categoryService.findOneCategory(id);
        List<Item> itemList = category.getItemList();
        model.addAttribute("itemList", itemList);
        return "item_show";
    }

    @GetMapping("/item/info/{id}")
    public String itemInfo(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findOneItem(id);
        model.addAttribute("item", item);
        List<Review> reviewList = item.getList();
        System.out.println("review size" + reviewList.size());
        model.addAttribute("reviewList", reviewList);
        return "item_info";
    }
    @GetMapping("/item/admin")
    public String itemAdd() {
        return "item_admin";
    }
}
