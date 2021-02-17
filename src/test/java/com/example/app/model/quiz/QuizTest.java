package com.example.app.model.quiz;

import com.example.app.model.question.Question;
import com.example.app.model.question.QuestionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizTest {
    private Quiz quiz;

    @BeforeEach
    void setUp() {
        quiz = new Quiz(1, "name", "description", QuizDifficulty.Low, 5, 10);
    }

    @Test
    void emptyConstructor() { assertEquals(0, (new Quiz()).getId()); }

    @Test
    void getId() { assertEquals(1, quiz.getId()); }

    @Test
    void setId() {
        quiz.setId(10);
        assertEquals(10, quiz.getId());
    }

    @Test
    void getName() { assertEquals("name", quiz.getName()); }

    @Test
    void setName() {
        quiz.setName("new");
        assertEquals("new", quiz.getName());
    }

    @Test
    void testEquals() {
        Quiz quiz1 = new Quiz(1, "name", "description", QuizDifficulty.Low, 5, 10);
        assertEquals(quiz1, quiz);
    }

    @Test
    void testHashCode() { assertNotEquals(0, quiz.hashCode()); }

    @Test
    void getDescription() { assertEquals("description", quiz.getDescription()); }

    @Test
    void setDescription() {
        quiz.setDescription("new description");
        assertEquals("new description", quiz.getDescription());
    }

    @Test
    void getDifficulty() { assertEquals(QuizDifficulty.Low, quiz.getDifficulty()); }

    @Test
    void setDifficulty() {
        quiz.setDifficulty(QuizDifficulty.High);
        assertEquals(QuizDifficulty.High, quiz.getDifficulty());
    }

    @Test
    void getTime() { assertEquals(5, quiz.getTime()); }

    @Test
    void setTime() {
        quiz.setTime(6);
        assertEquals(6, quiz.getTime());
    }

    @Test
    void getNumberOfQuestion() { assertEquals(10, quiz.getNumberOfQuestion()); }

    @Test
    void setNumberOfQuestion() {
        quiz.setNumberOfQuestion(9);
        assertEquals(9, quiz.getNumberOfQuestion());
    }

    @Test
    void getQuestions() {
        assertNull(quiz.getQuestions());
    }

    @Test
    void setQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1, 2, "text", QuestionType.Single));
        quiz.setQuestions(questions);
        assertEquals(questions, quiz.getQuestions());
    }
}