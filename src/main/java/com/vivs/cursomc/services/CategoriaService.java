package com.vivs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivs.cursomc.domain.Categoria;
import com.vivs.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	//dependencia automaticamente instanciada pelo spring 
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElse(null);
	}

}
