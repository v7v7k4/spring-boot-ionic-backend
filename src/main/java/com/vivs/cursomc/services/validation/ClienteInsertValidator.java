package com.vivs.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.domain.enums.TipoCliente;
import com.vivs.cursomc.dto.ClienteNewDTO;
import com.vivs.cursomc.repositories.ClienteRepository;
import com.vivs.cursomc.resources.exception.FieldMessage;
import com.vivs.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override     
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) { 
 
        List<FieldMessage> list = new ArrayList<>(); 
        
        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
        	list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }
        
        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
        	list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }
        
        //verifica se o email do cliente ja existe
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux!=null) {
        	list.add(new FieldMessage("email", "E-mail já existente."));
        }
        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
        	context.disableDefaultConstraintViolation();
        	context.buildConstraintViolationWithTemplate(e.getMessage())
        		.addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    } 
}
