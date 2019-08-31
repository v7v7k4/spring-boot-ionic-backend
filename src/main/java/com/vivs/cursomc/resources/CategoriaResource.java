package com.vivs.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vivs.cursomc.domain.Categoria;
import com.vivs.cursomc.dto.CategoriaDTO;
import com.vivs.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	//@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = categoriaService.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	//@RequestMapping(method=RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build(); //created é status 201
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id){
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listaCategoria = categoriaService.findAll();
		List<CategoriaDTO> listaCategoriaDTO = listaCategoria.stream().map(dto -> new CategoriaDTO(dto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaCategoriaDTO);
	}
	
	//localhost:8080/categorias/page?page=0&linesPerPage=5
	@GetMapping(value="/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Categoria> listaCategoria = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaCategoriaDTO = listaCategoria.map(dto -> new CategoriaDTO(dto));
		return ResponseEntity.ok().body(listaCategoriaDTO);
	}
}
