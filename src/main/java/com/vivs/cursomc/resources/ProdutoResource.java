package com.vivs.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivs.cursomc.domain.Produto;
import com.vivs.cursomc.dto.ProdutoDTO;
import com.vivs.cursomc.resources.utils.URL;
import com.vivs.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}
	
	//http://localhost:8080/produtos/?nome=or&categorias=1,4
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		//para tirar os %20 de espaço da url
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listaProdutoDTO = list.map(dto -> new ProdutoDTO(dto));
		return ResponseEntity.ok().body(listaProdutoDTO);
	}
}
