package com.example.chatdemo.chatLog.UserFiles;

import com.example.chatdemo.Chat.chat;
import com.example.chatdemo.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final MongoTemplate mongoTemplate;

    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);



    public List<recentChats> showRecentChats (String userId){

        //sort chats by creation date
        //get 5 most recernt

        Aggregation aggregation = Aggregation.newAggregation(

                Aggregation.match(
                        Criteria.where("usersInChatId").in(userId)
                ),

                Aggregation.unwind("messagesInChat"),

                Aggregation.sort(Sort.Direction.DESC, "messagesInChat.creationDate"),

                Aggregation.group("id")
                        .first("chatName").as("chatName")
                        .first("messagesInChat.message").as("recentChatMessage")
                        .first("messagesInChat.senderName").as("recentChatSender")
                        .first("messagesInChat.creationDate").as("time"),

                Aggregation.sort(Sort.Direction.DESC, "time"),

                Aggregation.limit(5)
        );

        AggregationResults<recentChats> results =
                mongoTemplate.aggregate(aggregation, "chat", recentChats.class);

        return results.getMappedResults();
    }












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

    public List<UserSearchDto> userSearch (String name){
        List<User> userSearch = userRepo.findByUsernameContaining(name);




          List<UserSearchDto> dtoList = userSearch.stream()
                 .map(U -> new UserSearchDto(U.getUsername(),U.getId()))
                 .collect(Collectors.toList());

        return dtoList;
    }




    public List<recentChats> findAllUserChats(String userId) {

        Aggregation aggregation = Aggregation.newAggregation(

                Aggregation.match(
                        Criteria.where("usersInChatId").in(userId)
                ),

                Aggregation.unwind("messagesInChat"),

                Aggregation.sort(Sort.Direction.DESC, "messagesInChat.creationDate"),

                Aggregation.group("id")
                        .first("chatName").as("chatName")
                        .first("messagesInChat.message").as("recentChatMessage")
                        .first("messagesInChat.senderName").as("recentChatSender")
                        .first("messagesInChat.creationDate").as("time"),

                Aggregation.sort(Sort.Direction.DESC, "time")

        );

        AggregationResults<recentChats> results =
                mongoTemplate.aggregate(aggregation, "chat", recentChats.class);

        return results.getMappedResults();
    }


    public List<message> getMessages(String chatId, int page) {

        int pageSize = 10;
        int skip = page * pageSize;

        Query query = new Query(Criteria.where("id").is(chatId));

        query.fields().slice("messagesInChat", skip, pageSize);

        chat Chat = mongoTemplate.findOne(query, chat.class);

        if (Chat == null) {
            return List.of();
        }

        return Chat.getMessagesInChat();
    }

}
