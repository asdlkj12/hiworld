package com.example.demo.dao;


import com.example.demo.bean.BookBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface BookDao {
    //get one book information
    BookBean getbook1(String bookId);
    //add book
    void addbook1(@Param("bookId") String bookId,@Param("bookName") String bookName, @Param("authorName") String authorName, @Param("keyWord") String keyWord);
    //delete book by id
    void deletebook1(@Param("bookId") String bookId);
    //upload the PDF
    void uploadPDF(@Param("bookPDF") String bookPDF, @Param("bookId") String bookId);
    //get the id from download book
    String download(@Param("bookId") String bookId);
    //get all book in Mysql
    List<BookBean> getallbook();
    //get the certified book
    List<BookBean> vertify();

    List<BookBean> getbookndr();
    //get the books that need to be reviewed
    List<BookBean> getbookcmr();
    //get the books that had been reviewed
    List<String> getrewer(@Param("bookId") String bookId);
    //get reviewers with same keyword of book
    List<BookBean> getcheck(); // re-look the reviewed information
    //Modify a set of judges, comments and reviewers(who are reviewing) at one time

    void changejudge1(@Param("judge") String judge,@Param("comment") String comment);
    void changejudge2(@Param("judge") String judge,@Param("comment") String comment);
    void changejudge3(@Param("judge") String judge,@Param("comment") String comment);

    void reviewingbook(@Param("bookId") String bookId);
    public String getjudge1();
    public String getjudge2();
    public String getjudge3();
    //get the author name
    public String getName();
    //get the comment
    List<BookBean> getComment(String bookId);
    void distri_re(@Param("str1") String str1,@Param("str2") String str2,@Param("str3") String str3,@Param("bookId") String bookId);
    //randomly assign three pre reviewer

}

