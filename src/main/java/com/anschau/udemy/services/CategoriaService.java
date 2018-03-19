package com.anschau.udemy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anschau.udemy.domain.Categoria;
import com.anschau.udemy.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElse(null);
		
	}
}
