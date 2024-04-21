package com.example.efabackend.Dto;

public class QuizDto {
    private String cour;
    private String matiere;
    private String question;
    private int rep;
    private String reponces;
    public QuizDto(String cour, String matiere, String question, int rep, String reponces) {
        this.cour = cour;
        this.matiere = matiere;
        this.question = question;
        this.rep = rep;
        this.reponces = reponces;
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
}
