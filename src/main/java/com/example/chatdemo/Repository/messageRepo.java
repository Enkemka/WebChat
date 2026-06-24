package com.example.chatdemo.Repository;

import com.example.chatdemo.Entity.message;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface messageRepo extends JpaRepository<message,Long> {
    Optional<message> findById(long id);
}
