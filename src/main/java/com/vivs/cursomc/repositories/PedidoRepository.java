package com.vivs.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vivs.cursomc.domain.Cliente;
import com.vivs.cursomc.domain.Pedido;

//ID tipo do atributo identificador do objeto
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	//buscar pedidos por cliente
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
