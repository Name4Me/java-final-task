CREATE TABLE app.assignments (
                                   userId int NOT NULL,
                                   quizId int NOT NULL,
                                   score int NOT NULL DEFAULT 0,
                                   status int NOT NULL DEFAULT 0,
                                   createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   startedAt timestamp NULL DEFAULT NULL,
                                   finishedAt timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (userId, quizId)
);

ALTER TABLE app.assignments
    ADD INDEX users_quizes_ibfk_2 (quizId);

ALTER TABLE app.assignments
    ADD CONSTRAINT assignments_ibfk_1 FOREIGN KEY (userId)
        REFERENCES app.users (id) ON DELETE CASCADE;

ALTER TABLE app.assignments
    ADD CONSTRAINT assignments_ibfk_2 FOREIGN KEY (quizId)
        REFERENCES app.quizzes (id) ON DELETE CASCADE;

--
-- Установка кодировки, с использованием которой клиент будет посылать запросы на сервер
--
SET NAMES 'utf8';

--
-- Установка базы данных по умолчанию
--
USE app;

DELIMITER $$

--
-- Создать триггер `trigger1`
--
CREATE
    DEFINER = 'root'@'localhost'
    TRIGGER trigger1
    BEFORE UPDATE
    ON users_quizzes
    FOR EACH ROW
BEGIN
    IF OLD.status = 0
        AND NEW.status = 1 THEN
        SET NEW.startedAt = CURRENT_TIMESTAMP;
    END IF;
    IF OLD.status = 1 THEN
        IF NEW.status = 2
            OR TIMESTAMPDIFF(MINUTE, OLD.startedAt, CURRENT_TIMESTAMP) > (SELECT
                                                                                  quizzes.time - 1
                                                                          FROM quizzes
                                                                          WHERE quizzes.id = OLD.quizId) THEN
            SET NEW.finishedAt = CURRENT_TIMESTAMP;
            SET NEW.score = OLD.score;
            SET NEW.status = 2;
        END IF;
    END IF;
    IF OLD.status = 2 THEN
        SET NEW.score = OLD.score;
    END IF;
END
$$

DELIMITER ;
