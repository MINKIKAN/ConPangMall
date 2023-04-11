package com.console.mall.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 구매자

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="item_id")
    //private Item item;

//    private int item_id; // 주문 상품 번호
//    private String itemName; // 주문 상품 이름
//    private int itemPrice; // 주문 상품 가격
//    private int itemCount; // 주문 상품 수량
//    private int itemTotalPrice; // 가격*수량

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item; // 주문상품에 매핑되는 판매상품

    private int isCancel; // 주문 취소 여부 (0:주문완료 / 1:주문취소)

    // 장바구니 전체 주문
    public static OrderItem createOrderItem(int itemId, Member member, CartItem cartItem, Item item) {
        OrderItem orderItem = new OrderItem();
        orderItem.item = item;

        return orderItem;
    }

    // 상품 개별 주문


}


