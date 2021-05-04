package com.example.demo.controller;
import com.example.demo.bean.BookBean;
import com.example.demo.service.BookService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class BookController {
    @Autowired
    private BookService bookService;
    //add the book
    @RequestMapping("/add")
    public String show2() {
        return "add";
    }
    //enter the author interface
    @RequestMapping("/interface_author")
    public String show(Model m) {
        //use list to get all information in Mysql
        List<BookBean> books = this.bookService.getallbook();
        m.addAttribute("books", books);
        return "interface_author";
    }
   //enter to the university interface
    @RequestMapping("/interface_university")
    public String show2(Model m) throws IOException{
        //get the certified books and add the stamp
        List<BookBean> books = this.bookService.vertify();
        m.addAttribute("books", books);
        for(int j=0;j<books.size();j++) {
            try {
                PdfReader reader = new PdfReader(books.get(j).getBookPDF());
                PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(books.get(j).getBookPDF()+".pdf"));
                // Use System Font
                BaseFont base = BaseFont.createFont();
                Rectangle pageRect;
                PdfGState gs = new PdfGState();
                //set text opacity
                gs.setFillOpacity(0.6f);
                gs.setStrokeOpacity(0.6f);
                //Get the total number of PDF pages
                int total = reader.getNumberOfPages() + 1;
                JLabel label = new JLabel();
                FontMetrics metrics;
                int textH;
                int textW;
                label.setText("the essay was certified");
                metrics = label.getFontMetrics(label.getFont());
                //Get the width and height of the text
                textH = metrics.getHeight();
                textW = metrics.stringWidth(label.getText());
                PdfContentByte under;
                for (int i = 1; i < total; i++) {
                    pageRect = reader.getPageSizeWithRotation(i);
                    //Get a watermark text
                    under = stamper.getOverContent(i);
                    under.saveState();
                    under.setGState(gs);
                    under.beginText();
                    //set watermark color
                    under.setColorFill(BaseColor.LIGHT_GRAY);
                    //set watermark size
                    under.setFontAndSize(base, 8);
                    int position = 0;
                    int interval = -3;
                    for (int height = interval + textH; height < pageRect.getHeight(); height = height + textH * 3) {
                        for (int width = interval + textW - position * 150; width < pageRect.getWidth() + textW; width = width + textW) {
                            //add the watermark and set the incline
                            under.showTextAligned(Element.ALIGN_LEFT, "the essay was certified", width - textW, height - textH, 25);
                        }
                        position++;
                    }
                    //add watermark
                    under.endText();
                }
                //close stream
                stamper.close();
                reader.close();
                return "interface_university";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "interface_university";
    }
    //get one book by bookId
    @RequestMapping(value = "getBook/{id}", method = RequestMethod.GET)
    public String getbook1(@PathVariable String bookId) {
        return bookService.getbook1(bookId).toString();
    }

    //delete one book by bookId
    @RequestMapping(value = "/delete/{bookId}")
    public String deletebook1(@PathVariable String bookId) {
        bookService.deletebook1(bookId);
        return "enter_author";
    }
    //update one book by bookId
    @RequestMapping(value = "/update/{bookId}", method = {RequestMethod.POST, RequestMethod.GET})
    public String changebook1(BookBean bookBean, @PathVariable String bookId) {
        bookService.deletebook1(bookId);
        return "update";
    }
    //add one book
    @RequestMapping(value = {"/insert"}, method = RequestMethod.POST)
    public String addbook1(@RequestParam("bookId") String bookId,
                           @RequestParam("bookName") String bookName,
                           @RequestParam("keyWord") String keyWord,
                           @RequestParam("file") MultipartFile file,
                           Model m) {
        String authorName = bookService.getName();
        bookService.addbook1(bookId, bookName, authorName, keyWord);
        List<String> canrewer= bookService.getrewer(bookId);//get same keyword reviewer into a list
        int max=canrewer.size();

        Random rand=new Random(); // randomly get three different elements from list
        int one = (int)rand.nextInt(max-0)+0;
        int two = (int)rand.nextInt(max-0)+0;
        while(two==one){
            two = (int)rand.nextInt(max-0)+0;

        }
        int three = (int)rand.nextInt(max-0)+0;
        while(three==one || three==two){

            three = (int)rand.nextInt(max-0)+0;


        }

        String str1=canrewer.get(one);
        String str2=canrewer.get(two);
        String str3=canrewer.get(three);

        bookService.distri_re(str1,str2,str3,bookId);//design /change attributes in database
        // end of randomly assign
        //upload the file
        if (file.isEmpty()) {
            return "";
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
    //re-upload the file for update
    @RequestMapping(value = {"/update_information"}, method = RequestMethod.POST)
    public String updatebook1(@RequestParam("bookId") String bookId,
                              @RequestParam("bookName") String bookName,
                              @RequestParam("keyWord") String keyWord,
                              @RequestParam("authorName") String authorName,
                              @RequestParam("file") MultipartFile file,
                              Model m) {
        bookService.addbook1(bookId, bookName, authorName, keyWord);
        if (file.isEmpty()) {
            return "";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "C:/file";
        bookService.uploadPDF(path + "/" + fileName, bookId);
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //determine if it is exists
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "enter_university";
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
    //preview and download in reviewer interface
    @RequestMapping(value="/preview/{bookId}")
    public String download(@PathVariable String bookId, HttpServletResponse response) {
        //Get the file by the save folder path of the file and the name of the file
        String bookPDF=bookService.download(bookId);
        File file = new File(bookPDF);
        //if file exists
        if (file.exists()) {
            //Set the format of the response as Force-Download. Once you click the "Download" button, the file will be downloaded automatically
            response.setContentType("application/force-download");
            //Name the file by setting the header
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            //read and write operation
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os=null;
                os = response.getOutputStream();
                response.reset();

                //read from file
                int i = bis.read(buffer);
                while (i != -1) {
                    //write to output stream
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //close stream
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
        return "ndr";
    }
    //preview and download online in the university interface
    @RequestMapping(value="/preview_certified/{bookId}")
    public String download2(@PathVariable String bookId, HttpServletResponse response) {
        //Get the file by the save folder path of the file and the name of the file
        String bookPDF=bookService.download(bookId);
        File file = new File(bookPDF+".pdf");
        //if file exists
        if (file.exists()) {
            //Set the format of the response as Force-Download. Once you click the "Download" button, the file will be downloaded automatically
            response.setContentType("application/force-download");
            //Name the file by setting the header
            response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            //read and write operation
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os=null;
                os = response.getOutputStream();
                response.reset();

                //read from file
                int i = bis.read(buffer);
                while (i != -1) {
                    //write to output stream
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //close stream
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
        return "enter_university";
    }
    //see the reviewer's comment
    @RequestMapping("/comment/{bookId}")
    public String show3(@PathVariable String bookId, Model m) {
       List<BookBean> books=bookService.getComment(bookId);
       m.addAttribute("books", books);
       return "comment";
    }
}





