package com.console.mall.entitiy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 구매자

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> list = new ArrayList<>();

    private int totalPrice;

    public void addCartItem(CartItem cartItem) {
        list.add(cartItem);
        cartItem.setCart(this);
    }

    public void setTotalPrice() {
        int totalPrice = 0;
        for (CartItem ci : list) {
            totalPrice += ci.getItem().getPrice();
        }
        this.totalPrice = totalPrice;
    }
}

