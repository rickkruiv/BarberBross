package com.barberbross.barberbross;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BarberbrossApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarberbrossApplication.class, args);
	}

}
