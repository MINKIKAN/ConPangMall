package com.console.mall.controller;

import com.console.mall.dto.PaginationDTO;
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

    @GetMapping("/item")
    public String itemShow(@RequestParam(value = "id") Long id,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "total", defaultValue = "0") Long totalPages,
                           Model model) {
        if (totalPages == 0) {
            totalPages = (itemService.allCount(id));
        }
        PaginationDTO paginationDTO = new PaginationDTO(page, totalPages);
        List<Item> itemList = itemService.findAllItem(id, paginationDTO);

        model.addAttribute("itemList", itemList);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("id", id);
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
        String fileName = UriUtils.encode(file.getOriginalFilename(), StandardCharsets.UTF_8);
        String root = System.getProperty("user.dir");
        System.out.println("root = " + root);
        Path path = Paths.get(root + "/src/main/resources/static/img/" + fileName.replace("\\", "/"));
        Files.write(path, bytes);

        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        item.setImage("/img/" + fileName);
        item.setItemInfo(itemInfo);
        item.setItemVideo(itemVideo);
        Category category = categoryService.findOneCategory(id);
        item.setCategory(category);
        System.out.println("item.image = " + item.getImage());
        itemService.saveItem(item);
        return null;
    }


    @GetMapping("/item/prev")
    public String itemPrev(@RequestParam(value = "id") Long id,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "total", defaultValue = "0") Long totalPages,
                           Model model) {

        PaginationDTO paginationDTO = new PaginationDTO(page, totalPages);
        List<Item> itemList = itemService.findAllItem(id, paginationDTO);
        int pageSize = paginationDTO.getPageSize();
        int startPage = 0;
        if (page / pageSize == 0 || page == pageSize) {
            page--;
        }
        startPage = page / pageSize * pageSize + 1;

        int endPage = startPage + pageSize - 1;
        paginationDTO.setPage(page);
        paginationDTO.setStartPage(startPage);
        paginationDTO.setEndPage(endPage);

        model.addAttribute("itemList", itemList);
        model.addAttribute("pagination", paginationDTO);
        model.addAttribute("id", id);
        return "item_show";
    }
}
