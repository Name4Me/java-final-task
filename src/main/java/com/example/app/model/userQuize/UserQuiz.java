package com.example.app.model.userQuize;

import com.example.app.model.quiz.Quiz;

import java.sql.Timestamp;
import java.util.Date;

public class UserQuiz {
    private int userId;
    private int quizId;
    private int score;
    private UserQuizStatus status;
    private Date createdAt;
    private Date updatedAt;
    private Date startedAt;
    private Date finishedAt;
    private Quiz quiz;

    public UserQuiz(){ }

    public UserQuiz(int userId, int quizId, int score, UserQuizStatus status,
                    Timestamp createdAt, Timestamp updatedAt, Timestamp startedAt, Timestamp finishedAt) {
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.finishedAt = finishedAt;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UserQuizStatus getStatus() {
        return status;
    }

    public void setStatus(UserQuizStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }
}
