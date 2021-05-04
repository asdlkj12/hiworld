package com.example.demo.dao;

import com.example.demo.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserDao {

    UserBean userLogin(@Param("name") String name,@Param("password") String password, @Param("role") String role);

    //user register
    void adduser(@Param("name") String name, @Param("password") String password, @Param("role") String role);
    void setrewer(@Param("name") String name);
    //set the name of author
    void setName(@Param(("name")) String name);

}
