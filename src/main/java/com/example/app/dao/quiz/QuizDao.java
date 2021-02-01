package com.example.app.dao.quiz;

import com.example.app.model.quiz.Quiz;

import java.util.List;

public interface QuizDao {
    /**
     * This method saves new category to db.
     *
     * @param category Object to be created.
     * @return         An updated object.
     */
    Quiz createCategory(Quiz category);

    /**
     * This method updates category.
     *
     * @param category Object to be updated.
     * @return         An updated object.
     */
    Quiz updateCategory(Quiz category);

    /**
     * This method deletes category from db.
     *
     * @param id Id of the category to be deleted.
     * @return   True if deletion successful, otherwise false.
     */
    boolean deleteCategoryById(Long id);

    /**
     * This method finds category by id.
     *
     * @param id Id of the category.
     * @return   Category object retrieved from db, otherwise null.
     */
    Quiz findCategoryById(Long id);

    /**
     * This method finds category by name.
     *
     * @param name Name of the category.
     * @return     Category object with given name, otherwise null.
     */
    Quiz findCategoryByName(String name);

    /**
     * This method gets all categories
     *
     * @return A list of all categories.
     */
    List<Quiz> findAll();
}
