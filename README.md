# bookstore
# Bookstore Management System - Spring Boot

## Description

This is a simple application created using Java, Spring Boot Framework and MySql database.
Bookstore management system is a software that allows people to register into the application using their credintials, search for the books they want to read and purchase them.

## Main Functionalities

### Liquibase
Tables and their relationships are created using xml files (liquibase). The roles of the user are inserted into the database from a csv file, as soon as the application starts running.
When a user is registered into the applicaion, he gets a ROLE_CUSTOMER role.

### Spring Security
To secure the application is used Spring Security Framework. When a user write his credintials to register into the application, his password is encrypted using BCrypt Password Encoder.
Only the encrypted password is saved into the database.
When a user try to login into the application, he should write the right credintials that are stored into the database and if the authentication is successful a unique Token is created.
The token is used to identify each user and allows them access to the application. The Token has an expiration time. 
A user tha is not logged in into the application can only access some endpoints: login endpoint, register endpoint, search books endpoint.

### External API
An external API is called to get the books that the user search for. The user can specify the name of the book (name parameter), 
can specify if he wants to search by title (intitle keyword), search by category (subject keyword) etc. A JSON is sent from the external API.

### Mail Link Verification
When a user write his credentials to register into the application, an email is sent to his email address that contains a link. 
The user should click on the link to activate his account. The link expires after 24 hours.
