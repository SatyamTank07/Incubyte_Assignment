package com.incubyte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
//import org.junit.Test;

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

}
