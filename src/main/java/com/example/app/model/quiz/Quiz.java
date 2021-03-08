package com.example.app.model.quiz;

import com.example.app.model.question.Question;

import java.util.List;

public class Quiz {
    private int id;
    private String name;
    private String description;
    private QuizDifficulty difficulty;
    private int time;
    private int numberOfQuestion;
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(int id, String name, String description, QuizDifficulty difficulty, int time, int numberOfQuestion) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.time = time;
        this.numberOfQuestion = numberOfQuestion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz category = (Quiz) o;

        if (id != category.id) return false;
        return name.equals(category.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuizDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(QuizDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
