package com.console.mall.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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

}
