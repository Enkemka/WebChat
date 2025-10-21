package com.example.chatdemo.Chat;

import com.example.chatdemo.message;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "chat")
public class chat {

    @Id
    private String id;
    private List<String> usersInChatId;
    private List<message> messagesInChat;


    public chat(List<String>usersInChat) {
        this.usersInChatId = usersInChat;
    }

    public chat(String id,List<String>usersInChat,List<message> messagesInChat) {
        this.id = id;
        this.usersInChatId = usersInChat;
        this.messagesInChat = messagesInChat;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public List<String> getUsersInChatId() {
        return usersInChatId;
    }

    public void setUsersInChat(List<String> usersInChatId) {
        this.usersInChatId = usersInChatId;
    }

    public List<message> getMessagesInChat() {
        return messagesInChat;
    }

    public void setMessagesInChat(List<message> messagesInChat) {
        this.messagesInChat = messagesInChat;
    }


}
