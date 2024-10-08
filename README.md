﻿# Incubyte_Assignment | Library Management System

## File Links
  - **LibrarySystem.java**: [View the file](https://github.com/SatyamTank07/incubyte_assignment/blob/main/src/main/java/com/incubyte/LibrarySystem.java)
  - **LibrarySystemTest.java**: [View the file](https://github.com/SatyamTank07/incubyte_assignment/blob/main/src/test/java/com/incubyte/LibrarySystemTest.java)

## Description

This project is a simple Library Management System written in Java. It allows users to manage a collection of books, including adding new books, borrowing books, and returning them. The system ensures that the number of available copies is correctly managed, and it also includes a set of unit tests to verify the functionality.

## Features

- **Add Books:** Allows adding new books to the library by specifying details such as ISBN, title, author, year of publication, and the number of available copies.
- **Borrow Books:** Enables users to borrow books, decrementing the available copy count. If no copies are available, the system throws an exception.
- **Return Books:** Users can return borrowed books, incrementing the available copy count. The system also handles cases where no copies were borrowed or the book doesn't exist in the library.
- **View Available Books:** Displays a list of all books currently available in the library, including the number of copies for each book.
- **Error Handling:** The system provides meaningful error messages and handles exceptions such as borrowing a book with no available copies or returning a non-existent book.

## Test Cases

- **Book Creation and Validation**: Ensures that books are created with valid attributes and that invalid data results in appropriate exceptions.
- **Adding Books**: Tests the addition of books to the library, verifying that duplicates are handled correctly by increasing the number of available copies.
- **Borrowing and Returning Books**: Checks that borrowing and returning books properly adjusts the number of available copies and handles edge cases such as borrowing with no available copies or returning non-existent books.
- **Library State**: Validates the library's ability to accurately report the current state of available books, including scenarios with no books available.

These test cases ensure the system handles normal operations and edge cases effectively, maintaining data integrity and reliability.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven (for managing dependencies and building the project)

## Installation

To set up this project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>

2. **Set up your development environment:**
  - Make sure you have Java 8 or later installed.

3. **Compile and Run the project:**
  ```bash
   mvn clean compile
   mvn test
