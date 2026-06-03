package com.example.chatdemo;

import com.example.chatdemo.chatLog.UserFiles.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface messageRepo extends JpaRepository<message,String> {
    Optional<message> findById(long id);
}
