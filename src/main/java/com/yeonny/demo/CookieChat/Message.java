package com.yeonny.demo.CookieChat;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String id;
    private String clientId;
    private LocalDateTime time;
    private String text;

    public Message(String clientId, String text){
        this.id = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.time = LocalDateTime.now();
        this.text = text;
    }
}
