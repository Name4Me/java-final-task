package com.example.app.service.userQuiz;

import com.example.app.dao.userQuize.UserQuizDao;
import org.apache.log4j.Logger;

public class UserQuizService {
    private static final Logger LOGGER = Logger.getLogger(UserQuizService.class);

    private UserQuizDao userQuizDao;

    public UserQuizService(UserQuizDao userQuizDao) {
        LOGGER.info("Initializing UserQuizService");

        this.userQuizDao = userQuizDao;
    }
}
