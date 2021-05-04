package com.example.demo.bean;

public class BookBean {
    private String bookId;
    private String bookName;
    private String authorName;
    private String author;
    private String judge1; // Create three judgments(YES or NO)
    private String judge2;
    private String judge3;
    private String rewer1; // three reviewers has review one novel
    private String rewer2;
    private String rewer3;
    private String comment1; // three comments with judgments
    private String comment2;
    private String comment3;
    private String judgerewer; // who are judging book
    private String revbook;   // which book is being judge
    private String bookPDF;
    private String keyWord;  //keyword of book

    private String canre1;  //  Three pre assigned reviewers
    private String canre2;
    private String canre3;
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() { return authorName; }

    public String getBookPDF() { return bookPDF; }

    public void setBookPDF(String bookPDF) {this.bookPDF = bookPDF; }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }



    public String getJudge1() {
        return judge1;
    }

    public void setJudge1(String judge1) {
        this.judge1 = judge1;
    }

    public String getJudge2() {
        return judge2;
    }

    public void setJudge2(String judge2) {
        this.judge2 = judge2;
    }

    public String getJudge3() {
        return judge3;
    }

    public void setJudge3(String judge3) {
        this.judge3 = judge3;
    }




    public String getRewer1() {
        return rewer1;
    }

    public void setRewer1(String rewer1) {
        this.rewer1 = rewer1;
    }

    public String getRewer2() {
        return rewer2;
    }

    public void setRewer2(String rewer2) {
        this.rewer2 = rewer2;
    }

    public String getRewer3() {
        return rewer3;
    }

    public void setRewer3(String rewer3) {
        this.rewer3 = rewer3;
    }




    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }







    public String getJudgerewer() {
        return judgerewer;
    }

    public void setJudgerewer(String judgerewer) {
        this.judgerewer = judgerewer;
    }

    public String getRevbook() {
        return revbook;
    }

    public void setRevbook(String revbook) {
        this.revbook = revbook;
    }
    public String getKeyWord(){return keyWord;};
    public void setKeyWord(String keyWord){this.keyWord=keyWord;};
    public String getAuthor(){return author;};
    public void setAuthor(String author){this.author=author;};

    public String getCanre1() {
        return canre1;
    }
    public void setCanre1(String canre1) { this.canre1 = canre1; }

    public String getCanre2() {
        return canre2;
    }
    public void setCanre2(String canre2) { this.canre2 = canre2; }

    public String getCanre3() {
        return canre3;
    }
    public void setCanre3(String canre3) { this.canre3 = canre3; }





    public BookBean(String bookId, String bookName, String authorName,String judge1, String judge2,String judge3,String comment1,String comment2,String comment3, String rewer1,String rewer2,String rewer3,String revbook,String keyWord,String author,String canre1,String canre2,String canre3) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.judge1 = judge1;
        this.judge2 = judge2;
        this.judge3 = judge3;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.comment3 = comment3;
        this.rewer1 = rewer1;
        this.rewer2 = rewer2;
        this.rewer3 = rewer3;
        this.revbook = revbook;
        this.keyWord= keyWord;
        this.author=author;
        this.canre1= canre1;
        this.canre2= canre2;
        this.canre3= canre3;

    }

}
