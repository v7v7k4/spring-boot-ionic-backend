package com.vivs.cursomc.services;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vivs.cursomc.domain.Categoria;
import com.vivs.cursomc.dto.CategoriaDTO;
import com.vivs.cursomc.repositories.CategoriaRepository;
import com.vivs.cursomc.services.exceptions.DataIntegrityException;
import com.vivs.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	

	@Autowired
    private MessageSource messageSource;
	
	//dependencia automaticamente instanciada pelo spring 
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
//		return obj.orElseThrow(() -> new ObjectNotFoundException(
//				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				messageSource.getMessage("objeto.naoencontrado", null, Locale.ENGLISH)));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		Categoria newObj = find(categoria.getId());
		updateData(newObj, categoria);
		return categoriaRepository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
		
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
