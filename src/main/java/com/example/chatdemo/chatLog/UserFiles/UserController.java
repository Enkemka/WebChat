package com.example.chatdemo.chatLog.UserFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/User/")

@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;




    @GetMapping("/home")
    public ResponseEntity<?> homePage(@AuthenticationPrincipal UserDetails user ){
        if (user == null) {
            System.out.println("not wokring");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "User not authenticated"));
        }
        String username=user.getUsername();
        return ResponseEntity.ok(Map.of("username",username));
    }



    //get recent chats


    //settings





}
