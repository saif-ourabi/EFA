package com.example.efabackend.entity;

import java.time.LocalDateTime;
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
    private LocalDateTime time;

    @Column(name = "reply_message", length = 255)
    private String replyMessage;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id") // Assurez-vous que le nom de la colonne est correct
    private Chat chat;

    // Constructeur par défaut
    public Message() {
        this.time = LocalDateTime.now();
    }

    // Constructeur avec les champs senderEmail et replyMessage
    public Message(String senderEmail, String replyMessage) {
        this.senderEmail = senderEmail;
        this.replyMessage = replyMessage;
        this.time = LocalDateTime.now();
    }

    // Constructeur avec tous les champs sauf l'ID (généré automatiquement)
    public Message(String senderEmail, LocalDateTime time, String replyMessage, Chat chat) {
        this.senderEmail = senderEmail;
        this.time = time;
        this.replyMessage = replyMessage;
        this.chat = chat;
    }

    // Getters and Setters

    // Méthode toString()
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderEmail='" + senderEmail + '\'' +
                ", time=" + time +
                ", replyMessage='" + replyMessage + '\'' +
                ", chat=" + chat +
                '}';
    }
}