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

CREATE
    DEFINER = 'root'@'localhost'
    TRIGGER app.trigger2
    AFTER DELETE
    ON app.choices
    FOR EACH ROW
BEGIN
    CALL updateQuestions(NEW.questionId);
END;

CREATE
    DEFINER = 'root'@'localhost'
    TRIGGER app.trigger3
    AFTER UPDATE
    ON app.choices
    FOR EACH ROW
BEGIN
    CALL updateQuestions(OLD.questionId);
END;

CREATE
    DEFINER = 'root'@'localhost'
    TRIGGER app.trigger4
    AFTER UPDATE
    ON app.choices
    FOR EACH ROW
BEGIN
    CALL updateQuestions(NEW.questionId);
END;

CREATE DEFINER = 'root'@'localhost'
PROCEDURE app.updateQuestions(IN questionId INT)
BEGIN
SELECT type FROM questions q WHERE q.id = questionId INTO @qType;
SELECT IF(COUNT(*)<2,0,1) FROM choices c WHERE c.questionId = questionId AND c.isCorrect INTO @newQType;
IF (@qType != @newQType) THEN
    UPDATE questions set type = @newQType WHERE id = questionId;
END IF;
END;