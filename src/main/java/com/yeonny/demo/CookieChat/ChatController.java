package com.yeonny.demo.CookieChat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    
    @PostMapping("/chat/send")
    public String sendMessage(@RequestBody MessageDto messageDto){
        String response = chatService.sendMessage(messageDto);
        return response;
    }

    @GetMapping("chat/show-message")
    public List<Message> showMessage(@RequestBody MessageDto messageDto){
        List<Message> list = chatService.showMessage(messageDto);
        return list;
    }
}
