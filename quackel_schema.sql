DROP DATABASE Quackeldb;

CREATE DATABASE Quackeldb;

USE Quackeldb;

CREATE TABLE user(
	id BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL UNIQUE
) AUTO_INCREMENT = 1001;

CREATE TABLE quack(
	id BIGINT(19) PRIMARY KEY AUTO_INCREMENT,
    userId BIGINT,
    body VARCHAR(255) NOT NULL, 
    FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE
) AUTO_INCREMENT = 1001;
    