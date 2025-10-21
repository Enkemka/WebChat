package com.example.chatdemo.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface chatRepo extends MongoRepository<chat, String> {

    chat findChatById(String id);
    chat deleteChatById(String chatId);
}
