package com.example.app.service;

import com.example.app.dao.ChoiceDao;
import com.example.app.model.Choice;
import com.example.app.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class ChoiceService {
    private static final Logger LOGGER = Logger.getLogger(ChoiceService.class);

    private final ChoiceDao choiceDao;

    public ChoiceService(ChoiceDao choiceDao) {
        LOGGER.info("Initializing ChoiceService");
        this.choiceDao = choiceDao;
    }

    public Page<Choice> getPage(Integer questionId, Integer page, Integer size) {
        LOGGER.info("ChoiceService getting page number " + page + ", of size " + size);
        if(page == null || size == null || page < 1 || size < 1) { return null; }
        List<Choice> items =  choiceDao.findAll(questionId,(page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    public Object createChoice(Choice choice) {
        LOGGER.info("ChoiceService new choice");
        return choiceDao.createChoice(choice);
    }

    public Choice updateChoice(Choice choice) {
        LOGGER.info("ChoiceService update choice");
        return choiceDao.updateChoice(choice);
    }

    public boolean deleteChoice(int id) {
        LOGGER.info("ChoiceService delete choice");
        return choiceDao.deleteChoiceById(id);
    }
}
