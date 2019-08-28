package com.vivs.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivs.cursomc.domain.Pagamento;

//ID tipo do atributo identificador do objeto
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
