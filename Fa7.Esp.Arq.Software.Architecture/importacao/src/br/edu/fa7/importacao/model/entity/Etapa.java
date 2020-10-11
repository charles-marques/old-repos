package br.edu.fa7.importacao.model.entity;

import java.util.List;

public abstract class Etapa {
	
	public List<Validacao> validacaoList;
	
	public Boolean validar() {
		return Boolean.TRUE;
	}
}
