package com.example.app.service.quiz;

import com.example.app.dao.quiz.QuizDao;
import com.example.app.model.quiz.Quiz;
import com.example.app.model.user.User;
import com.example.app.model.user.UserType;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    private static final Logger LOGGER = Logger.getLogger(QuizServiceImpl.class);

    private QuizDao quizDao;

    public QuizServiceImpl(QuizDao quizDao) {
        LOGGER.info("Initializing UserServiceImpl");
        this.quizDao = quizDao;
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        LOGGER.info("New quiz registration");
        return quizDao.createQuiz(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        LOGGER.info("New quiz registration");
        return quizDao.updateQuiz(quiz);
    }

    @Override
    public boolean deleteQuiz(long id) {
        LOGGER.info("New quiz registration");
        return quizDao.deleteQuizById(id);
    }

    @Override
    public Quiz findQuizById(Long id) {
        LOGGER.info("Finding quiz by id " + id);
        return id == null ? null : quizDao.findQuizById(id);
    }

    @Override
    public Page<Quiz> getPageByQuiz(Integer page, Integer size) {
        LOGGER.info("Getting page number " + page + ", of size " + size);

        if(page == null || size == null || page < 1 || size < 1) {
            return null;
        }

        List<Quiz> items =  quizDao.findAll((page - 1) * size, size);
        return new Page<>(items, page, size);
    }
}
