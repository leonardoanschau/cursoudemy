package com.anschau.udemy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anschau.udemy.domain.Cliente;
import com.anschau.udemy.repositories.ClienteRepository;
import com.anschau.udemy.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
