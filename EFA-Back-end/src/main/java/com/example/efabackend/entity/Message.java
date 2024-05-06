package com.example.efabackend.entity;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 45)
    private Long id;

    @Column(name = "sender_email", length = 255)
    private String senderEmail;

    @Column(name = "time")
    private Date time = new Date(System.currentTimeMillis());

    @Column(name = "reply_message", length = 255)
    private String replyMessage;

    // Constructeurs
    public Message() {
    }

    public Message(String senderEmail, Date time, String replyMessage) {
        this.senderEmail = senderEmail;
        this.time = time;
        this.replyMessage = replyMessage;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderEmail='" + senderEmail + '\'' +
                ", time=" + time +
                ", replyMessage='" + replyMessage + '\'' +
                '}';
    }
}