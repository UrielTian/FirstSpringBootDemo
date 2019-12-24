package com.example.firstspringbootdemo.service;

import com.example.firstspringbootdemo.bean.User;
import com.example.firstspringbootdemo.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TransActionService {

    @Resource
    UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public void testTransaction(){
        User user1 = new User("ty1","ty1");
        userDao.addUser(user1);

        int i = 1/0;

        User user2 = new User("ty2","ty2");
        userDao.addUser(user2);

    }
}
