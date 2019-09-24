package com.vivs.cursomc.services.exceptions;

public class FileException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8074539710014077186L;

	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(String msg, Throwable causaExcecaoAnterior) {
		super(msg, causaExcecaoAnterior);
	}

}
