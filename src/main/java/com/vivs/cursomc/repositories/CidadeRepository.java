package com.vivs.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivs.cursomc.domain.Cidade;

//ID tipo do atributo identificador do objeto
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
