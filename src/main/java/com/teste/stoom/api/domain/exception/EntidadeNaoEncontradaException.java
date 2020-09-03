package com.teste.stoom.api.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = -6774378230504042314L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
