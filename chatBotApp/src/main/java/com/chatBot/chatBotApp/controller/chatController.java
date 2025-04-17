package com.chatBot.chatBotApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatBot.chatBotApp.model.userMessage;
import com.chatBot.chatBotApp.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class chatController {

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatMessage chatMessage) {
        // You can use the message to communicate with Rasa or another bot framework
        String botReply = getBotReply(chatMessage.getMessage());  // Get bot's reply (e.g., via Rasa API)

        // Return the response to the frontend
        return new ChatResponse(botReply);
    }

    // Example method to simulate a bot response
    private String getBotReply(String message) {
        // Call Rasa or any other logic here to get a real bot response
        return "You said: " + message;  // Simple mock reply
    }

    // Inner class to represent the message sent by the user
    public static class ChatMessage {
        private String message;

        // Getters and Setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    // Inner class to represent the response from the bot
    public static class ChatResponse {
        private String reply;

        public ChatResponse(String reply) {
            this.reply = reply;
        }

        // Getter
        public String getReply() {
            return reply;
        }
    }
}
