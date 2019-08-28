package com.vivs.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vivs.cursomc.domain.ItemPedido;

//ID tipo do atributo identificador do objeto
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
