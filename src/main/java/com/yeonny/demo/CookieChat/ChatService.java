package com.yeonny.demo.CookieChat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private Map<String, String[]> userMap = new HashMap<>();
    private Map<String, List<Message>> messageMap = new HashMap<>();

    public String sendMessage(MessageDto messageDto){
        String roomName = messageDto.roomId;
        Message message = new Message(roomName , messageDto.text);

        List<Message> messageList = messageMap.getOrDefault(roomName, new ArrayList<>());
        messageList.add(message);
        messageMap.put(roomName , messageList);

        return "메시지가 전송되었습니다";
    }
}
