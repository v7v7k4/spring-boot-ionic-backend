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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vivs.cursomc.domain.Categoria;
import com.vivs.cursomc.dto.CategoriaDTO;
import com.vivs.cursomc.services.CategoriaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value="Busca por id") 
	//@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = categoriaService.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") //só autoriza a entrar no post se for perfil admin
	@ApiOperation(value="Insere categoria") 
	//@RequestMapping(method=RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO){
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build(); //created é status 201
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@ApiOperation(value="Atualiza categoria")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id){
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@ApiOperation(value="Remove categoria")
	@ApiResponses(value = {
			@ApiResponse(code=400, message="Não é possível excluir uma categoria que possui produtos."),
			@ApiResponse(code=404, message="Código inexistente")
	})
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Retorna todas as categorias")
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listaCategoria = categoriaService.findAll();
		List<CategoriaDTO> listaCategoriaDTO = listaCategoria.stream().map(dto -> new CategoriaDTO(dto)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaCategoriaDTO);
	}
	
	@ApiOperation(value="Retorna todas as categorias com paginação")
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
