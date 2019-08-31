package com.vivs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivs.cursomc.domain.Pedido;
import com.vivs.cursomc.repositories.PedidoRepository;
import com.vivs.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
	
	//dependencia automaticamente instanciada pelo spring 
	@Autowired
	private PedidoRepository PedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = PedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
