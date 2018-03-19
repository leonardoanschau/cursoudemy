package com.anschau.udemy;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anschau.udemy.domain.Categoria;
import com.anschau.udemy.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoUdemyApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository cr;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoUdemyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		cr.saveAll(Arrays.asList(cat1, cat2));
		
	}
}
