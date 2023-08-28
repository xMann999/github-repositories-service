package com.sergiuszg.github.repositories.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubRepositoriesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubRepositoriesServiceApplication.class, args);
	}

}
