RESTful Web Service using spring-boot, JPA, MySQL for Managing Listing for online advertising service

Provides multiple API end points. Try it out @http://localhost:8091/swagger-ui.html#/

This project built using Java and the following tools:

    # Spring Boot as server side framework
    # Maven as build automation tool
    # Hibernate as ORM / JPA implementation
    # MySQL as database implementation
    # Spring Data JPA as the top layer over Hibernate
     
     Quick Start

     Prerequisites
     1. Create a MySQL database
     CREATE DATABASE test;

     2. Modify MySQL username and password

    Open src/main/resources/application.properties file
    Change spring.datasource.username and spring.datasource.password properties to match your MySQL connection

    Build project
    Build the application using the following maven command:
    -->   mvn clean install

    Run project
    After packaging the application into an executable .jar file, you can start the server running the following command using any terminal in the project directory:
    -- >  mvn spring-boot:run

    The server will start running at http://localhost:8091.

    After running

    POST /vehicles JSON Input format:

     {
    "vin": "c32h24",
    "make": "Toyota",
    "dealerName": "Jean",
    "model": "GGGGTT",
    "year": 2021,
    "registeredMobile": 33667744,
    "price": 7000.0,
    "postingDate": "06-06-2021",
    "status": "DRAFT"
    }

    PUT /vehicles/{id} JSON Input format to change the given state(PUBLISHED, DRAFT) of Car
    Get information about vehicle  http://localhost:8091/vehicles

    API Documentation
    When server is up and running, you can use swagger to explore the available endpoints and try them out. Find it at: http://localhost:8091/swagger-ui.html#/








