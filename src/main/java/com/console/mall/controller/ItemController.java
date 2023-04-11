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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        model.addAttribute("page", 1);
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
    @GetMapping("/admin/item-add")
    public String itemAdd(Model model) {
        List<Category> categoryList = categoryService.findAllCategory();


        model.addAttribute("categoryList", categoryList);

        return "item_admin";
    }

    @PostMapping("/admin/item-save")
    @ResponseBody
    public String adminAddItem(@RequestParam("name") String name,
                               @RequestParam("price") int price,
                               @RequestParam("stockQuantity") int stockQuantity,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("itemInfo") String itemInfo,
                               @RequestParam("itemVideo") String itemVideo,
                               @RequestParam("category_id") Long id) throws IOException {

        byte[] bytes = file.getBytes();
        String fileName =  UriUtils.encode(file.getOriginalFilename(), StandardCharsets.UTF_8);
        String root = System.getProperty("user.dir");
        System.out.println("root = " + root);
        Path path = Paths.get(root + "/src/main/resources/static/img/"  + fileName.replace("\\", "/"));
        Files.write(path, bytes);

        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        item.setImage("/img/"+ fileName);
        item.setItemInfo(itemInfo);
        item.setItemVideo(itemVideo);
        Category category = categoryService.findOneCategory(id);
        item.setCategory(category);
        System.out.println("item.image = " + item.getImage());
        itemService.saveItem(item);
        return null;
    }

    @GetMapping("/item/pagination/{page}")
    public String pagination(@PathVariable("page") String currentPage, Model model) {
        int page = Integer.parseInt(currentPage);
        model.addAttribute("page", page);
        return "item_show";
    }

}
