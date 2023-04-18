package com.console.mall.entitiy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String content;
    private String senderId;

    public ChatMessage(String content, String senderId) {
        this.content = content;
        this.senderId = senderId;
    }
}
