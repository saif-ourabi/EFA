package com.example.efabackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "database_sequences")
public class DatabaseSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "seq")
    private int seq;

    public DatabaseSequence() {
    }

    public DatabaseSequence(int seq) {
        this.seq = seq;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
