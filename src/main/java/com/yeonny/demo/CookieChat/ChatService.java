package com.yeonny.demo.CookieChat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private Map<String, String[]> userMap = new HashMap<>();
    private Map<String, List<Message>> messageMap = new HashMap<>();
}
