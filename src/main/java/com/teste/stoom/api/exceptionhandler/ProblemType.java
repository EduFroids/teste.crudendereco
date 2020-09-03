package com.teste.stoom.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	PARAMETRO_INVALIDO("/paramentro-invalido", "Parâmetro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados_invalidos", "Dados inválidos");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://teste.stoom.com.br" + path;
		this.title = title;
		
	}

}