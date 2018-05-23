package com.ralap.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BlogWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogWebApplication.class, args);
	}
}
