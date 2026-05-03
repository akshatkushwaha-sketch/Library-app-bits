# Library Management System

A Spring Boot web application to manage Books and Authors, built with Spring MVC, JPA, and JSP views.

## Tech Stack

- Java 17+ / Spring Boot 3.2
- Spring Data JPA + H2 (in-memory database)
- JSP + JSTL for views
- JUnit 5 + Mockito for tests

## Running the App

```bash
export PATH="/opt/homebrew/bin:$PATH"
mvn spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080).

The database is seeded automatically with 10 authors and 10 books on startup.

## Running Tests

```bash
mvn test
```

18 tests across repository and service layers.

## Features

- **Authors**: Create, list, and edit authors. Duplicate email detection.
- **Books**: Create, list, and edit books with author assignment.
- **H2 Console**: Available at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  - JDBC URL: `jdbc:h2:mem:librarydb` | Username: `sa` | Password: *(blank)*

## Project Structure

```
src/main/java/com/library/
  entity/          Author.java, Book.java
  repository/      AuthorRepository.java, BookRepository.java
  service/         AuthorService.java, BookService.java
  controller/      HomeController.java, AuthorController.java, BookController.java
src/main/webapp/WEB-INF/views/
  home.jsp, author/, book/
src/test/java/com/library/
  repository/      AuthorRepositoryTest.java
  service/         AuthorServiceTest.java, BookServiceTest.java
```
