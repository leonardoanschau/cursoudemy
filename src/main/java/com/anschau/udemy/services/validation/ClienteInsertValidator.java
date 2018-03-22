package com.anschau.udemy.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.anschau.udemy.domain.Cliente;
import com.anschau.udemy.domain.enums.TipoCliente;
import com.anschau.udemy.dto.ClienteNewDTO;
import com.anschau.udemy.repositories.ClienteRepository;
import com.anschau.udemy.resources.exceptions.FieldMessage;
import com.anschau.udemy.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override     
	public void initialize(ClienteInsert ann) {     }

	@Override     
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
	
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("Email", "Email já existente!"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
