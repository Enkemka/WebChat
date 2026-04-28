package com.example.chatdemo.Chat;

import com.example.chatdemo.chatLog.UserFiles.recentChats;
import com.example.chatdemo.message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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

    @Autowired
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


    public chat newChat(String userId, String chatName) {

        String id = new ObjectId().toString();   // generate Mongo-style id

        List<String> usersInChat = new ArrayList<>(List.of(userId));

        List<message> messages = new ArrayList<>();

        chat newChat = new chat(id, chatName ,usersInChat, messages);

        return mongoTemplate.save(newChat);
    }

    // Get all messages from a chat
    public List<message> showChat(String chatId) {

        Query query = new Query(Criteria.where("_id").is(chatId));

        chat chat = mongoTemplate.findOne(query, chat.class);

        if (chat == null) {
            throw new RuntimeException("Chat not found");
        }

        return chat.getMessagesInChat();
    }

    public chat viewChat(String chatId) {
        return chatRepo.findChatById(chatId);
    }


    // Delete a chat
    public void deleteChat(String chatId) {

        Query query = new Query(Criteria.where("_id").is(chatId));

        mongoTemplate.remove(query, chat.class);
    }

    // Add a message to a chat
    public void addMessage(message message, String chatId) {

        message.setChatId(chatId);

        Query query = new Query(Criteria.where("_id").is(chatId));

        Update update = new Update().push("messagesInChat", message);

        mongoTemplate.updateFirst(query, update, chat.class);
    }

    // Remove message from chat
    public void deleteMessage(String messageId, String chatId) {

        Query query = new Query(Criteria.where("_id").is(chatId));

        Update update = new Update().pull(
                "messagesInChat",
                Query.query(Criteria.where("_id").is(messageId))
        );

        mongoTemplate.updateFirst(query, update, chat.class);
    }

    // Add a user to chat
    public void addUser(String userId, String chatId) {

        Query query = new Query(Criteria.where("_id").is(chatId));

        Update update = new Update().addToSet("usersInChatId", userId);

        mongoTemplate.updateFirst(query, update, chat.class);
    }

    // Remove user from chat
    public void deleteUser(String userId, String chatId) {

        Query query = new Query(Criteria.where("_id").is(chatId));

        Update update = new Update().pull("usersInChatId", userId);

        mongoTemplate.updateFirst(query, update, chat.class);
    }

    // Check if user belongs to chat
    public boolean isUserInChat(String userId, String chatId) {

        Query query = new Query(
                Criteria.where("_id").is(chatId)
                        .and("usersInChatId").in(userId)
        );

        return mongoTemplate.exists(query, chat.class);
    }
}



