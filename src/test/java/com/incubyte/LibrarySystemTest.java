package com.incubyte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LibrarySystemTest 
{
    // main code will go here as a Test form
    @Test
    public void createClassTest(){
//        LibrarySystem library = new LibraryManagementSystem();

        LibrarySystem.Book book1 = new LibrarySystem.Book("978-0135166307", "Effective Java", "Joshua Bloch", 2018, 3);
        assertEquals("ISBN should match the provided value",book1.getISBN(),"978-0135166307");
        assertEquals("Title should match the provided value",book1.getTitle(),"Effective Java");
        assertEquals("Author should match the provided value",book1.getauthor(),"Joshua Bloch");
        assertEquals("Year should match the provided value",book1.getyear(),2018);
        assertEquals("Year should match the provided value",book1.getCopiesAvailable(),3);
    }

}
