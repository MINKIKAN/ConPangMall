package com.console.mall.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ChatMessage {

    @Id
    private String roomId;
    private String content;
    private String senderId;

}
