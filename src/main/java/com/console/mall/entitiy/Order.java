package com.console.mall.entitiy;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> list = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member; // 구매자
//
//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItems = new ArrayList<>();
//
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
//    private LocalDate createDate; // 구매 날짜
//
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status; // 주문상태 [ORDER, CANCEL]
//    @PrePersist
//    public void createDate() {
//        this.createDate = LocalDate.now();
//    }
//
//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }
//
//    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
//        Order order = new Order();
//        order.setMember(member);
//        for (OrderItem orderItem : orderItemList) {
//            order.addOrderItem(orderItem);
//        }
//        order.setCreateDate(order.createDate);
//        return order;
//    }
//
//    public void setQuantity(int quantity) {
//        this.orderItems.forEach(orderItem -> orderItem.setCount(quantity));
//    }
//


}