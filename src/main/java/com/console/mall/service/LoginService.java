//package com.console.mall.service;
//
//import com.console.mall.entitiy.Member;
//import com.console.mall.respository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import javax.persistence.*;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class LoginService {
//    @Autowired
//    private final MemberRepository memberRepository;
//    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb2");
//
//    public boolean login(String loginId, String password) {
//        // EntityManager
//        EntityManager em = emf.createEntityManager();
//        // 로그인 성공 여부
//        boolean success = false;
//
//        try {
//            // 로그인 정보 확인 쿼리
//            TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.login_id = :loginId AND m.pw = :password", Member.class);
//            query.setParameter("loginId", loginId);
//            query.setParameter("password", password);
//
//            // 로그인 성공 여부 판단
//            if (!query.getResultList().isEmpty()) {
//                success = true;
//            }
//        } finally {
//            // EntityManager 반환
//            em.close();
//        }
//
//        return success;
//    }
//}
