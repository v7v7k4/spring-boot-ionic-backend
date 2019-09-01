package com.vivs.cursomc.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vivs.cursomc.domain.enums.EstadoPagamento;
@Entity
public class PagamentoComBoleto extends Pagamento {
	
	private static final long serialVersionUID = 8899730996839836308L;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataVencimento;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataPagamento;
	
	public PagamentoComBoleto() {
		
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, LocalDate dataVencimento, LocalDate dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
	
	

}
