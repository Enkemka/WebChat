package com.example.chatdemo.chatLog.UserFiles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
public class User {


    @Id
    private String id;
    private String username;
    private String password;
    private String Email;
    private String creationDate;



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
