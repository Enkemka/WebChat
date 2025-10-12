package com.example.chatdemo.chatLog.UserFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/")

@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> RegisterUser(@RequestBody UserRegisterDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.ok(userDto);

    }

    @GetMapping("/home")
    public ResponseEntity<Map<String, Object>> homePage(@AuthenticationPrincipal UserDetails user ){
        String username=user.getUsername();
        return ResponseEntity.ok(Map.of("username",username));
    }


    @GetMapping("/test")
    public String test(){
        return "yo whats good";
    }


}
