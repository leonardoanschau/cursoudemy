package com.anschau.udemy.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anschau.udemy.domain.Categoria;
import com.anschau.udemy.dto.CategoriaDTO;
import com.anschau.udemy.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		 Categoria obj = categoriaService.find(id);
		 return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
		 categoriaService.delete(id);
		 return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		 List<Categoria> list = categoriaService.findAll();
		 List<CategoriaDTO> listaDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		 return ResponseEntity.ok().body(listaDto);
	}
	
}
