package com.example.chatdemo.Chat;

import com.example.chatdemo.message;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;


import java.util.List;

@Entity
@Table(name = "User")
public class Chat {

    @Id
    private String id;

    @NotBlank
    @Size(min = 3, max = 25)
    private String name;


    private List<String> usersInChatId;


    private List<message> messagesInChat;


    public Chat(List<String>usersInChat) {
        this.usersInChatId = usersInChat;
    }

    public Chat(String id, String name, List<String>usersInChat, List<message> messagesInChat) {
        this.id = id;
        this.name = name;
        this.usersInChatId = usersInChat;
        this.messagesInChat = messagesInChat;
    }

    public Chat() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
