package com.console.mall.respository;

import com.console.mall.entitiy.Member;
import com.console.mall.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberService service;
@Test
    void 멤버출력(){
    System.out.println("=====================================================");
    List<Member> list = service.findMembers();
    for(Member m : list){
        System.out.println("m = " + m);
    }
   }
}