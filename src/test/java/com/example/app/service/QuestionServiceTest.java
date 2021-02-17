package com.example.app.service;

import com.example.app.dao.QuestionDao;
import com.example.app.model.question.Question;
import com.example.app.model.question.QuestionType;
import com.example.app.util.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    private final Question question = new Question(1, 2, "text", QuestionType.Single);
    private QuestionService questionService;
    @Mock
    private QuestionDao questionDao;

    @BeforeEach
    void setUp() {
        questionService = new QuestionService(questionDao);
    }

    @Test
    void getPage() {
        List<Question> items =  new ArrayList<>();
        items.add(question);
        Mockito.when(questionDao.findAll(1, 0, 5)).thenReturn(items);
        Page<Question> actual = questionService.getPage(1, 1, 5);
        assertEquals(question, actual.getItems().get(0));
        Mockito.verify(questionDao).findAll(1, 0, 5);
    }

    @Test
    void createQuestion() {
        Mockito.when(questionDao.createQuestion(question)).thenReturn(question);
        Object actual = questionService.createQuestion(question);
        assertEquals(question, actual);
        Mockito.verify(questionDao).createQuestion(question);
    }

    @Test
    void updateQuestion() {
        Mockito.when(questionDao.updateQuestion(question)).thenReturn(question);
        Object actual = questionService.updateQuestion(question);
        assertEquals(question, actual);
        Mockito.verify(questionDao).updateQuestion(question);
    }

    @Test
    void deleteQuestion() {
        Mockito.when(questionDao.deleteQuestionById(1)).thenReturn(true);
        boolean actual = questionService.deleteQuestion(1);
        assertTrue(actual);
        Mockito.verify(questionDao).deleteQuestionById(1);
    }
}