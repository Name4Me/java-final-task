CREATE TABLE app.questions (
                               id int NOT NULL AUTO_INCREMENT,
                               quizId int NOT NULL,
                               text text NOT NULL,
                               type int NOT NULL,
                               createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (id)
)
    ENGINE = INNODB,
AUTO_INCREMENT = 13,
AVG_ROW_LENGTH = 2730,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE app.questions
    ADD CONSTRAINT questions_ibfk_1 FOREIGN KEY (quizId)
        REFERENCES app.quizzes (id) ON DELETE CASCADE;