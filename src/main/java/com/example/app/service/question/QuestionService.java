package com.example.app.service.question;

import com.example.app.dao.question.QuestionDao;
import com.example.app.model.question.Question;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class QuestionService {

    private static final Logger LOGGER = Logger.getLogger(QuestionService.class);

    private QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        LOGGER.info("Initializing QuestionService");
        this.questionDao = questionDao;
    }

    public Page<Question> getPage(Integer quizId, Integer page, Integer size) {
        LOGGER.info("QuestionService getting page number " + page + ", of size " + size);
        if(page == null || size == null || page < 1 || size < 1) { return null; }

        List<Question> items =  questionDao.findAll(quizId,(page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    public Object createQuestion(Question question) {
        LOGGER.info("QuestionService new question");
        return questionDao.createQuestion(question);
    }

    public Question updateQuestion(Question question) {
        LOGGER.info("QuestionService update question");
        return questionDao.updateQuestion(question);
    }

    public boolean deleteQuestion(int id) {
        LOGGER.info("QuestionService delete question");
        return questionDao.deleteQuestionById(id);
    }
}
