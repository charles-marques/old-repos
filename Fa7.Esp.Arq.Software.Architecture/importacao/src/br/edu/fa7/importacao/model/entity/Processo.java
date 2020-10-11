package br.edu.fa7.importacao.model.entity;

import java.util.Date;
import java.util.List;

public abstract class Processo {
	
	private Integer id;
	private List<EtapaProcesso> etapaProcessoList;
	private String descricao;
	private Date dataRegistro;
	private Boolean ativo;
	
}
