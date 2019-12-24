package com.example.firstspringbootdemo.cache;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ResourceBundle;


@RestController
@PropertySource("classpath:params.properties")
public class ConstKitInit implements CommandLineRunner {

	public final static String mobNo = ResourceBundle.getBundle("params").getString("mobNo");;
//	public static String mobNo ;

	@Override
	@RequestMapping("/mobile")
	public void run(String... args) throws Exception {
		System.out.println("init"+mobNo);
	}

}
