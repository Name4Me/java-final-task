package com.example.app.model.assignment;

import com.example.app.model.quiz.Quiz;
import com.example.app.model.quiz.QuizDifficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AssignmentTest {
    Assignment assignment;

    @BeforeEach
    void setUp() {
        assignment = new Assignment(1, 2, 0, AssignmentStatus.NotStarted,
                null, null, null, null);
    }

    @Test
    void emptyConstructor() { assertEquals(0, (new Assignment()).getQuizId()); }

    @Test
    void getQuizId() { assertEquals(2, assignment.getQuizId()); }

    @Test
    void setQuizId() {
        assignment.setQuizId(3);
        assertEquals(3, assignment.getQuizId());
    }

    @Test
    void getUserId() { assertEquals(1, assignment.getUserId()); }

    @Test
    void setUserId() {
        assignment.setUserId(6);
        assertEquals(6,assignment.getUserId());
    }

    @Test
    void getScore() { assertEquals(0, assignment.getScore()); }

    @Test
    void setScore() {
        assignment.setScore(20);
        assertEquals(20, assignment.getScore());
    }

    @Test
    void getStatus() {
        assertEquals(AssignmentStatus.NotStarted, assignment.getStatus());
    }

    @Test
    void setStatus() {
        assignment.setStatus(AssignmentStatus.Started);
        assertEquals(AssignmentStatus.Started, assignment.getStatus());
    }

    @Test
    void getQuiz() {
        assertNull(assignment.getQuiz());
    }

    @Test
    void setQuiz() {
        Quiz quiz = new Quiz(1, "name", "description", QuizDifficulty.Low, 5, 10);
        assignment.setQuiz(quiz);
        assertEquals(quiz, assignment.getQuiz());
    }
}