package com.example.chatdemo.Chat;

import com.example.chatdemo.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class chatService {

    @Autowired
    private chatRepo chatRepo;

    MongoTemplate mongoTemplate;




    //add new chat
    public chat newChat(String userId){
        List<String> usersInChat = new ArrayList<String>();
        usersInChat.add(userId);
        chat Chat = new chat(usersInChat);

        return chatRepo.save(Chat);

    }

    public List<message> showChat (String chatId){
        chat Chat = chatRepo.findChatById(chatId);
       return Chat.getMessagesInChat();
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
