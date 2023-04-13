package com.console.mall.entitiy;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    private int count; // 상품 개수

 

    // 이미 담겨있는 물건 또 담을 경우 수량 증가
    public void addCount(int count) {
        this.count += count;
    }

}