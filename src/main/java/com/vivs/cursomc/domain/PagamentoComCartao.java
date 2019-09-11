package com.vivs.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vivs.cursomc.domain.enums.EstadoPagamento;
@Entity
@JsonTypeName("pagamentoComCartao") //@type aparece no json como pagamentoComBoleto ou pagamentoComCartao
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 8899730996839836308L;
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
