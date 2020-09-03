package com.teste.stoom.api.domain.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 818287073903397919L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}