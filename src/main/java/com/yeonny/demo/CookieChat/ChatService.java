package com.yeonny.demo.CookieChat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final Map<String, List<Message>> messageMap = new HashMap<>();
    private final Map<RoomClientKey, LocalDateTime> clientsRead = new HashMap<>();

    public void sendMessage(String roomId, String clientId, String text){
        Message mg = new Message(clientId, text);
        if(!messageMap.containsKey(roomId)){
            List<Message> mgList = new ArrayList<>();
            mgList.add(mg);
            messageMap.put(roomId, mgList);
        }else{
            List<Message> existMgList = messageMap.get(roomId);
            existMgList.add(mg);
        }

    }

    public void markAsRead(String roomId, String clientId){
        RoomClientKey key = new RoomClientKey(clientId, roomId);
       
        clientsRead.put(key, LocalDateTime.now());
        System.out.println("읽은 시점: "+ clientsRead.get(key));
    }

    public List<Message> getUnreadMessage(String clientId, String roomId){
        List<Message> all = messageMap.getOrDefault(roomId, new ArrayList<>());
        RoomClientKey key = new RoomClientKey(clientId, roomId);
        LocalDateTime lastRead = clientsRead.get(key);
        System.out.println("읽은 시점 값: "+lastRead);
        System.out.println(all);
        if(lastRead == null) return all;

        List<Message> unRead = new ArrayList<>();
        for(Message mgs : all){
            if(mgs.getTime().isAfter(lastRead)){
                unRead.add(mgs);
            }
        }
        return unRead;
    }
}
