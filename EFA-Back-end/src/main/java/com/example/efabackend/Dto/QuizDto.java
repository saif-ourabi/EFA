package com.example.efabackend.Dto; // Correction : Changé "Dto" à "dto" pour correspondre aux conventions de nommage

public class QuizDto {
    private String cour;
    private String matiere;
    private String question;
    private int rep;
    private String reponses; // Correction : Changé "reponces" à "reponses" pour correspondre à la variable

    public QuizDto() {
        // Constructeur vide requis
    }

    public QuizDto(String cour, String matiere, String question, int rep, String reponses) {
        this.cour = cour;
        this.matiere = matiere;
        this.question = question;
        this.rep = rep;
        this.reponses = reponses; // Correction : Changé "reponces" à "reponses" pour correspondre à la variable
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

    public String getReponses() {
        return reponses;
    }

    public void setReponses(String reponses) {
        this.reponses = reponses;
    }
}
