package com.console.mall.entitiy;

import com.console.mall.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;
    private String login_id;
    private String pw;
    private String phone;


    @Embedded
    private Address address;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> list = new ArrayList<>();
}
