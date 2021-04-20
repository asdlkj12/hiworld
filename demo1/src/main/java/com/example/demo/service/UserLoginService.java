package com.example.demo.service;

import com.example.demo.bean.UserBean;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserLoginService {

    /**
     * 注入dao
     */
    @Autowired
    private UserDao userdao;

    //用户登录
    public UserBean userLogin(String name,String password,String role){
        UserBean userbean = userdao.userLogin(name,password,role);
        return userbean;
    }

    //注册新用户
    public void adduser(String name,String password,String role){
        userdao.adduser(name,password,role);

    }
}
