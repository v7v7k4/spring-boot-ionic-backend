package com.vivs.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vivs.cursomc.domain.Estado;

//ID tipo do atributo identificador do objeto
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
	@Transactional(readOnly=true) //retorna todos os estados ordenados por nome
	public List<Estado> findAllByOrderByNome();

}
