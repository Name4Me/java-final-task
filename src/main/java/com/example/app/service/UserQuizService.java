package com.example.app.service;

import com.example.app.dao.UserQuizDao;
import com.example.app.model.question.Question;
import com.example.app.model.userQuize.UserQuiz;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class UserQuizService {
    private static final Logger LOGGER = Logger.getLogger(UserQuizService.class);

    private UserQuizDao userQuizDao;

    public UserQuizService(UserQuizDao userQuizDao) {
        LOGGER.info("Initializing UserQuizService");

        this.userQuizDao = userQuizDao;
    }

    public Object createUserQuiz(UserQuiz userQuiz) {
        LOGGER.info("UserQuizService new userQuiz");
        return userQuizDao.createUserQuiz(userQuiz);
    }

    public UserQuiz updateUserQuiz(UserQuiz userQuiz) {
        LOGGER.info("UserQuizService update userQuiz");
        return userQuizDao.updateUserQuiz(userQuiz);
    }

    public UserQuiz getUserQuizByUserIdQuizId(int userId, int quizId, boolean getQuestions) {
        LOGGER.info("UserQuizService get UserQuiz by userId: "+userId+" quizId: "+quizId);
        return userQuizDao.findUserQuizByUserIdQuizId(userId, quizId, getQuestions);
    }

    public Page<UserQuiz> getPageByUserId(Integer userId, Integer page, Integer size) {
        LOGGER.info("QuestionService getting page number " + page + ", of size " + size);
        if(page == null || size == null || page < 1 || size < 1) { return null; }

        List<UserQuiz> items = userQuizDao.findAll(userId,(page - 1) * size, size);
        return new Page<>(items, page, size);
    }
}
