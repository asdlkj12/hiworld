package com.example.demo.controller;

import com.example.demo.bean.BookBean;
import com.example.demo.bean.UserBean;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/user")
public class reviewController {

    @Autowired
    private BookService bookService;


    @RequestMapping("/ndr")  // jump to the list of articles that can be reviewed

    public String show(Model m){

        List<BookBean> books=this.bookService.getndrbook(); //Put the books that can be reviewed into the list
        m.addAttribute("books", books);  //Used to display the list data in the front end

        return "ndr";  //jump to the page that list of the articles that can be reviewed

    }

    @RequestMapping("/cmr")  // jump to the list of articles that has been reviewed already
    public String show1(Model m){
        List<BookBean> books=this.bookService.getcmrbook(); //put the books that has been reviewed into the list
        m.addAttribute("books", books);

        return "cmr"; //jump
    }

    @RequestMapping(value = "/review/{bookId}") // jump to the review interface
    public String getreview(@PathVariable String bookId) {
        // Set the value of the "being reviewed or checked" attribute in the database to the ID of the book being reviewed
        bookService.reviewingbook(bookId);
        return "review_page"; //jump
    }

    @RequestMapping(value = "/check/{bookId}") // jump to the check interface to see the review information you have give
    public String check(Model m,@PathVariable String bookId) {
        // Set the value of the "being reviewed or checked" attribute in the database to the ID of the book being checked
        bookService.reviewingbook(bookId);
        List<BookBean> book1=this.bookService.getcheck();
        ////put the review information of book that be checked into the list
        m.addAttribute("books", book1);

        return "checkcmr"; //jump to "checkcmr" to display review information
    }


    @RequestMapping("/backreview")
    public String change(@RequestParam("comment") String comment, @RequestParam("judge") String judge){
        //The method used to conduct the review and submit the review content to the database
        String judge1= bookService.getjudge1();
        String judge2= bookService.getjudge2();
        String judge3= bookService.getjudge3();
        //By extracting the judge attribute,judge which group of review information (the first group, the second group, the third group) in the modified database is submitted
        String judge0=judge;
        String comment0=comment;

        if(judge1.equals("null")) {
            // if the first group not be used, submit information to the first group
            bookService.changejudge1(judge0, comment0);
            return "interface_reviewer"; // end
        }

        if(judge2.equals("null")) {
            //if the second group not be used, submit information to the first group
            bookService.changejudge2(judge0, comment0);
            return "interface_reviewer";//end
        }

        if(judge3.equals("null")) {
            // if the third group not be used, submit information to the first group
            bookService.changejudge3(judge0, comment0);
            return "interface_reviewer";
        }


        return "interface_reviewer";//end
    }

}
