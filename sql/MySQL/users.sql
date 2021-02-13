CREATE TABLE app.users (
                           id int NOT NULL AUTO_INCREMENT,
                           login varchar(20) NOT NULL,
                           password varchar(255) NOT NULL,
                           email varchar(50) NOT NULL,
                           userType int NOT NULL DEFAULT 1,
                           status int NOT NULL DEFAULT 1,
                           createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updatedAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (id)
)
    ENGINE = INNODB,
AUTO_INCREMENT = 21,
AVG_ROW_LENGTH = 8192,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;