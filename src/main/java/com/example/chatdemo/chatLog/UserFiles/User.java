package com.example.chatdemo.chatLog.UserFiles;

import com.example.chatdemo.Chat.Chat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "User")
public class User {


    @Id
    private String id;



    @NotBlank
    @Size(min = 3, max = 30)
    private String username;


    private String password;


    @Email
    @NotBlank
    @Size(min = 3, max = 50)

    private String Email;

    @CreatedDate
    private String creationDate;


    //private List<User> FriendsList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_chats",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private List<Chat>chatList;

    @Enumerated(EnumType.STRING)
    private ROLE role;


    //@NotBlank
    //private String profilePictureUrl;


   /* public User(String username, String password, String Email, LocalDateTime creationDate) {
        this.username = username;
        this.password = password;
        this.Email = Email;
        this.creationDate = creationDate.toString();
        this.profilePictureUrl = "defulatPFP.url"

    }*/







    public User(String username, String password, String Email, LocalDateTime creationDate) {
        this.username = username;
        this.password = password;
        this.Email = Email;
        this.creationDate = creationDate.toString();

    }








    public User( String username, String password, String email, String creationDate) {
        this.username = username;
        this.password = password;
        this.Email = email;
        this.creationDate = creationDate;
    }









    public User() {

    }

    public User(String username, String encode, String email, LocalDateTime now, String email1) {

    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }




    public User( String username, String password, String creationDate) {
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
    }

    public User( String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
