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
    //
    public BookBean getbook1(String bookId) {
        return bookDao.getbook1(bookId);
    }
    public void deletebook1(String bookId) {bookDao.deletebook1(bookId); }
    public  void addbook1(String bookId,String bookName,String authorName, String keyWord) {bookDao.addbook1(bookId,bookName,authorName,keyWord); }
    public List<BookBean> getallbook(){return bookDao.getallbook();}
    public void uploadPDF(String bookPDF, String bookId){bookDao.uploadPDF(bookPDF,bookId);}
    public String download(String bookId){return bookDao.download(bookId);}
    public List<BookBean> getndrbook(){return bookDao.getbookndr();}
    //get the books that need to be reviewed
    public List<BookBean> getcmrbook(){return bookDao.getbookcmr();}
    //get the books that had been reviewed
    public List<BookBean> getcheck(){return bookDao.getcheck();}  // re-look the reviewed information
    public List<String> getrewer(String bookId){return bookDao.getrewer(bookId);} //get reviewers with same keyword of book
    //change judge from "null" to "yes" or "no" in database

    //get three judge information
    public void changejudge1(String judge,String comment){bookDao.changejudge1(judge,comment);};
    public void changejudge2(String judge,String comment){bookDao.changejudge2(judge,comment);};
    public void changejudge3(String judge,String comment){bookDao.changejudge3(judge,comment);};
    public void reviewingbook(String bookId){bookDao.reviewingbook(bookId);};

    public String getjudge1(){
        String judgeA = bookDao.getjudge1();
        return judgeA;
    }
    public String getjudge2(){
        String judgeB = bookDao.getjudge2();
        return judgeB;
    }
    public String getjudge3(){
        String judgeC = bookDao.getjudge3();
        return judgeC;
    }
    public String getName(){
        String author=bookDao.getName();
        return  author;
    }
    //get reviewers' comment
    public List<BookBean> getComment(String bookId){return bookDao.getComment(bookId);}
    //get certified book
    public List<BookBean> vertify(){
        return bookDao.vertify();
    }

    //randomly assign three pre reviewer
    public void distri_re(String str1,String str2,String str3,String bookId){bookDao.distri_re(str1,str2,str3,bookId);};
}

