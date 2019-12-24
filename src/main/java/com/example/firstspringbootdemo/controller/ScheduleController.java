package com.example.firstspringbootdemo.controller;

import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ScheduleController {

//    @Scheduled(cron = "2,12,22,32,42,52 * * 21 8 ?")
    private void println(){
        System.out.println(new Date());
    }
}
