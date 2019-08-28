package com.vivs.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivs.cursomc.domain.Pedido;

//ID tipo do atributo identificador do objeto
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
