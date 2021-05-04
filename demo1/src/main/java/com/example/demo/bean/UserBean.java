package com.example.demo.bean;

public class UserBean {
    private String name;
    private String password;
    private String role;
    private String field;

    private BookBean bookbean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() { return  role; }

    public void setRole(String role) { this.role = role; }

    public String getfiled() { return  field; }

    public void setfield(String role) { this.field = field; }


    public BookBean getBookBean(){return bookbean;}
    public void setBookBean(BookBean bookbean){this.bookbean=bookbean;}


    public UserBean(String name, String password, String role, String field){
        this.name = name;
        this.password=password;
        this.role=role;
        this.field=field;
    }
}

