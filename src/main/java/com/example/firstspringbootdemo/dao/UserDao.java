package com.example.firstspringbootdemo.dao;

import com.example.firstspringbootdemo.bean.User;
import com.example.firstspringbootdemo.bean.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {
    @Resource
    UserMapper userMapper;
    public User queryUserByName(String name){
        return userMapper.queryUserByName(name);
    }

    public int addUser(User user){
        return userMapper.addUser(user.getName(),user.getPassword());
    }
}
