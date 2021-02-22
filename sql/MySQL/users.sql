CREATE TABLE app.users (
                           id int NOT NULL AUTO_INCREMENT,
                           password blob NOT NULL,
                           email varchar(50) NOT NULL,
                           userType int NOT NULL DEFAULT 1,
                           status int NOT NULL DEFAULT 1,
                           PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;