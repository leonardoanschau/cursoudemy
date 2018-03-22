package com.anschau.udemy.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anschau.udemy.domain.Produto;
import com.anschau.udemy.dto.ProdutoDTO;
import com.anschau.udemy.resources.utils.URl;
import com.anschau.udemy.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		 Produto obj = produtoService.find(id);
		 return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="")String nome,
			@RequestParam(value="categorias", defaultValue="")String categorias,
			@RequestParam(value="page", defaultValue="0")Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		 String nomeDecode = URl.decodeParams(nome);
		 List<Integer> ids = URl.decodeIntList(categorias);
		 Page<Produto> list = produtoService.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		 Page<ProdutoDTO> listaDto = list.map(obj -> new ProdutoDTO(obj));
		 return ResponseEntity.ok().body(listaDto);
	}
}
