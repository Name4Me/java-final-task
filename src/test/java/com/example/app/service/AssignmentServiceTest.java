package com.example.app.service;

import com.example.app.dao.AssignmentDao;
import com.example.app.model.assignment.Assignment;
import com.example.app.model.assignment.AssignmentStatus;
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
class AssignmentServiceTest {
    private final Assignment assignment = new Assignment(1, 2, 0, AssignmentStatus.NotStarted,
            null, null, null, null);
    private AssignmentService assignmentService;
    @Mock
    private AssignmentDao assignmentDao;

    @BeforeEach
    void setUp() {
        assignmentService = new AssignmentService(assignmentDao);
    }

    @Test
    void createUserQuiz() {
        Mockito.when(assignmentDao.createUserQuiz(assignment)).thenReturn(assignment);
        Object actual = assignmentService.createUserQuiz(assignment);
        assertEquals(assignment, actual);
        Mockito.verify(assignmentDao).createUserQuiz(assignment);
    }

    @Test
    void updateUserQuiz() {
        Mockito.when(assignmentDao.updateUserQuiz(assignment)).thenReturn(assignment);
        Object actual = assignmentService.updateUserQuiz(assignment);
        assertEquals(assignment, actual);
        Mockito.verify(assignmentDao).updateUserQuiz(assignment);
    }

    @Test
    void getUserQuizByUserIdQuizId() {
        Mockito.when(assignmentDao.findUserQuizByUserIdQuizId(1, 1, true)).thenReturn(assignment);
        Object actual = assignmentService.getUserQuizByUserIdQuizId(1, 1, true);
        assertEquals(assignment, actual);
        Mockito.verify(assignmentDao).findUserQuizByUserIdQuizId(1, 1, true);
    }

    @Test
    void getPageByUserId() {
        List<Assignment> items =  new ArrayList<>();
        items.add(assignment);
        Mockito.when(assignmentDao.findAll(1, 0, 6)).thenReturn(items);
        Page<Assignment> actual = assignmentService.getPageByUserId(1, 1, 5);
        assertEquals(assignment, actual.getItems().get(0));
        Mockito.verify(assignmentDao).findAll(1, 0, 6);
    }
}