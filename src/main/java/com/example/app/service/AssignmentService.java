package com.example.app.service;

import com.example.app.dao.AssignmentDao;
import com.example.app.model.assignment.Assignment;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class AssignmentService {
    private static final Logger LOGGER = Logger.getLogger(AssignmentService.class);

    private AssignmentDao assignmentDao;

    public AssignmentService(AssignmentDao assignmentDao) {
        LOGGER.info("Initializing AssignmentService");

        this.assignmentDao = assignmentDao;
    }

    public Object createUserQuiz(Assignment assignment) {
        LOGGER.info("AssignmentService new assignment");
        return assignmentDao.createUserQuiz(assignment);
    }

    public Assignment updateUserQuiz(Assignment assignment) {
        LOGGER.info("AssignmentService update assignment");
        return assignmentDao.updateUserQuiz(assignment);
    }

    public Assignment getUserQuizByUserIdQuizId(int userId, int quizId, boolean getQuestions) {
        LOGGER.info("AssignmentService get Assignment by userId: "+userId+" quizId: "+quizId);
        return assignmentDao.findUserQuizByUserIdQuizId(userId, quizId, getQuestions);
    }

    public Page<Assignment> getPageByUserId(Integer userId, Integer page, Integer size) {
        LOGGER.info("QuestionService getting page for userId:" + userId+" number: " + page + ", of size: " + size);
        if(page == null || size == null || page < 1 || size < 1) { return null; }
        List<Assignment> items = assignmentDao.findAll(userId,(page - 1) * size, size);
        return new Page<>(items, page, size);
    }
}
