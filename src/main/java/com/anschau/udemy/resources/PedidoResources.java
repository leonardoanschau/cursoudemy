package com.anschau.udemy.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anschau.udemy.domain.Pedido;
import com.anschau.udemy.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResources {

	@Autowired
	PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		 Pedido obj = pedidoService.buscar(id);
		 return ResponseEntity.ok().body(obj);
	}
}
