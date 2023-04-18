package com.console.mall.entitiy;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Chat {
    public enum MessageType {
        ENTER, COMM
    }
    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
}
