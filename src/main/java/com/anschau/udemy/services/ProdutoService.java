package com.anschau.udemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.anschau.udemy.domain.Categoria;
import com.anschau.udemy.domain.Produto;
import com.anschau.udemy.repositories.CategoriaRepository;
import com.anschau.udemy.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//ta dando BO nesse metodo
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		try {
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado!id: " + id + ", Tipo: " + Produto.class.getName()));
		} catch (ObjectNotFoundException e) {
			Produto pro = obj.get();
			return pro;
		}
	}
	
	

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
}
