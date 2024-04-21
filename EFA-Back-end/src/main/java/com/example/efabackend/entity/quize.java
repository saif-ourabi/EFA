package com.example.efabackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quize")
public class quize {
    @Id
    @Column(name = "id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cour", length = 255)
    private String cour;

    @Column(name = "matiere", length = 255)
    private String matiere;

    @Column(name = "question", length = 255)
    private String question;

    @Column(name = "rep", length = 11)
    private int rep;
    @Column(name = "reponces", length = 255)
    private String reponces;
    public quize(long id, String cour, String matiere, String question, int rep, String reponces) {
        this.id = id;
        this.cour = cour;
        this.matiere = matiere;
        this.question = question;
        this.rep = rep;
        this.reponces = reponces;
    }

    public quize(String cour, String matiere, String question, int rep, String reponces) {
        this.cour = cour;
        this.matiere = matiere;
        this.question = question;
        this.rep = rep;
        this.reponces = reponces;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCour() {
        return cour;
    }

    public void setCour(String cour) {
        this.cour = cour;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public String getReponces() {
        return reponces;
    }

    public void setReponces(String reponces) {
        this.reponces = reponces;
    }
     

    @Override
    public String toString() {
        return "quiz{" +
                "id=" + id +
                ", cour='" + cour + '\'' +
                ", matiere='" + matiere + '\'' +
                ", question='" + question + '\'' +
                ", rep='" + rep + '\'' +
                ", reponces='" + reponces + '\'' +
                '}';
    }
}
