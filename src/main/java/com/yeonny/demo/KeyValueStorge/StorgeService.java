package com.yeonny.demo.KeyValueStorge;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StorgeService {
    private Map<String, String> storge = new HashMap<>();
    private PriorityQueue<DurationEntry> durationEntries = new PriorityQueue<>(
        (a,b) -> a.getTime().compareTo(b.getTime()));

    public StorgeService() throws Exception{
        loadToFile();
        for(Map.Entry<String, String> obj : storge.entrySet()){
            System.out.println(obj.getKey() + ": " + obj.getValue());
        }
    }

    public void putIn(StorgeDto storgeDto) throws Exception{
        storge.put(storgeDto.key, storgeDto.value);
        saveToFile();
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

    private void saveToFile() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("storge.json"), storge);
    }

    private void loadToFile() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        storge = objectMapper.readValue(
            new File("storge.json"),
            new TypeReference<Map<String, String>>() {} );
    }
}