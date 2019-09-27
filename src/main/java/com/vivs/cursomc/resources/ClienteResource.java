package com.vivs.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.dto.ClienteDTO;
import com.vivs.cursomc.dto.ClienteNewDTO;
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
	
	@GetMapping(value="/email")
	public ResponseEntity<Cliente> find(@RequestParam(value="value") String email) {
		Cliente obj = clienteService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listaCliente = clienteService.findAll();
		List<ClienteDTO> listaClienteDTO = listaCliente.stream().map(dto -> new ClienteDTO(dto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaClienteDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	//localhost:8080/categorias/page?page=0&linesPerPage=5
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> listaCliente = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listaClienteDTO = listaCliente.map(dto -> new ClienteDTO(dto));
		return ResponseEntity.ok().body(listaClienteDTO);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO){
		Cliente cliente = clienteService.fromDTO(clienteNewDTO);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build(); //created é status 201
	}
	
//	@PostMapping(value="/picture")
//	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file")MultipartFile file){
		//URI uri = clienteService.uploadProfilePicture(file);
//		return ResponseEntity.created(uri).build(); //created é status 201
//	}

}
