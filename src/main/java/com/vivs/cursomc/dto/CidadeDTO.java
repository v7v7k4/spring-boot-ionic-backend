package com.vivs.cursomc.dto;

import java.io.Serializable;

import com.vivs.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443008728139573554L;
	private Integer id;
	private String nome;
	
	public CidadeDTO() {
	}

	public CidadeDTO(Cidade obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
