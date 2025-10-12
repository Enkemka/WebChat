package com.example.chatdemo.chatLog.UserFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);



    public User registerUser(UserRegisterDto user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("userName already exists");
        }

        User RegisteredUser = new User(user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getEmail(),
                LocalDateTime.now()
        );

        return userRepo.save(RegisteredUser);
    }

}
