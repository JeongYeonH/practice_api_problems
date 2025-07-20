package com.yeonny.demo.LegacyTest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LegacyService {
    private String[][] paths 
    = new String[][] {{"0", "health" },{"1", "NA" },{"2", "NA"},{"3", "reverse"},
                        {"4", "NA"},{"5", "reverse"},{"6", "health"},{"7", "NA"},
                        {"8", "health"},{"9", "reverse"},{"10", "NA"},{"11", "health"}};
    private Map<String, int[]> map = new HashMap<>();

    public String move(LegacyDto legacyDto){
        String name = legacyDto.userName;
        int range = legacyDto.moveRange;
        if(!map.containsKey(name)){
            map.put(name, new int[]{0, 3, 0, 1});
        }
        int[] currUserInfo = map.get(name);
        
        int pos = currUserInfo[0];
        int health = currUserInfo[1];
        int point = currUserInfo[2];
        int dir = currUserInfo[3];
        
        if(health - range < 0){
            return "체력이 부족합니다";
        }
        if(pos + (dir*range) < 0 || pos + (dir*range) > 11){
            return "범위를 벗어났습니다";
        }

        point += range;
        pos += (dir*range);
        String order = paths[pos][1];

        switch (order) {
            case "health":
                health += 3;
                break;
            case "reverse":
                dir *= -1;
                break;
            case "NA":
                break;
            default:
                break;
        }

        map.put(name, new int[] {pos, health, point, dir});    

        return "유저: " + name + ", 위치:채력:점수:방향" + pos+":"+health+":"+point+":"+dir;
    }
}
