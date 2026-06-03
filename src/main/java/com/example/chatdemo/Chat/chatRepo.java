package com.example.chatdemo.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface chatRepo extends JpaRepository<Chat, String> {

    Optional<Chat> findChatById(String id);
    Chat deleteChatById(String chatId);
}
