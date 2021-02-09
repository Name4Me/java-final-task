package com.example.app.model.choice;

import com.example.app.model.question.QuestionType;

import java.sql.Timestamp;
import java.util.Date;

public class Choice {
    private int id;
    private int questionId;
    private String text;
    private boolean isTrue;
    private Date createdAt;
    private Date updatedAt;

    public Choice(){ }
    public Choice(int id, int questionId, String text, Boolean isTrue, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isTrue = isTrue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
