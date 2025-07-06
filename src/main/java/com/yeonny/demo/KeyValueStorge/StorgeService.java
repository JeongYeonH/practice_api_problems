package com.yeonny.demo.KeyValueStorge;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StorgeService {
    private final Map<String, String> storge = new HashMap<>();
    private final Map<String, LocalDateTime> duration = new HashMap<>();

    public void putIn(StorgeDto storgeDto){
        storge.put(storgeDto.key, storgeDto.value);
    }

    public void setTime(StorgeDto storgeDto){
        LocalDateTime result = LocalDateTime.now().plusSeconds(storgeDto.seconds);
        duration.put(storgeDto.key, result);
    }

    public String getValue(String key){
        String value = storge.get(key);
        return value;
    }

    @Scheduled(fixedDelay = 1000)
    public void delete(){
        LocalDateTime now = LocalDateTime.now();
        for(Entry<String, LocalDateTime> entry : duration.entrySet()){
            if(entry.getValue().isBefore(now)){
                duration.remove(entry.getKey());
                storge.remove(entry.getKey());
                System.out.println("다음 저장소가 삭제됨: " + entry.getKey());
            }
        }
    }
}
