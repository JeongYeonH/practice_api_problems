package com.yeonny.demo.LegacyTest;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LegacyController {

    private final LegacyService legacyService;
    
    @PostMapping("/legacy/move")
    public String move(@RequestBody LegacyDto legacyDto){
        String respone = legacyService.move(legacyDto);
        return respone;
    }
}
