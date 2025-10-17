package com.example.chatdemo.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class chatService {

    @Autowired
    private chatRepo chatRepo;

    MongoTemplate mongoTemplate;

    public chat showChat (String chatId){
        return chatRepo.findByChatId(chatId);
    }


    //add new chat
    public chat newChat(chat Chat){
       return chatRepo.save(Chat);

    }


    //deletechatbyId
    public void deleteChat (String chatId){
         chatRepo.deleteChatById(chatId);
    }


    //add user
    public void AddUser(String userId,String chatId){

        Query query = new Query(Criteria.where("_id").is(chatId));
        Update update = new Update().addToSet("userIds", userId);
        mongoTemplate.updateFirst(query, update, chat.class);

    }


    //delete user
    public void deleteUser(String userId,String chatId){
        Query query = new Query(Criteria.where("_id").is(chatId));
        Update update = new Update().pull("userIds", userId);
        mongoTemplate.updateFirst(query, update, chat.class);

    }

    public void AddMessage(Message message, String chatId){

        Query query = new Query(Criteria.where("_id").is(chatId));
        Update update = new Update().addToSet("messagesInChat", message);
        mongoTemplate.updateFirst(query, update, chat.class);

    }


    public void deleteMessage(String messageId,String chatId){
        Query query = new Query(Criteria.where("_id").is(chatId));
        Update update = new Update().pull("messagesInChat", Query.query(Criteria.where("id").is(messageId)));;
        mongoTemplate.updateFirst(query, update, chat.class);

    }





}
