package com.yeonny.demo.KeyValueStorge;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DurationEntry {
    private String key;
    private LocalDateTime time;

    public DurationEntry(StorgeDto storgeDto){
        this.key = storgeDto.key;
        this.time = LocalDateTime.now().plusSeconds(storgeDto.seconds); 
    }
}
