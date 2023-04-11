package com.console.mall.dto;

import com.console.mall.entitiy.Address;
import com.console.mall.entitiy.Member;
import lombok.*;

import javax.persistence.Embedded;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberDTO {
    private Long id;
    private String name;
    private String email;
    private String login_id;
    private String pw;
    private String phone;
    @Embedded
    private Address address;

}
