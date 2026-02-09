

CREATE DATABASE IF NOT EXISTS CompetitionDB;
USE CompetitionDB;


DROP TABLE IF EXISTS Competitors;

CREATE TABLE Competitors (
  competitor_id int NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  level varchar(50) DEFAULT NULL,
  country varchar(50) DEFAULT NULL,
  score1 int DEFAULT 0,
  score2 int DEFAULT 0,
  score3 int DEFAULT 0,
  score4 int DEFAULT 0,
  score5 int DEFAULT 0,
  password varchar(50) DEFAULT NULL,
  PRIMARY KEY (competitor_id)
);

INSERT INTO Competitors (competitor_id, name, level, country, score1, score2, score3, score4, score5, password) VALUES 
(1, 'shyam magar', 'Beginner', 'nepal', 0, 5, 5, 0, 5, NULL),
(12, 'piyush rauniyar', 'Intermediate', 'japan', 5, 0, 5, 5, 5, NULL),
(13, 'ronish karki', 'Beginner', 'nepal', 5, 5, 5, 5, 5, NULL),
(22, 'ads sadA', '23', 'JAPN', 0, 0, 0, 0, 0, NULL),
(76, 'jnbgb yhybhgy', '6', 'rtyu', 5, 5, 5, 5, 5, NULL),
(200, 'Alice Green', 'Beginner', 'UK', 4, 3, 5, 2, 4, NULL),
(201, 'Bob Brown', 'Intermediate', 'USA', 3, 4, 4, 5, 4, NULL),
(202, 'Carol White', 'Advanced', 'Canada', 5, 5, 4, 4, 5, NULL),
(203, 'David Black', 'Advanced', 'Australia', 4, 4, 5, 5, 4, NULL),
(999, 'My Self', 'Expert', 'Nepal', 0, 0, 5, 5, 5, NULL),
(1000, 'piyush Rauniyar', 'Advanced', 'Nepal', 0, 0, 0, 5, 5, NULL),
(1001, 'piyush rauniyar', 'Intermediate', 'nepal', 5, 0, 5, 5, 5, 'player123'),
(1006, 'ram thapa', 'Intermediate', 'japan', 5, 5, 5, 5, 5, 'player34');