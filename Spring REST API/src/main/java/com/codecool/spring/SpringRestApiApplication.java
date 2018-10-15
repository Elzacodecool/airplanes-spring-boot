package com.codecool.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.codecool.spring.model.Producent;
import com.codecool.spring.repository.ProducentRepository;
import com.codecool.spring.service.ProducentService;

@SpringBootApplication
public class SpringRestApiApplication implements CommandLineRunner {
	@Autowired
	private ProducentService ps;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRestApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("ok");
		ps.createProducent("LOL", "GOL");
	}
}
