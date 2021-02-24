package com.example.app.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Mockito.when(request.getRequestURI()).thenReturn("/app/controller/login");
        Mockito.when(request.getContextPath()).thenReturn("/app");
        ServletCommand command = commandManager.getGetCommand(request);
        assertEquals("GetLoginPageCommand", command.getClass().getSimpleName());
    }

    @Test
    void getGetCommandNull() {
        Mockito.when(request.getRequestURI()).thenReturn("/app/controller/xxx");
        Mockito.when(request.getContextPath()).thenReturn("/app");
        ServletCommand command = commandManager.getGetCommand(request);
        assertEquals("GetMainPageCommand", command.getClass().getSimpleName());
    }

    @Test
    void getPostCommand() {
        Mockito.when(request.getRequestURI()).thenReturn("/app/controller/login");
        Mockito.when(request.getContextPath()).thenReturn("/app");
        ServletCommand command = commandManager.getPostCommand(request);
        assertEquals("LoginCommand", command.getClass().getSimpleName());
    }

    @Test
    void getPostCommandNull() {
        Mockito.when(request.getRequestURI()).thenReturn("/app/controller/xxx");
        Mockito.when(request.getContextPath()).thenReturn("/app");
        ServletCommand command = commandManager.getPostCommand(request);
        assertEquals("GetMainPageCommand", command.getClass().getSimpleName());
    }

    @Test
    void getPostCommandNullPlus() {
        Mockito.when(request.getRequestURI()).thenReturn("/app/controller/xxx/");
        Mockito.when(request.getContextPath()).thenReturn("/app");
        ServletCommand command = commandManager.getPostCommand(request);
        assertEquals("GetMainPageCommand", command.getClass().getSimpleName());
    }

    @Test
    void getMappting() {
        Mockito.when(request.getRequestURI()).thenReturn("/app/controller/login");
        Mockito.when(request.getContextPath()).thenReturn("/app");
        String command = commandManager.getMapping(request);
        assertEquals("/controller/login", command);
    }




}