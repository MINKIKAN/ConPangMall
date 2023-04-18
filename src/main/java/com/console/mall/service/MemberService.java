package com.console.mall.service;

import com.console.mall.dto.MemberDTO;
import com.console.mall.entitiy.Address;
import com.console.mall.entitiy.Cart;
import com.console.mall.entitiy.Member;
import com.console.mall.respository.CartRepository;
import com.console.mall.respository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

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
        List<Member> findEmail = memberRepository.isEmail(member.getEmail());
         if(!findMembers.isEmpty()) {
             throw new IllegalStateException("이미 존재하는 아이디입니다.");

        }
         if(!findEmail.isEmpty()){
             throw  new IllegalStateException("이미 존재하는 이메일 입니다");
         }


    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findId(String id) {
        return memberRepository.findId(id);
    }

    public Long getId(String loginId) {
        Long id = memberRepository.findById(loginId);
        return id;
    }


    @Transactional

    public Member login(MemberDTO memberDTO) {
        Member member = new Member();
        member.setLogin_id(memberDTO.getLogin_id());
        member.setPw(memberDTO.getPw());
        List<Member> list = memberRepository.findByloginid(member);
        if (list.size() == 0) {
            return null;
        }
        Member m = memberRepository.findByloginid(member).get(0);
        return m;
    }

    @Transactional
    public int delete(String id, String pw) {
        Member login = new Member();
        login.setLogin_id(id);
        login.setPw(pw);
        List<Member> member = memberRepository.findByloginid(login);
        if (member.size() == 0) {
            return 0;
        }
        memberRepository.delete(member.get(0));
        return 1;
    }

    @Transactional
    public void update(String id, String name, String city, String street, String zipcode, String email, String phone, String pw) {
        Member member = memberRepository.findId(id);
        Address address = new Address(city, street, zipcode);
        member.setName(name);
        member.setAddress(address);
        member.setEmail(email);
        member.setPhone(phone);
        member.setPw(pw);

        memberRepository.save(member);
    }



    public Cart findByCart(String id) {
        return memberRepository.findByIdCart(id);
    }
    @Transactional
    public void delete(Member member){
        memberRepository.delete(member);
    }

}
