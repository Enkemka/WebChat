package com.example.chatdemo.Chat;

import com.example.chatdemo.Entity.Chat;
import com.example.chatdemo.Repository.chatRepo;
import com.example.chatdemo.chatLog.UserFiles.recentChats;
import com.example.chatdemo.Entity.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class chatService {

    @Autowired
    private chatRepo chatRepo;

   /* @Autowired
    MongoTemplate mongoTemplate;

    public List<recentChats> getAllUserChats (String userId){
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("usersInChatId").in(userId)),
                Aggregation.unwind("messagesInChat"),
                Aggregation.sort(Sort.Direction.DESC, "messagesInChat.creationDate"),
                Aggregation.group("_id")
                        .first("chatName").as("chatName")
                        .first("messagesInChat.message").as("recentChatMessage")
                        .first("messagesInChat.senderName").as("recentChatSender")
                        .first("messagesInChat.creationDate").as("time"),
                Aggregation.sort(Sort.Direction.DESC, "time")
        );

        AggregationResults<recentChats> results =
                mongoTemplate.aggregate(aggregation, "chat", recentChats.class);

        return results.getMappedResults();
    }


    public Chat newChat(String userId, String chatName) {



    }

    // Get all messages from a chat
    public List<message> showChat(String chatId) {







    public Chat viewChat(String chatId) {
        return chatRepo.findChatById(chatId);
    }


    // Delete a chat
    public void deleteChat(String chatId) {


    }

    // Add a message to a chat


    // Remove message from chat
    public void deleteMessage(String messageId, String chatId) {


    }

    // Add a user to chat
    public void addUser(String userId, String chatId) {


    }

    // Remove user from chat
    public void deleteUser(String userId, String chatId) {


    }

    // Check if user belongs to chat
    public boolean isUserInChat(String userId, String chatId) {


    }*/
}



