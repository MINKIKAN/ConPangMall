package com.console.mall.respository;

import com.console.mall.entitiy.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRepository {

    private final EntityManager em;


    public List<ChatMessage> findById(String roomId) {
        return em.createQuery("select cm from ChatMessage cm where roomId =:roomId", ChatMessage.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }

    public void save(ChatMessage chatMessage) {
        em.persist(chatMessage);
    }
}
