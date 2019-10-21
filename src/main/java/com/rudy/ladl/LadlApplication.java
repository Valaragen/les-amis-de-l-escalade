package com.rudy.ladl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LadlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LadlApplication.class, args);
	}

}
