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
    public static MemberDTO toMemberDTO(Member member){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setName(member.getName());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setLogin_id(member.getLogin_id());
        memberDTO.setPw(member.getPw());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setAddress(member.getAddress());
        return memberDTO;
    }
}
