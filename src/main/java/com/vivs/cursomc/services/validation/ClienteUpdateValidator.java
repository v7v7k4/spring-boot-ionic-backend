package com.vivs.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.dto.ClienteDTO;
import com.vivs.cursomc.repositories.ClienteRepository;
import com.vivs.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	//Para pegar o id do cliente que está sendo passado na url e não no corpo - clientes/1
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override     
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) { 
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
        List<FieldMessage> list = new ArrayList<>(); 
        
        //verifica se o email do cliente ja existe
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux!=null && !aux.getId().equals(uriId)) {
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
