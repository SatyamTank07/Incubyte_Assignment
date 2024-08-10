package com.incubyte;
import java.util.LinkedHashMap;
import java.util.Map;

public class LibrarySystem
{
    // all classes are created here
    static class Book{
        private String ISBN;
        private String title;
        private String author;
        private int year;
        private int copiesAvailable;
        private int borrowCount;

        public Book(String ISBN, String title, String author, int year, int copiesAvailable) {

            if (ISBN == null || ISBN.isEmpty() ||
                    title == null || title.isEmpty() ||
                    author == null || author.isEmpty()) {
                throw new IllegalArgumentException("ISBN, title, and author must not be empty");
            }

            if (year < 1800) {
                throw new IllegalArgumentException("Year must be a Greater then 1800 number");
            }

            if (copiesAvailable < 0) {
                throw new IllegalArgumentException("Copies available must be non-negative");
            }

            this.ISBN = ISBN;
            this.title = title;
            this.author = author;
            this.year = year;
            // Ensure at least one copy is available
            this.copiesAvailable = Math.max(copiesAvailable, 1);
            this.borrowCount = 0;

        }

        public String getISBN() {
            return ISBN;
        }

        public String getTitle() {
            return title;
        }

        public String getauthor(){
            return author;
        }

        public int getyear(){
            return year;
        }
        public int getCopiesAvailable() {
            return copiesAvailable;
        }

        public void addNewCopy() {
            copiesAvailable += this.copiesAvailable;
        }

        public void borrowBook() {
            if (copiesAvailable > 0) {
                copiesAvailable--;
                borrowCount++;
            } else {
                throw new IllegalStateException("No copies available to borrow.");
            }
        }

        public void returnBook() {
            if (borrowCount > 0) {
                copiesAvailable++;
                borrowCount--;
            } else {
                throw new IllegalStateException("No copies have been borrowed.");
            }
        }
    }

    interface Library {
        void addBook(Book book);

        void borrowBook(String ISBN);

        void returnBook(String ISBN);

        Book getBook(String ISBN);
    }


    static class LibraryManagementSystem implements Library {
        private Map<String, Book> bookMap;

        public LibraryManagementSystem() {
            this.bookMap = new LinkedHashMap<>();
        }

        @Override
        public void addBook(Book book) {
            if (book == null) {
                throw new NullPointerException("Book cannot be null");
            }
            if (book.getCopiesAvailable() < 1) {
                throw new IllegalArgumentException("Book should have at least one copy");
            }
            if (bookMap.containsKey(book.getISBN())) {
                bookMap.get(book.getISBN()).addNewCopy();
            } else {
                bookMap.put(book.getISBN(), book);
            }
        }

        @Override
        public void borrowBook(String ISBN) {
            if (bookMap.containsKey(ISBN)) {
                bookMap.get(ISBN).borrowBook();
            }else {
                throw new IllegalArgumentException("Book with ISBN '" + ISBN + "' not found.");
            }
        }

        @Override
        public void returnBook(String ISBN) {
            if (bookMap.containsKey(ISBN)) {
                try {
                    bookMap.get(ISBN).returnBook();
                } catch (IllegalStateException e) {
                    throw e;
                }
            }else {
                throw new IllegalArgumentException("Book with ISBN '" + ISBN + "' not found.");
            }
        }
        @Override
        public Book getBook(String ISBN) {
            return bookMap.get(ISBN);
        }
    }
}
