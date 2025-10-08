package com.example.chatdemo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketControllerDemo {


    @MessageMapping("/chat")// when client sends to /app/chat
    @SendTo("/topic/messages")//broadcasts to topic/mesages
    public String message(String message) {
        return message;
    }



}
