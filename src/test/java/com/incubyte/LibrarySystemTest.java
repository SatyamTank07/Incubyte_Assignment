package com.incubyte;

import org.junit.jupiter.api.Test;
//import org.junit.Test;

import com.incubyte.LibrarySystem.LibraryManagementSystem;
import com.incubyte.LibrarySystem.Book;

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
        assertEquals(2, addedBook.getCopiesAvailable(), "Copies available should be 4");
    }

}
