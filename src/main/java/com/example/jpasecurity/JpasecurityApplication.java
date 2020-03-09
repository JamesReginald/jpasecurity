package com.example.jpasecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class JpasecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpasecurityApplication.class, args);
	}

}
