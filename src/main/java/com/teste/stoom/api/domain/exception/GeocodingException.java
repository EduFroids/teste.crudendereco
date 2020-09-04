package com.teste.stoom.api.domain.exception;

public class GeocodingException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -3018509836314263845L;
	
	public GeocodingException(String mensagem) {
		super(mensagem);
	}

}
