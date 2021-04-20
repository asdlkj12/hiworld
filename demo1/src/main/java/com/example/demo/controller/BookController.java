package com.example.demo.controller;
import com.example.demo.bean.BookBean;
import com.example.demo.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/add")
    public String show2() {
        return "add";
    }

    @RequestMapping("/interface_author")
    public String show(Model m) {
        List<BookBean> books = this.bookService.getallbook();
        m.addAttribute("books", books);
        return "interface_author";
    }
    @RequestMapping("/interface_university")
    public String show2(Model m) {
        List<BookBean> books = this.bookService.getallbook();
        m.addAttribute("books", books);
        return "interface_university";
    }

    @RequestMapping(value = "getBook/{id}", method = RequestMethod.GET)
    public String getbook1(@PathVariable String bookId) {
        return bookService.getbook1(bookId).toString();
    }


    @RequestMapping(value = "/delete/{bookId}")
    public String deletebook1(@PathVariable String bookId) {
        bookService.deletebook1(bookId);
        return "enter_author";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String changebook1(BookBean bookBean) {
        int result = bookService.changebook1(bookBean);
        if (result >= 1) {
            return "change successful";
        } else {
            return "change false";
        }
    }

    @RequestMapping(value = {"/insert"}, method = RequestMethod.POST)
    public String addbook1(@RequestParam("bookId") String bookId,
                           @RequestParam("bookName") String bookName,
                           @RequestParam("authorName") String authorName,
                           @RequestParam("file") MultipartFile file,
                           Model m) {
        bookService.addbook1(bookId, bookName, authorName);
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "C:/file";
        bookService.uploadPDF(path + "/" + fileName, bookId);
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "enter_author";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping(value="/download/{bookId}")
    public String download(@PathVariable String bookId, HttpServletResponse response) {
        //通过文件的保存文件夹路径加上文件的名字来获得文件
        String bookPDF=bookService.download(bookId);
        File file = new File(bookPDF);
        //当文件存在
        if (file.exists()) {
            //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            //进行读写操作
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os=null;
                os = response.getOutputStream();
                os.close();

                //从源文件中读
                int i = bis.read(buffer);
                while (i != -1) {
                    //写到response的输出流中
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //善后工作，关闭各种流
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "enter_author";
    }
}






