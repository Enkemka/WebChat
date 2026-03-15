package com.example.chatdemo.Chat;

import com.example.chatdemo.chatLog.UserFiles.recentChats;
import com.example.chatdemo.chatLog.UserFiles.userPrincipal;
import com.example.chatdemo.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

//

    @GetMapping("/all-chats")
    public List<recentChats> getAllChats(@AuthenticationPrincipal userPrincipal user) {
        String userId = user.getId();
        return chatService.getAllUserChats(userId);
    }





    @PostMapping("/createChat")
    public ResponseEntity<chat> createAChat(@RequestBody String userId) {
        try {
            return ResponseEntity.ok(chatService.newChat(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    //view speicfic chat and all messages clicked on as chat object
    @GetMapping("/{chatId}/view")
    public ResponseEntity<List<message>> viewChat(@PathVariable String chatId) {

        //if session user id is in chat as one of the users

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
    public ResponseEntity<?> addMessage(@RequestBody message Message,@PathVariable String chatId){
        //

        try {
            chatService.addMessage(Message,chatId);
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
            chatService.addUser(userId,chatId);
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
