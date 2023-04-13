package com.console.mall.service;

import com.console.mall.controller.MemberForm;
import com.console.mall.dto.MemberDTO;
import com.console.mall.entitiy.Address;
import com.console.mall.entitiy.Member;
import com.console.mall.respository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.isId(member.getLogin_id());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
    public  Member findId(String id){
        return memberRepository.findId(id);
    }
    public Long getId(String loginId) {
        Long id = memberRepository.findById(loginId);
        return id;
    }

@Transactional

    public Member login(MemberDTO memberDTO){
        Member member = new Member();
        member.setLogin_id(memberDTO.getLogin_id());
        member.setPw(memberDTO.getPw());
        List<Member> list = memberRepository.findByloginid(member);
        if(list.size() == 0) {
            return null;
        }
        Member m = memberRepository.findByloginid(member).get(0);
        return m;

    }
    @Transactional
    public void update(String id,String name,String city,String street,String zipcode,String email,String phone,String pw){
        Member member =memberRepository.findId(id);
        Address address = new Address(city,street,zipcode);
        member.setName(name);
        member.setAddress(address);
        member.setEmail(email);
        member.setPhone(phone);
        member.setPw(pw);

        memberRepository.save(member);
    }


}
