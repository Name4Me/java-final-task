package com.example.app.model.question;

import com.example.app.model.Choice;

import java.util.Date;
import java.util.List;

public class Question {
    private int id;
    private int quizId;
    private String text;
    private QuestionType type;
    private Date createdAt;
    private Date updatedAt;
    private List<Choice> choices;

    public Question() { }

    public Question(int id, int quizId, String text, QuestionType type, Date createdAt, Date updatedAt) {
        this.id = id;
        this.quizId = quizId;
        this.text = text;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
