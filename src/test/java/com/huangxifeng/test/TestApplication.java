package com.huangxifeng.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TestApplication implements WebMvcConfigurer
{
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
