package com.example.chatdemo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chatRepo extends MongoRepository<chat, String> {


    chat deleteChatById(String chatId);
}
