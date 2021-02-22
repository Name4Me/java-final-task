package com.example.app.service;

import com.example.app.dao.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.quiz.QuizDifficulty;
import com.example.app.model.user.UserType;
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
class QuizServiceTest {
    private final Quiz quiz = new Quiz(1, "name", "description", QuizDifficulty.Low, 5, 10);
    private QuizService quizService;
    @Mock private QuizDao quizDao;

    @BeforeEach
    void setUp() {
        quizService = new QuizService(quizDao);
    }

    @Test
    void createQuiz() {
        Mockito.when(quizDao.createQuiz(quiz)).thenReturn(quiz);
        Object actual = quizService.createQuiz(quiz);
        assertEquals(quiz, actual);
        Mockito.verify(quizDao).createQuiz(quiz);
    }

    @Test
    void updateQuiz() {
        Mockito.when(quizDao.updateQuiz(quiz)).thenReturn(quiz);
        Object actual = quizService.updateQuiz(quiz);
        assertEquals(quiz, actual);
        Mockito.verify(quizDao).updateQuiz(quiz);
    }

    @Test
    void deleteQuiz() {
        Mockito.when(quizDao.deleteQuizById(1)).thenReturn(true);
        boolean actual = quizService.deleteQuiz(1);
        assertTrue(actual);
        Mockito.verify(quizDao).deleteQuizById(1);
    }

    @Test
    void findQuizById() {
        Mockito.when(quizDao.findQuizById(1)).thenReturn(quiz);
        Object actual = quizService.findQuizById(1);
        assertEquals(quiz, actual);
        Mockito.verify(quizDao).findQuizById(1);
    }

    @Test
    void getPageByQuiz() {
        List<Quiz> items =  new ArrayList<>();
        items.add(quiz);
        Mockito.when(quizDao.findAll(UserType.USER, 0, 5)).thenReturn(items);
        Page<Quiz> actual = quizService.getPageByQuiz( 1, 5);
        assertEquals(quiz, actual.getItems().get(0));
        Mockito.verify(quizDao).findAll(UserType.USER, 0, 5);
    }

    @Test
    void getPageByUserId() {
        List<Quiz> items =  new ArrayList<>();
        items.add(quiz);
        Mockito.when(quizDao.findAllForUser(1, 0, 5)).thenReturn(items);
        Page<Quiz> actual = quizService.getPageByUserId(1, 1, 5);
        assertEquals(quiz, actual.getItems().get(0));
        Mockito.verify(quizDao).findAllForUser(1, 0, 5);
    }

    @Test
    void getPageByUserIdWithSort() {
        List<Quiz> items =  new ArrayList<>();
        items.add(quiz);
        Mockito.when(quizDao.findAllForUserWithSort(1, "id", "asc",0, 5)).thenReturn(items);
        Page<Quiz> actual = quizService.getPageByUserIdWithSort(1, "id", "asc",1, 5);
        assertEquals(quiz, actual.getItems().get(0));
        Mockito.verify(quizDao).findAllForUserWithSort(1, "id", "asc",0, 5);
    }
}