package com.example.demo.service;
import java.util.List;
import com.example.demo.bean.BookBean;
import com.example.demo.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    public BookBean getbook1(String bookId) {
        return bookDao.getbook1(bookId);
    }
    public void deletebook1(String bookId) {bookDao.deletebook1(bookId); }
    public int changebook1(BookBean bookBean) {
        return bookDao.changebook1(bookBean);
    }
    public  void addbook1(String bookId,String bookName,String authorName) {bookDao.addbook1(bookId,bookName,authorName); }
    public List<BookBean> getallbook(){return bookDao.getallbook();}
    public void uploadPDF(String bookPDF, String bookId){bookDao.uploadPDF(bookPDF,bookId);}
    public String download(String bookId){return bookDao.download(bookId);}
}

