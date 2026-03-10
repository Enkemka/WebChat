package com.example.chatdemo;

import com.example.chatdemo.chatLog.UserFiles.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface messageRepo extends MongoRepository<message, String> {
    message  findByid(String id);
}
