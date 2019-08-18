# REST API for Car Service Center Appointment Management

## Technical stacks

Spring Boot - JPA, Schedule Job

Maven

Travis CI - CI build up on code push to github

Heroku - Auto deploy upon success build on Travis CI

PostgreSQL - Application Database on Heroku

H2 - In memory database for test

Swagger2 - REST API UI

## Pre-reqs

Java 8, Maven (build tool)

## How to run test

    ./mvnw test

## How to build and start on local

    build: ./mvnw clean package

    Start app: java -jar target/*.jar or ./mvnw spring-boot:run 

## Application Swagger URL
    
    https://jl-nielsen-assessment.herokuapp.com/swagger-ui.html