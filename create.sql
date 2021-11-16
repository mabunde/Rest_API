CREATE DATABASE news_portal
CREATE TABLE users(id serial PRIMARY KEY,
name VARCHAR,
position VARCHAR,
department VARCHAR
);
CREATE TABLE departments(
id serial PRIMARY KEY,
name VARCHAR,description VARCHAR,
employeesCount INT
);
CREATE TABLE news(
id serial PRIMARY KEY,
description VARCHAR,
type VARCHAR
);
CREATE TABLE department_users(
id serial PRIMARY KEY,
dept_id INT,
user_id INT
);
CREATE TABLE department_news(
id serial PRIMARY KEY,
dept_id INT,
user_id INT,
news_id INT
);
