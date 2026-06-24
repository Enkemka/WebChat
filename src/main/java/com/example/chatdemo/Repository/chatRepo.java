package com.example.chatdemo.Repository;

import com.example.chatdemo.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface chatRepo extends JpaRepository<Chat, Long> {

    Optional<Chat> findChatById(String id);
    Chat deleteChatById(String chatId);
}
