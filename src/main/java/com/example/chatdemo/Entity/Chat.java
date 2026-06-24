package com.example.chatdemo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class Chat {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 25)
    private String chatName;

    @CreatedDate
    private Date creationDate;

    @OneToMany(mappedBy = "UserChat_id")
    private List<UserChat> userChats = new ArrayList<>();


    public Chat(String chatName, Date creationDate) {

        this.chatName = chatName;
        this.creationDate = creationDate;

    }


    public Chat() {

    }
}
