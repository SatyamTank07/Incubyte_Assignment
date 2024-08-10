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
