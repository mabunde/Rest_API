# New Portal
## Author
By Sitati Solomon

## Description
NewsPortal is an Organisational news dissemination application. Users can post news to the general staff or to staff in a specific department.
## Setup/Installation Requirements
* git clone https://github.com/mabunde/Rest_API.git
* cd to news-portal/
* open with intellij IDEA or your preferred editor
* run create.sql file for database setup
* Run the gradle

## DATABASE SETUP
* CREATE DATABASE new_portal;
* CREATE TABLE users(id serial PRIMARY KEY,name VARCHAR,position VARCHAR,department VARCHAR);
* CREATE TABLE departments(id serial PRIMARY KEY,name VARCHAR,description VARCHAR,employeesCount INT);
* CREATE TABLE news(id serial PRIMARY KEY,description VARCHAR,type VARCHAR);
* CREATE TABLE department_users(id serial PRIMARY KEY,dept_id INT,user_id INT);
* CREATE TABLE department_news(id serial PRIMARY KEY,dept_id INT,user_id INT,news_id INT);

#### TEST DATABASE SETUP
* CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;

## API routes to use
* GET "/departments"
* GET "/departments/:departmentId"
* GET "/departments/:departmentId/users"
* GET "/departments/:departmentId/users/:userId/details"
* POST "/departments/new"
* POST "/departments/:departmentId/employees/new"
## Technologies Used
* Java
* Spark
* Handlebars
* PostgreSQL
* Sql2o
## Support and contact details
For support contact solomonsitati39@gmail.com

## License
MIT License

# Copyright (c) 2021 Sitati Solomon