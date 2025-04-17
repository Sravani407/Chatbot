package com.chatBot.chatBotApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatBot.chatBotApp.model.userMessage;
import com.chatBot.chatBotApp.service.*;

@RestController
@RequestMapping("/api/chat")
public class chatController {

    @Autowired
    private rasaService rasaService;

    @PostMapping
    public ResponseEntity<?> chat(@RequestBody userMessage userMessage) {
        String botReply = rasaService.sendMessageToRasa(userMessage.getMessage());
        return ResponseEntity.ok(botReply);
    }
}
