package com.rudy.ladl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class LadlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LadlApplication.class, args);
	}

}
