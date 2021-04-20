package com.example.demo.bean;

public class BookBean {
    private String bookId;
    private String bookName;
    private String authorName;
    private String bookPDF;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String name) {
        this.bookName = bookName;
    }

    public String getAuthorName() { return authorName; }

    public void setAuthorName(String name) {
        this.authorName = authorName;
    }

    public String getBookPDF() { return bookPDF; }

    public void setBookPDF(String bookPDF) {this.bookPDF = bookPDF; }


    public BookBean(String bookId, String bookName, String authorName, String bookPDF) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookPDF=bookPDF;
    }

}
