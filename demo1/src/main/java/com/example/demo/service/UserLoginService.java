package com.example.demo.service;

import com.example.demo.bean.UserBean;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserLoginService {

    @Autowired
    private UserDao userdao;

    //user login
    public UserBean userLogin(String name,String password,String role){
        UserBean userbean = userdao.userLogin(name,password,role);
        return userbean;
    }

    //user register
    public void adduser(String name,String password,String role){
        userdao.adduser(name,password,role);

    }
    public void setrewer(String name){userdao.setrewer(name);};
    public void setName(String name){userdao.setName(name);};

}
