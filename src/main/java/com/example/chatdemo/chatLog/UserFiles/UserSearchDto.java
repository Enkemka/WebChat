package com.example.chatdemo.chatLog.UserFiles;

public class UserSearchDto {
    private String Username;
    private String id;



    public UserSearchDto(String username, String id) {
       this. Username = username;
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





}
