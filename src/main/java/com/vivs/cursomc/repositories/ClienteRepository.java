package com.vivs.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vivs.cursomc.domain.Cliente;

//ID tipo do atributo identificador do objeto
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email); //findByEmail - email é o campo email da classe Cliente

}
