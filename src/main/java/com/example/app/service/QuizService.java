package com.example.app.service;

import com.example.app.dao.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class QuizService {

    private static final Logger LOGGER = Logger.getLogger(QuizService.class);

    private final QuizDao quizDao;

    public QuizService(QuizDao quizDao) {
        LOGGER.info("Initializing UserServiceImpl");
        this.quizDao = quizDao;
    }

    public Quiz createQuiz(Quiz quiz) {
        LOGGER.info("New quiz registration");
        return quizDao.createQuiz(quiz);
    }

    public Quiz updateQuiz(Quiz quiz) {
        LOGGER.info("New quiz registration");
        return quizDao.updateQuiz(quiz);
    }

    public boolean deleteQuiz(int id) {
        LOGGER.info("New quiz registration");
        return quizDao.deleteQuizById(id);
    }

    public Quiz findQuizById(int id) {
        LOGGER.info("Finding quiz by id " + id);
        return id == 0 ? null : quizDao.findQuizById(id);
    }

    public Page<Quiz> getPageByQuiz(Integer page, Integer size) {
        LOGGER.info("Getting page number " + page + ", of size " + size);
        if(page == null || size == null || page < 1 || size < 1) { return null; }
        List<Quiz> items =  quizDao.findAll((page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    public Page<Quiz> getPageByUserId(Integer userId, Integer page, Integer size) {
        LOGGER.info("Getting page number " + page + ", of size " + size);
        if(page == null || size == null || page < 1 || size < 1) { return null; }
        List<Quiz> items =  quizDao.findAllForUser(userId,(page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    public Page<Quiz> getPageByUserIdWithSort(Integer userId, String colName, String direction , Integer page, Integer size) {
        LOGGER.info("Getting userId: " + userId + " page number " + page + ", of size " + size);
        if(page == null || size == null || page < 1 || size < 1) { return null; }
        List<Quiz> items =  quizDao.findAllForUserWithSort(userId, colName, direction,(page - 1) * size, size);
        return new Page<>(items, page, size);
    }
}
