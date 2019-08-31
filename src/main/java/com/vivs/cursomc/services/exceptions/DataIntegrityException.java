package com.vivs.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5644119786458806855L;

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable causaExcecaoAnterior) {
		super(msg, causaExcecaoAnterior);
	}

}
