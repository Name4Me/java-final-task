package com.example.app.service.quiz;

import com.example.app.model.quiz.Quiz;
import com.example.app.util.Page;

public interface QuizService {

    /**
     * This method creates new quiz.
     *
     * @param quiz Quiz to create.
     * @return     Updated quiz object.
     */
    Quiz createQuiz(Quiz quiz);

    Quiz updateQuiz(Quiz quiz);

    boolean deleteQuiz(long id);

    /**
     * This method finds quiz by id.
     *
     * @param id Id of quiz to find.
     * @return   Quiz found by id, otherwise null;
     */
    Quiz findQuizById(Long id);

    Page<Quiz> getPageByQuiz(Integer page, Integer size);
}
