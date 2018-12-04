package com.example.sweproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.example.sweproject.dao")
public class SweprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweprojectApplication.class, args);
	}
}
