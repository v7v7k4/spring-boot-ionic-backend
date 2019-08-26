package com.vivs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.repositories.ClienteRepository;
import com.vivs.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	//dependencia automaticamente instanciada pelo spring 
	@Autowired
	private ClienteRepository ClienteRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = ClienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
