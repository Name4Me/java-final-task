package com.example.app.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RegisterCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    void execute() {
        Mockito.when(request.getParameter("email")).thenReturn("user1@test.com");
        Mockito.when(request.getParameter("password")).thenReturn("1");
        Mockito.when(request.getParameter("confirm_password")).thenReturn("1");
        HttpSession session = mock(HttpSession.class);
        Mockito.when(request.getSession()).thenReturn(session);
        RegisterCommand registerCommand = new RegisterCommand();
        registerCommand.execute(request, response);
    }
}