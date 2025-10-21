package com.example.chatdemo.Chat;

import com.example.chatdemo.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:3000")
public class chatController {

   @Autowired
    private chatService chatService;

    public chatController(chatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/createChat")
    public ResponseEntity<chat> createChat(@RequestBody String userId) {
        try {

            return ResponseEntity.ok(chatService.newChat(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/{chatId}/view")
    public ResponseEntity<List<message>> viewChat(@PathVariable String chatId) {
        try {
            List<message> Chat = chatService.showChat(chatId);
            return ResponseEntity.ok(Chat);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/deleteChat/{chatId}")
    public ResponseEntity<?> deleteChat(@PathVariable String chatId){
        try{
            chatService.deleteChat(chatId);
            return ResponseEntity.ok("ok");

        }catch(Exception e){

            return ResponseEntity.badRequest().build();
        }
    }


    @PatchMapping("/{chatId}/add")
    public ResponseEntity<?> addMessage(@RequestBody Message message,@PathVariable String chatId){
        //

        try {
            chatService.AddMessage(message,chatId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/{chatId}/remove")

    public ResponseEntity<?> removeMessage(@PathVariable String messageId,@PathVariable String chatId){

        try {
            chatService.deleteMessage(messageId,chatId);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



//usermakes chat
















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



}
