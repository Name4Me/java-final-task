package com.example.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChoiceTest {
    private Choice choice;

    @BeforeEach
    void setUp() {
        choice = new Choice(1, 2, "test", true);
    }

    @Test
    void emptyConstructor() { assertEquals(0, (new Choice()).getId()); }

    @Test
    void getId() { assertEquals(1, choice.getId()); }

    @Test
    void setId() {
        choice.setId(10);
        assertEquals(10, choice.getId());
    }

    @Test
    void getQuestionId() { assertEquals(2, choice.getQuestionId()); }

    @Test
    void setQuestionId() {
        choice.setQuestionId(3);
        assertEquals(3, choice.getQuestionId());
    }

    @Test
    void getText() { assertEquals("test", choice.getText()); }

    @Test
    void setText() {
        choice.setText("new");
        assertEquals("new", choice.getText());
    }

    @Test
    void getIsCorrect() { assertTrue(choice.getIsCorrect()); }

    @Test
    void setIsCorrect() {
        choice.setIsCorrect(false);
        assertFalse(choice.getIsCorrect());
    }
}