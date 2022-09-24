# Case_Study_1-Core_Java_Project

Problem Statement - The management of a company decides to store the personal details of all its employees in a MySQL Database. It is required to create a Java SE application that can fulfil the following requirements.

Requirements:

The Following are the expectations should be met in application development:
1.	It should be a Maven Based Project
2.	Should be a Layered Application with repository, service etc.,
3.	Log4j should be used for Logging, log messages should be stored in a file
4.	Java Collections, Exception Handling, JDBC API’s, Java 8 Streams should be used
5.	User input is done through Scanner Class, and it should menu driven
6.	Proper Exception Handling Should be Done

•	The Database should have a table with following Columns
  o	FirstName
  o	Last Name
  o	Address
  o	Email address
  o	Phone number
  o	Date Of Birth
  o	Wedding Date

• Query to create the table:
  o CREATE TABLE `lumen_employees` (
      `firstName` varchar(45) NOT NULL,
      `lastName` varchar(45) NOT NULL,
      `address` varchar(45) NOT NULL,
      `email` varchar(45) NOT NULL,
      `phoneNumber` mediumtext NOT NULL,
      `dateOfBirth` date NOT NULL,
      `dateOfWedding` date NOT NULL,
      PRIMARY KEY (`email`)
    );

  
•	Java Application should have following functionalities
  1.	Add employee details
  2.	Get the List of employees by their firstName.
  3.	Get the List of employees with FirstName and Phone Number
  4.	Update the email and phoneNumber of a particular employee.
  5.	Delete Details of a Particular employee by firstName
  6.	Get a list of employees with their firstName and emailAddress whose Birthday falls on the given date
  7.	Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date
