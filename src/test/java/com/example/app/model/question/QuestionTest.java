package com.example.app.model.question;

import com.example.app.model.Choice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {
    Question question;

    @BeforeEach
    void setUp() {
        question = new Question(1, 2, "text", QuestionType.Single);
    }

    @Test
    void emptyConstructor() { assertEquals(0, (new Question()).getId()); }

    @Test
    void getQuizId() { assertEquals(2, question.getQuizId()); }

    @Test
    void setQuizId() {
        question.setQuizId(10);
        assertEquals(10, question.getQuizId());
    }

    @Test
    void getId() { assertEquals(1, question.getId()); }

    @Test
    void setId() {
        question.setId(5);
        assertEquals(5, question.getId());
    }

    @Test
    void getText() { assertEquals("text", question.getText()); }

    @Test
    void setText() {
        question.setText("new");
        assertEquals("new", question.getText());
    }

    @Test
    void getType() { assertEquals(QuestionType.Single, question.getType()); }

    @Test
    void setType() {
        question.setType(QuestionType.Multiple);
        assertEquals(QuestionType.Multiple, question.getType());
    }

    @Test
    void getChoices() { assertNull(question.getChoices()); }

    @Test
    void setChoices() {
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(1, 2, "test", true));
        question.setChoices(choices);
        assertEquals(choices, question.getChoices());
    }
}