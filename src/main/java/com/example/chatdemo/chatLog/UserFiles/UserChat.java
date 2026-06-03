package com.example.chatdemo.chatLog.UserFiles;

import com.example.chatdemo.Chat.Chat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserChat {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private String role; // ADMIN, MEMBER

    private LocalDateTime joinedAt;
}
