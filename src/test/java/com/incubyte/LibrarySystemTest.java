package com.incubyte;

import org.junit.jupiter.api.Test;
//import org.junit.Test;

import com.incubyte.LibrarySystem.LibraryManagementSystem;
import com.incubyte.LibrarySystem.Book;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySystemTest 
{
    // main code will go here as a Test form
    @Test
    public void createClassTest(){

        LibrarySystem.Book book1 = new LibrarySystem.Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 3);
        assertEquals("978-0135166307",book1.getISBN(),"ISBN should match the provided value");
        assertEquals("Effective Java",book1.getTitle(),"Title should match the provided value");
        assertEquals("Joshua Bloch",book1.getauthor(),"Author should match the provided value");
        assertEquals(3,book1.getCopiesAvailable(),"Year should match the provided value");
        assertEquals(2018, book1.getyear(),"Year should match the provided value");

    }

    @Test
    public void testBookCreationWithEmptyValues() {
        // Test with empty ISBN, title, and author
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LibrarySystem.Book("", "", "", 2018, 3);
        });

        // Validate that the exception message is correct
        assertEquals("ISBN, title, and author must not be empty", exception.getMessage());
    }

    @Test
    public void testBookCreationWithInvalidYear() {
        // Test with invalid year (e.g., negative year)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LibrarySystem.Book("978-0135166307", "Effective Java", "Joshua Bloch", 1757, 3);
        });

        // Validate that the exception message is correct
        assertEquals("Year must be a Greater then 1800 number", exception.getMessage());
    }

    @Test
    public void testBookCreationWithInvalidCopiesAvailable() {
        // Test with invalid copiesAvailable (e.g., negative number of copies)
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new LibrarySystem.Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, -3);
        });

        // Validate that the exception message is correct
        assertEquals("Copies available must be non-negative", exception.getMessage());
    }

    @Test
    public void testAddBook() {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Book book = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 1);

        library.addBook(book);

        Book addedBook = library.getBook("978-0135166307");
        assertNotNull(addedBook, "Book should be added to the library");
    }

    @Test
    public void testAddNullBook() {
        LibraryManagementSystem library = new LibraryManagementSystem();

        try {
            library.addBook(null);
            fail("Expected NullPointerException not thrown");
        } catch (NullPointerException e) {
            assertEquals("Book cannot be null", e.getMessage());
        }
    }

    @Test
    public void testAddBookWithZeroCopies() {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Book book = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 0);

        // Add book to library
        library.addBook(book);

        // Retrieve the book and check the number of copies
        Book addedBook = library.getBook("978-0135166307");
        assertNotNull(addedBook, "Book should be added to the library");
        assertEquals(1, addedBook.getCopiesAvailable(), "Book should have at least one copy");

    }

    @Test
    public void testAddBookWithExistingISBN() {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Book book = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 1);

        library.addBook(book);
        library.addBook(book); // Adding the same book again

        Book addedBook = library.getBook("978-0135166307");
        assertNotNull(addedBook, "Book should be added to the library");
        assertEquals(2, addedBook.getCopiesAvailable(), "Copies available should be 2");
    }

    @Test
    public void testBorrowBook() {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Book book1 = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 3);
        Book book2 = new Book("978-0596009205", "Head First Java", "Kathy Sierra, Bert Bates", 2005, 4);

        library.addBook(book1);
        library.addBook(book2);

        library.borrowBook("978-0135166307");
        assertEquals(2,book1.getCopiesAvailable(),"Copies available should be 2");
    }

    @Test
    public void testwithZerocopys() {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Book book1 = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 1);

        library.addBook(book1);

        library.borrowBook("978-0135166307");
        assertThrows(IllegalStateException.class, () -> {
            library.borrowBook("978-0135166307");
        }, "Should throw IllegalStateException when no copies are available to borrow");
    }

    @Test
    public void testReturnBookSuccessfully() {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Book book = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 1);

        library.addBook(book);
        library.borrowBook("978-0135166307");
        library.returnBook("978-0135166307");

        Book returnedBook = library.getBook("978-0135166307");
        assertEquals(1, returnedBook.getCopiesAvailable(), "Copies should increase after returning the book");
    }

    @Test
    public void testReturnNonExistentBook() {
        LibraryManagementSystem library = new LibraryManagementSystem();

        String nonExistingISBN = "000-0000000000";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook(nonExistingISBN);
        });

        String expectedMessage = "Book with ISBN '" + nonExistingISBN + "' not found.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage, "Exception message should match expected message.");
    }

    @Test
    public void testReturnBookWithNoBorrowedCopies() {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Book book = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 1);

        library.addBook(book);

        // This should throw an IllegalStateException because no copies have been borrowed.
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            library.returnBook("978-0135166307");
        });

        // Check that the exception message is as expected
        assertEquals("No copies have been borrowed.", exception.getMessage());

    }

    @Test
    public void testViewAvailableBooks_NoBooks() {
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the method
        library.viewAvailableBooks();

        // Restore original System.out
        System.setOut(originalOut);

        // Verify the output
        assertEquals("No books available in the library." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void testViewAvailableBooks_WithBooks() {
        LibraryManagementSystem library = new LibraryManagementSystem();

        Book book1 = new Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 3);
        Book book2 = new Book("978-0321356680", "Java Concurrency in Practice", "Brian Goetz", 2006, 2);

        library.addBook(book1);
        library.addBook(book2);

        // Capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the method
        library.viewAvailableBooks();

        // Restore original System.out
        System.setOut(originalOut);

        // Verify the output
        String expectedOutput = "Available Books:" + System.lineSeparator()
                + "Effective Java by Joshua Bloch, 2018 - 3 copies available" + System.lineSeparator()
                + "Java Concurrency in Practice by Brian Goetz, 2006 - 2 copies available" + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }


}
