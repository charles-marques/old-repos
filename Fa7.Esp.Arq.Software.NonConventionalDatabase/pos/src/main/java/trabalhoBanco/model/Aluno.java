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
public class Aluno extends MongoAppEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2326363285976758735L;
	// ID_ALUNO INTEGER
	private Integer id;
	// NOME VARCHAR
	private String nome;
	// FONE VARCHAR
	private String fone;

	public Aluno() {
	}
	
	public Aluno(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	@Override
	protected void configDocument() {
		if (id != null) append("id", this.id);
		if (nome != null) append("nome", this.nome);
		if (fone != null) append("fone", this.fone);
	}
	
	@Override
	protected void configDocumentUpdate() {
		if (nome != null) appendUpdate("nome", this.nome);
		if (fone != null) appendUpdate("fone", this.fone);
	}

	@Override
	protected void configProperties(Entry<String, Object> entry) {
		if (entry.getKey().equals("id")) {
			this.id = (Integer) entry.getValue();
		}
		if (entry.getKey().equals("nome")) {
			this.nome = (String) entry.getValue();
		}
		if (entry.getKey().equals("fone")) {
			this.fone = (String) entry.getValue();
		}
	}

}
