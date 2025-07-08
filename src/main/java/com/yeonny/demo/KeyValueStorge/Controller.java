package com.yeonny.demo.KeyValueStorge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Controller {
    
    private final StorgeService storgeService;

    @PostMapping("/storge/put-in")
    public void putIn(@RequestBody StorgeDto storgeDto) throws Exception{
        storgeService.putIn(storgeDto);
    }

    @PostMapping("/storge/set-time")
    public void setTime(@RequestBody StorgeDto storgeDto) throws Exception{
        storgeService.setTime(storgeDto);
    }

    @GetMapping("/storge/get-value")
    public String getValue(@RequestParam String key){
        String value = storgeService.getValue(key);
        return value;
    }
}
