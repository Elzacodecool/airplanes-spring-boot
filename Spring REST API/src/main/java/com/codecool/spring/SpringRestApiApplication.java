package com.codecool.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codecool.spring.service.ProducerService;

@SpringBootApplication
public class SpringRestApiApplication implements CommandLineRunner {
	@Autowired
	private ProducerService ps;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("ok");
		ps.createProducent("LOL", "GOL");
	}
}
