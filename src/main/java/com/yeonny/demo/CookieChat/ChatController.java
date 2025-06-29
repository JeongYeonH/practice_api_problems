package com.yeonny.demo.CookieChat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {
    
    private final ChatService chatService;

    @PostMapping("/chat/send")
    public void send(
        @RequestBody MessageDto messageDto){
        chatService.sendMessage(messageDto.roomId, messageDto.clientId, messageDto.text);
    }

    @GetMapping("/chat/show")
    public List<Message> show(
        @RequestParam String roomId,
        @RequestParam String clientId){
        List<Message> messages = chatService.getUnreadMessage(clientId, roomId);
        return messages;
    }

    @GetMapping("/chat/mark")
    public void markAsRead(
        @RequestParam String roomId,
        @RequestParam String clientId){
        chatService.markAsRead(roomId, clientId);
    }
}
