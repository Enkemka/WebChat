package com.example.chatdemo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "message")
public class message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat")
    private Long chatId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Long senderId;

    @NotBlank
    private String senderName;

    @NotBlank
    private String message;

    @CreatedDate
    private String sendDate;



    public message( Long chatId, Long senderId, String senderName, String message ,  String sendDate) {

        this.chatId = chatId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.sendDate =  sendDate;
    }

    public message() {

    }


    public Long getId() {
        return id;
    }



    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreationDate() {
        return sendDate;
    }

    public void setCreationDate(String creationDate) {
        this.sendDate = creationDate;
    }




}
