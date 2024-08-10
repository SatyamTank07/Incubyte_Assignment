package com.incubyte;

public class LibrarySystem
{
    // all classes are created here
    static class Book{
        private String ISBN;
        private String title;
        private String author;
        private int year;
        private int copiesAvailable;

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
            this.copiesAvailable = copiesAvailable;
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
    }
}
