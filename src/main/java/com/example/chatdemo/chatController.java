package com.example.chatdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:3000")
public class chatController {

    @Autowired
    private chatService chatService;

    public chatController(chatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/createChat")
    public ResponseEntity<chat> createChat(@RequestBody chat Chat){
        try{
            chatService.newChat(Chat);
        return ResponseEntity.ok(Chat);

            }catch(Exception e){

            return ResponseEntity.badRequest().build();
        }

}

//usermakes chat












    @DeleteMapping("/deleteChat/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable String chatId){
        try{
            chatService.deleteChat(chatId);
            return ResponseEntity.ok("ok");

        }catch(Exception e){

            return ResponseEntity.badRequest().build();
        }
    }




    @PatchMapping("/{chatId}/add/{userId}")
    public ResponseEntity<?> addUser(@PathVariable String userId,@PathVariable String chatId){
        try {
            chatService.AddUser(userId,chatId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/{chatId}/delete/{userId}")
    public ResponseEntity<?> DeleteUser(@PathVariable String userId,@PathVariable String chatId){
        try {
            chatService.deleteUser(userId,chatId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("{chatId}/addMessage/")
    public ResponseEntity<?> addMessage(@RequestBody Message message,@PathVariable String chatId){
        try {
            chatService.AddMessage(message,chatId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("{chatId}/DeleteMessage/{messageId}")
    public ResponseEntity<?> removeMessage(@PathVariable String messageId,@PathVariable String chatId){
        try {
            chatService.deleteMessage(messageId,chatId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
