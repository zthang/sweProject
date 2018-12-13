package com.example.sweproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.sweproject.dao")
@ComponentScan("com.example")
public class SweprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweprojectApplication.class, args);
	}
}
