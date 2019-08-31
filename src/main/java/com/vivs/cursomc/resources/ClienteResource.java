package com.vivs.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	//cliente pode serializar os endereços dele mas o endereço não pode serializar o cliente
	//@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = clienteService.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
