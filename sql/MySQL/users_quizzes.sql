CREATE TABLE app.users_quizzes (
                                   userId int NOT NULL,
                                   quizId int NOT NULL,
                                   score int NOT NULL DEFAULT 0,
                                   status int NOT NULL DEFAULT 0,
                                   createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   startedAt timestamp NULL DEFAULT NULL,
                                   finishedAt timestamp NULL DEFAULT NULL,
                                   PRIMARY KEY (userId, quizId)
)
    ENGINE = INNODB,
    AVG_ROW_LENGTH = 8192,
    CHARACTER SET utf8mb4,
    COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE app.users_quizzes
    ADD INDEX users_quizes_ibfk_2 (quizId);

ALTER TABLE app.users_quizzes
    ADD CONSTRAINT users_quizzes_ibfk_1 FOREIGN KEY (userId)
        REFERENCES app.users (id) ON DELETE CASCADE;

ALTER TABLE app.users_quizzes
    ADD CONSTRAINT users_quizzes_ibfk_2 FOREIGN KEY (quizId)
        REFERENCES app.quizzes (id) ON DELETE CASCADE;
       
BEGIN
  IF OLD.status = 0 and NEW.status = 1 THEN 
    set NEW.startedAt = CURRENT_TIMESTAMP; 
  END IF;
  IF OLD.status = 1 THEN 
    select time FROM quizzes WHERE quizzes.id = OLD.quizId INTO @t;
    IF TIMESTAMPDIFF(MINUTE, startedAt, CURRENT_TIMESTAMP) > (@t+1) OR NEW.status = 2 THEN 
      set NEW.finishedAt = CURRENT_TIMESTAMP; 
      set NEW.score = OLD.score; 
      set NEW.status = 2;
    END IF;  
  END IF;
  IF OLD.status = 2 THEN 
    set NEW.score = OLD.score; 
  END IF;
END
