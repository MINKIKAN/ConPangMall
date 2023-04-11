package com.console.mall.entitiy;

import com.console.mall.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String image;

    @Column(columnDefinition = "LONGTEXT")
    private String itemInfo;
    private String itemVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Review> list = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public List<OrderItem> getOrderItemList() {
        List<OrderItem> orderItemList = new ArrayList<>();
        this.getOrderItemList().forEach(orders -> {
            for (OrderItem orderItem : orders.getItem().getOrderItemList()) {
                if (orderItem.getItem().equals(this)) {
                    orderItemList.add(orderItem);
                }
            }
        });
        return orderItemList;
    }
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 없습니다.");
        }
        this.stockQuantity = restStock;
    }
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
}
