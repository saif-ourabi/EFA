package com.example.efabackend.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "database_sequences")
public class DatabaseSequence {
 @Id
    @Column(name = "id")
    private String id;

    @Column(name = "seq")
    private int seq;

    public DatabaseSequence() {
    }

    public DatabaseSequence(String id, int seq) {
        this.id = id;
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
