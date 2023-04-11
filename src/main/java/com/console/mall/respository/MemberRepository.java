package com.console.mall.respository;

import com.console.mall.entitiy.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;



@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
       return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select  m from Member m where m.name = :name", Member.class).setParameter( "name", name).getResultList();
    }

    public void deleteById(Long id) {
    }

    public Long findById(String loginId) {
        TypedQuery<Long> query = em.createQuery("SELECT m.id FROM Member m WHERE m.login_id = :login_id", Long.class);
        query.setParameter("login_id", loginId);
        Long id = query.getSingleResult();
      return id;
    }
//    public Member findByLoginid(String login_id){
//        return em.find(Member.class,login_id);
//
//    }
    public List<Member> findByloginid(String loginid){
        return em.createQuery("select  m from Member m where m.login_id = :loginid", Member.class).setParameter( "loginid", loginid).getResultList();
    }

}
