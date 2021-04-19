package com.doodlechallenge.chatapi.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ChatController {

    private final ChatService service;

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    public ChatController(ChatService service) {
        this.service = service;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Chat> getChats() {
        return this.service.getChats();
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public void submitChat(@RequestBody Chat chat) {
        Chat savedChat = this.service.addChat(chat);
        this.template.convertAndSend("/topic/chat", savedChat);
    }
}
