package com.example.chatdemo.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chatRepo extends MongoRepository<chat, String> {

    chat findByChatId(String chatId);
    chat deleteChatById(String chatId);
}
