package com.example.efabackend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {

    public static final String SEQUENCE_NAME = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Integer chatId;

    @Column(name = "first_user_name", length = 255)
    private String firstUserName;

    @Column(name = "second_user_name", length = 255)
    private String secondUserName;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messageList;

    // Constructors
    public Chat() {
    }

    public Chat(String firstUserName, String secondUserName, List<Message> messageList) {
        this.firstUserName = firstUserName;
        this.secondUserName = secondUserName;
        this.messageList = messageList;
    }

    // Getters and Setters
    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getFirstUserName() {
        return firstUserName;
    }

    public void setFirstUserName(String firstUserName) {
        this.firstUserName = firstUserName;
    }

    public String getSecondUserName() {
        return secondUserName;
    }

    public void setSecondUserName(String secondUserName) {
        this.secondUserName = secondUserName;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    // toString method
    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", firstUserName='" + firstUserName + '\'' +
                ", secondUserName='" + secondUserName + '\'' +
                ", messageList=" + messageList +
                '}';
    }
}
