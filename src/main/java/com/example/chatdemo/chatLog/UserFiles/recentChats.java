package com.example.chatdemo.chatLog.UserFiles;

import com.example.chatdemo.message;

public class recentChats {



    private String chatId;
    private String chatName;
    private String recentChatMessage;
    private String recentChatSender;
    private String time;


    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getRecentChatMessage() {
        return recentChatMessage;
    }

    public void setRecentChatMessage(String recentChatMessage) {
        this.recentChatMessage = recentChatMessage;
    }

    public String getRecentChatSender() {
        return recentChatSender;
    }

    public void setRecentChatSender(String recentChatSender) {
        this.recentChatSender = recentChatSender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
