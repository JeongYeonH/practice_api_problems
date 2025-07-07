package com.yeonny.demo.KeyValueStorge;



import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StorgeService {
    private final Map<String, String> storge = new HashMap<>();
    private final PriorityQueue<DurationEntry> durationEntries = new PriorityQueue<>(
        (a,b) -> a.getTime().compareTo(b.getTime()));

    public StorgeService(){
        loadToFile();
    }

    public void putIn(StorgeDto storgeDto){
        storge.put(storgeDto.key, storgeDto.value);
    }

    public void setTime(StorgeDto storgeDto){
        DurationEntry durationEntry = new DurationEntry(storgeDto);
        durationEntries.offer(durationEntry);
    }

    public String getValue(String key){
        String value = storge.get(key);
        return value;
    }

    @Scheduled(fixedDelay = 1000)
    public void delete(){
        LocalDateTime now = LocalDateTime.now();
        while(!durationEntries.isEmpty() && durationEntries.peek().getTime().isBefore(now)){
            DurationEntry entry = durationEntries.poll();
            storge.remove(entry.getKey());
        }
    }

    private void saveToFile(){
        
    }

    private void loadToFile(){

    }
}