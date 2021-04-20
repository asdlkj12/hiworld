package com.example.demo.dao;

import com.example.demo.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserDao {

    UserBean userLogin(@Param("name") String name,@Param("password") String password, @Param("role") String role);

    //注册新用户(方式1)
    void adduser(@Param("name") String name, @Param("password") String password, @Param("role") String role);

}
