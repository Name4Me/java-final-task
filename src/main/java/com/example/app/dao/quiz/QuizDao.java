package com.example.app.dao.quiz;

import com.example.app.model.quiz.Quiz;
import com.example.app.model.user.User;
import com.example.app.model.user.UserType;

import java.util.List;

public interface QuizDao {
    /**
     * This method saves new quiz to db.
     *
     * @param quiz Object to be created.
     * @return         An updated object.
     */
    Quiz createQuiz(Quiz quiz);

    /**
     * This method updates quiz.
     *
     * @param quiz Object to be updated.
     * @return         An updated object.
     */
    Quiz updateQuiz(Quiz quiz);

    /**
     * This method deletes quiz from db.
     *
     * @param id Id of the quiz to be deleted.
     * @return   True if deletion successful, otherwise false.
     */
    boolean deleteQuizById(Long id);

    /**
     * This method finds quiz by id.
     *
     * @param id Id of the quiz.
     * @return   Quiz object retrieved from db, otherwise null.
     */
    Quiz findQuizById(Long id);

    /**
     * This method finds quiz by name.
     *
     * @param name Name of the quiz.
     * @return     Quiz object with given name, otherwise null.
     */
    Quiz findQuizByName(String name);

    /**
     * This method gets all quizzes
     *
     * @param offset   Element to start from.
     * @param size     How much elements to take.
     * @return A list of all quizzes.
     */
    List<Quiz> findAll(Integer offset, Integer size);

}
