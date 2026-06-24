package com.example.chatdemo.Repository;

import com.example.chatdemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface userChatRepo extends JpaRepository<User, Long> {
}
