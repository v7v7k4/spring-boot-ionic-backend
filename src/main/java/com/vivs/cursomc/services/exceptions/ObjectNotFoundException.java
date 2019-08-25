package com.vivs.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -756574575393645080L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable causaExcecaoAnterior) {
		super(msg, causaExcecaoAnterior);
	}

}
