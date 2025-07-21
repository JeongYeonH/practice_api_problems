package com.yeonny.demo.CookieChat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Message> showMessage(MessageDto messageDto){
        String roomName = messageDto.roomId;
        String userName = messageDto.userId;
        String userReadTime = userMap.getOrDefault(
            roomName, new String[]{userName, null})[1];
        LocalDateTime pivotTime; 
        if(userReadTime == null){
            pivotTime = LocalDateTime.MIN;
        }else{
            pivotTime =  LocalDateTime.parse(userReadTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
       
        List<Message> messageList = messageMap.get(roomName);
        messageList.stream()
        .filter(msg -> msg.sendTime.isAfter(pivotTime))
        .collect(Collectors.toList());

        return messageList;
    }
}
