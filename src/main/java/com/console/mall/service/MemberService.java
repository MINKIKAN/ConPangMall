package com.console.mall.service;

import com.console.mall.entitiy.Member;
import com.console.mall.respository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Member> findMembers = memberRepository.findByName(member.getName());
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

    public Long getId(String loginId) {
        Long id = memberRepository.findById(loginId);
        return id;
    }

    @Transactional
    public void update(Long id, String name, String email, String login_id, String pw, String phone) {
        Member member = memberRepository.findOne(id); // 영속성 컨텍스트가 저장
        member.setName(name); // 이름값 자동 수정 -> jpa updqte 쿼리문을 실행
        member.setEmail(email);
        member.setLogin_id(login_id);
        member.setPw(pw);
        member.setPhone(phone);
    }

}
