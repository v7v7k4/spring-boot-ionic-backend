package com.vivs.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vivs.cursomc.services.S3Service;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3service;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	//executar a instanciação no momento que a aplicação iniciar
	@Override
	public void run(String... args) throws Exception {
		s3service.upload("C:\\teste\\IMG-20161225-WA0029.jpg");
	}

}
