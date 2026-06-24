package com.example.chatdemo.chatLog.UserFiles;

import com.example.chatdemo.Entity.User;
import com.example.chatdemo.Entity.message;
import com.example.chatdemo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


//switch iwith exists by id
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


//pagenation for showing recent chat

    public List<recentChats> showRecentChats(String userId, int limit) {


    }













//exists by id
    public List<UserSearchDto> userSearch (String name){

    }









//find all chats containing useer name, from maybe a custom query in repo

    public List<recentChats> findAllUserChats(String userId) {


    }






    //getting messages(pagenation)

    public List<message> getMessages(String chatId, int page) {



}
