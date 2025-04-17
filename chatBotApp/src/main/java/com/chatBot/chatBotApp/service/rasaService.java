package com.chatBot.chatBotApp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class rasaService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendMessageToRasa(String message) {
        String url = "http://localhost:5005/webhooks/rest/webhook";
        
        Map<String, String> payload = new HashMap<>();
        payload.put("sender", "user1");
        payload.put("message", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.POST, request, List.class);
        if (!response.getBody().isEmpty()) {
            Map firstReply = (Map) response.getBody().get(0);
            return (String) firstReply.get("text");
        }

        return "I didn't understand that.";
    }
}

