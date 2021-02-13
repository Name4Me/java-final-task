CREATE TABLE app.quizzes (
                             id int NOT NULL AUTO_INCREMENT,
                             name varchar(50) NOT NULL,
                             description text NOT NULL,
                             difficulty int NOT NULL,
                             time int NOT NULL,
                             numberOfQuestion int NOT NULL,
                             createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (id)
)
    ENGINE = INNODB,
AUTO_INCREMENT = 22,
AVG_ROW_LENGTH = 3276,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;