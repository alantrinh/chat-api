package com.doodlechallenge.chatapi.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository repository;

    @Autowired
    public ChatService(ChatRepository repository) {
        this.repository = repository;
    }

    public List<Chat> getChats() {
        return this.repository.findAll();
    }

    public Chat addChat(Chat chat) {
        chat.setTimestamp(LocalDateTime.now());
        this.repository.save(chat);
        return chat;
    }
}
