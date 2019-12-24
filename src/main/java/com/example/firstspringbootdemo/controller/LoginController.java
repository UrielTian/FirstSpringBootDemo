package com.example.firstspringbootdemo.controller;

//import com.example.firstspringbootdemo.config.WebSecurityConfig;

import com.example.firstspringbootdemo.config.WebSecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

/**
 * Created by ty on 2019/5/27.
 */
@Controller
public class LoginController {


    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account, Model model){
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginVerify")
    public String loginVerify(String username,String password,HttpSession session){

        if ("uriel".equals(username)&&"123456".equals(password)) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            return "redirect:/hello";
        } else {
            return "login";
        }
    }
}
