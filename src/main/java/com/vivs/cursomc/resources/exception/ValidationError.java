package com.vivs.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1057629064675461456L;
	private List<FieldMessage> listaFieldMessage = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getListaFieldMessage() {
		return listaFieldMessage;
	}

	public void addError(String fieldName, String message) {
		listaFieldMessage.add(new FieldMessage(fieldName, message));
	}

	
	
	

}
