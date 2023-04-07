package com.console.mall.dto;

import com.console.mall.entitiy.Category;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String name;
    private String price;
    private int stockQuantity;
    private String image;
    private String itemInfo;

    private Category category;

    public void setPrice(int price) {
        Locale locale = new Locale("ko", "KR");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        String formattedPrice = currencyFormat.format(price);
        this.price = formattedPrice;
    }
}
