DROP DATABASE Quackeldb;

CREATE DATABASE Quackeldb;

USE Quackeldb;

CREATE TABLE users(
	userID BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(25) NOT NULL UNIQUE
) AUTO_INCREMENT = 1001;

CREATE TABLE quacks(
	messageID BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    userID BIGINT,
    body VARCHAR(255) NOT NULL, 
    
    FOREIGN KEY (userID) REFERENCES user(userID) ON DELETE CASCADE
) AUTO_INCREMENT = 1001;
    