package com.example.demo.dao;


import com.example.demo.bean.BookBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface BookDao {
    BookBean getbook1(String bookId);

    void addbook1(@Param("bookId") String bookId,@Param("bookName") String bookName, @Param("authorName") String authorName);

    int changebook1(BookBean bookBean);

    void deletebook1(@Param("bookId") String bookId);
    void uploadPDF(@Param("bookPDF") String bookPDF, @Param("bookId") String bookId);
    String download(@Param("bookId") String bookId);

    List<BookBean> getallbook();
}

