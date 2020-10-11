package trabalhoBanco.model;

import java.util.Map.Entry;

import trabalhoBanco.mongo.persistence.MongoAppEntity;

public class Professor extends MongoAppEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3974215483208071184L;

	// ID_PROFESSOR INTEGER
	private Integer id;
	// NOME VARCHAR
	private String nome;
	// EMAIL
	private String email;

	public Professor() {
		
	}
	
	public Professor(Integer id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @see trabalhoBanco.mongo.persistence.MongoAppEntity#configDocument()
	 */
	@Override
	protected void configDocument() {
		if (id != null) append("id", id);
		if (nome != null) append("nome", nome);
		if (email != null) append("email", email);
	}
	
	@Override
	protected void configDocumentUpdate() {
		if (nome != null) appendUpdate("nome", nome);
		if (email != null) appendUpdate("email", email);
	}
	
	/**
	 * @see trabalhoBanco.mongo.persistence.MongoAppEntity#configProperties(java.util.Map.Entry)
	 */
	@Override
	protected void configProperties(Entry<String, Object> entry) {
		if (entry.getKey().equals("id")) {
			this.id = (Integer) entry.getValue();
		}
		if (entry.getKey().equals("nome")) {
			this.nome = (String) entry.getValue();
		}
		if (entry.getKey().equals("email")) {
			this.email = (String) entry.getValue();
		}

	}

}
