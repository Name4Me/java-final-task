CREATE TABLE app.questions (
                               id int NOT NULL AUTO_INCREMENT,
                               quizId int NOT NULL,
                               text text NOT NULL,
                               type int NOT NULL DEFAULT 0,
                               createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (id)
);

ALTER TABLE app.questions
    ADD CONSTRAINT questions_ibfk_1 FOREIGN KEY (quizId)
        REFERENCES app.quizzes (id) ON DELETE CASCADE;