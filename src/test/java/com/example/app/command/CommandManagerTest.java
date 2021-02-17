package com.example.app.command;

import com.example.app.dao.AssignmentDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommandManagerTest {
    private CommandManager commandManager;
    @Mock private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        commandManager = new CommandManager();
    }

    @Test
    void getGetCommand() {
        ServletCommand command = commandManager.getGetCommand(request);
    }

    @Test
    void getPostCommand() {
    }

    @Test
    void getMappting() {
    }




}