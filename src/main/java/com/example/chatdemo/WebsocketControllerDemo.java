package com.example.chatdemo;

import com.example.chatdemo.Chat.chatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketControllerDemo {

    @Autowired
    private chatService chatService;


    @MessageMapping("/chat/{chatId}")// when client sends to /app/chat
    @SendTo("/messageGroup/{chatId}")//broadcasts to topic/mesages
    public Message message(Message message, @DestinationVariable String chatId) {
            chatService.AddMessage(message,chatId);
            return  message;

    }



}
