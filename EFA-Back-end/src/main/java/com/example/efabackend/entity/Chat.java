package com.example.efabackend.entity;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "chat")
public class Chat {

    public static final String SEQUENCE_NAME = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "first_user_name", length = 255)
    private String firstUserName;

    @Column(name = "second_user_name", length = 255)
    private String secondUserName;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    
    private List<Message> messageList;

    // Constructeurs
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

    public void setChatId(int i) {
        this.chatId = i;
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

    // Méthode toString()
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Chat{")
                .append("chatId=").append(chatId)
                .append(", firstUserName='").append(firstUserName != null ? firstUserName : "null").append('\'')
                .append(", secondUserName='").append(secondUserName != null ? secondUserName : "null").append('\'');
        if (messageList != null) {
            stringBuilder.append(", messageList=[");
            for (Message message : messageList) {
                stringBuilder.append("\n\t").append(message.toString());
            }
            stringBuilder.append("\n]");
        } else {
            stringBuilder.append(", messageList=null");
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
    
    
}
