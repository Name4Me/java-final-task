CREATE TABLE app.choices (
                             id int NOT NULL AUTO_INCREMENT,
                             questionId int NOT NULL,
                             text text NOT NULL,
                             isCorrect tinyint(1) NOT NULL DEFAULT 0,
                             createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (id)
);

ALTER TABLE app.choices
    ADD CONSTRAINT choices_ibfk_1 FOREIGN KEY (questionId)
        REFERENCES app.questions (id) ON DELETE CASCADE;