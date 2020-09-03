package com.teste.stoom.api.domain.exception;

public class AddressNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -7042864362503553060L;
	
	public AddressNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public AddressNaoEncontradoException(Long id) {
		this(String.format("Não existe um cadastro de endereço com código %d", id));
	}

}