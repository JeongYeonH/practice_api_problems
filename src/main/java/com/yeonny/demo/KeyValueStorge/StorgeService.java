package com.yeonny.demo.KeyValueStorge;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class StorgeService {
    private Map<String, String> storge = new HashMap<>();
    private PriorityQueue<DurationEntry> durationEntries = new PriorityQueue<>(
        (a,b) -> a.getTime().compareTo(b.getTime()));

    public StorgeService() throws Exception{
        loadToFile();
    }

    public void putIn(StorgeDto storgeDto) throws Exception{
        storge.put(storgeDto.key, storgeDto.value);
        saveToFile();
    }

    public void setTime(StorgeDto storgeDto) throws Exception{
        DurationEntry durationEntry = new DurationEntry(storgeDto);
        durationEntries.offer(durationEntry);
        saveToFile();
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
            System.out.println("다음이 삭제되었습니다: " + entry.getKey());
        }
    }


    private void saveToFile() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("storge.json"), storge);
        
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.writeValue(new File("queue.json"), durationEntries);
    }

    private void loadToFile() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        storge = objectMapper.readValue(
            new File("storge.json"),
            new TypeReference<Map<String, String>>() {} );

        objectMapper.registerModules(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<DurationEntry> entryList = objectMapper.readValue(
            new File("queue.json"),
            new TypeReference<List<DurationEntry>>() {});
        
        durationEntries.addAll(entryList);
    }
}