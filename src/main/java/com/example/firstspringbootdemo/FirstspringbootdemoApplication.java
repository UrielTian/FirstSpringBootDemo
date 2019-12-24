package com.example.firstspringbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//这个注解可以不用写mapping文件
@MapperScan(basePackages = {"com.example.firstspringbootdemo.bean.mapper"})
//开启定时任务
@EnableScheduling
public class FirstspringbootdemoApplication {

    //程序入口，启动SpringBoot项目
    public static void main(String[] args) {
        SpringApplication.run(FirstspringbootdemoApplication.class, args);
    }

}
