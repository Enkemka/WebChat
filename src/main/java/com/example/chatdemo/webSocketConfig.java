package com.example.chatdemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;





import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.StompEndpointRegistry;



@Configuration
@EnableWebSocketMessageBroker
public class webSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint clients use to connect (e.g., ws://localhost:8080/chat)
        registry.addEndpoint("/chat")  .setAllowedOriginPatterns("*").withSockJS();


    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // clients subscribe here
        registry.enableSimpleBroker("/topic");

       // client sends here
        registry.setApplicationDestinationPrefixes("/app");

       /* registry.setUserDestinationPrefix("/user");*/
    }

}
