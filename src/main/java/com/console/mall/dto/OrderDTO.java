package com.console.mall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long orderItemId;
    private String itemNum;
    private int price;
    private int count;
    private String imgUrl;

    public OrderDTO(Long orderItemId, String itemNum, int price, int count, String imgUrl) {
        this.orderItemId = orderItemId;
        this.itemNum = itemNum;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
