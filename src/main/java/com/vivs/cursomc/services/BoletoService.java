package com.vivs.cursomc.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.vivs.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instanteDoPedido) {
		pagto.setDataVencimento(instanteDoPedido.toLocalDate());
	}

}
