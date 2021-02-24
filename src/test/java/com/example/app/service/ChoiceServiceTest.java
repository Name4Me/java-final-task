package com.example.app.service;

import com.example.app.dao.ChoiceDao;
import com.example.app.model.Choice;
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
class ChoiceServiceTest {
    private final Choice choice = new Choice(1, 2, "test", true);
    private ChoiceService choiceService;
    @Mock private ChoiceDao choiceDao;

    @BeforeEach
    void setUp() {
        choiceService = new ChoiceService(choiceDao);
    }

    @Test
    void getPage() {
        List<Choice> items =  new ArrayList<>();
        items.add(choice);
        Mockito.when(choiceDao.findAll(1, 0, 6)).thenReturn(items);
        Page<Choice> actual = choiceService.getPage(1, 1, 5);
        assertEquals(choice, actual.getItems().get(0));
        Mockito.verify(choiceDao).findAll(1, 0, 6);
    }

    @Test
    void createChoice() {
        Mockito.when(choiceDao.createChoice(choice)).thenReturn(choice);
        Object actual = choiceService.createChoice(choice);
        assertEquals(choice, actual);
        Mockito.verify(choiceDao).createChoice(choice);
    }

    @Test
    void updateChoice() {
        Mockito.when(choiceDao.updateChoice(choice)).thenReturn(choice);
        Object actual = choiceService.updateChoice(choice);
        assertEquals(choice, actual);
        Mockito.verify(choiceDao).updateChoice(choice);
    }

    @Test
    void deleteChoice() {
        Mockito.when(choiceDao.deleteChoiceById(1)).thenReturn(true);
        boolean actual = choiceService.deleteChoice(1);
        assertTrue(actual);
        Mockito.verify(choiceDao).deleteChoiceById(1);
    }
}