package com.example.firstspringbootdemo.controller;

import com.example.firstspringbootdemo.service.TransActionService;
import com.example.firstspringbootdemo.bean.User;
import com.example.firstspringbootdemo.dao.UserDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    UserDao userDao;

    @Resource
    TransActionService transActionService;

    @RequestMapping(value = "/user")
    public String printUser(String name){
        System.out.println(name);
        User user = userDao.queryUserByName(name);
        return user.getPassword();
    }

    @RequestMapping(value = "/transaction")
    public String trans(){
        transActionService.testTransaction();
        return "事务测试";
    }

}
