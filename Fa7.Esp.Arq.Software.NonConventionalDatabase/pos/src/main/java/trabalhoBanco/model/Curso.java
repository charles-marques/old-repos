package trabalhoBanco.model;

import java.util.Map.Entry;

import trabalhoBanco.mongo.persistence.MongoAppEntity;

/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 *
 */
public class Curso extends MongoAppEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6911663032722357096L;

	// ID_CURSO INTEGER
	private Integer id;
	// DESCRICAO VARCHAR
	private String descricao;
	// PRECO INTEGER
	private Integer preco;

	public Curso() {
		
	}
	
	public Curso(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getPreco() {
		return preco;
	}

	public void setPreco(Integer preco) {
		this.preco = preco;
	}

	@Override
	protected void configDocument() {
		if (id != null) append("id", id);
		if (descricao != null) append("descricao", descricao);
		if (preco != null) append("preco", preco);
	}
	
	@Override
	protected void configDocumentUpdate() {
		if (id != null) appendUpdate("id", id);
		if (descricao != null) appendUpdate("descricao", descricao);
		if (preco != null) appendUpdate("preco", preco);
	}

	@Override
	protected void configProperties(Entry<String, Object> entry) {
		if (entry.getKey().equals("id")) {
		    this.id = (Integer) entry.getValue();
		 }
		if (entry.getKey().equals("descricao")) {
		    this.descricao = (String) entry.getValue();
		 }
		if (entry.getKey().equals("preco")) {
		    this.preco = (Integer) entry.getValue();
		 }

	}

}
