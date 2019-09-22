package com.vivs.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651793045416060059L;

	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable causaExcecaoAnterior) {
		super(msg, causaExcecaoAnterior);
	}

}
