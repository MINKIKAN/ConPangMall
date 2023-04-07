package com.console.mall.form;

import com.console.mall.entitiy.Category;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
public class ItemForm {
    private Long id;
    private String name;
    private String price;
    private int stockQuantity;
    private String image;
    private String itemInfo;
    private String itemVideo;

    private Category category;

}
