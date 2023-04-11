package com.console.mall.entitiy;

import com.console.mall.dto.MemberDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    public static Member toMember(MemberDTO memberDTO){
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setLogin_id(memberDTO.getLogin_id());
        member.setPw(memberDTO.getPw());
        member.setPhone(memberDTO.getPhone());
        return member;
    }


}
