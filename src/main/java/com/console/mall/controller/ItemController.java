package com.console.mall.controller;

import com.console.mall.entitiy.Category;
import com.console.mall.entitiy.Item;
import com.console.mall.respository.ItemRepository;
import com.console.mall.service.CategoryService;
import com.console.mall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final CategoryService categoryService;
    @GetMapping("/item/id={id}")
    public String item(@PathVariable("id") Long id, Model model) {

        Category category = categoryService.findOneCategory(id);
        List<Item> itemList = category.getItemList();
        model.addAttribute("itemList", itemList);
        return "item_show";
    }
}
