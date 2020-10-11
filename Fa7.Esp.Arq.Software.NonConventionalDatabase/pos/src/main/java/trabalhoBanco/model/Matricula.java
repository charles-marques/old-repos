package trabalhoBanco.model;

import java.util.Map.Entry;

import org.bson.Document;

import trabalhoBanco.mongo.persistence.MongoAppEntity;

public class Matricula extends MongoAppEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2988240937678268364L;
	// ID_MATRICULA INTEGER
	private Integer id;
	// ID_ALUNO INTEGER
	private Aluno aluno;
	// ID_AULA INTEGER
	private Aula aula;

	public Matricula() {
		
	}
	
	public Matricula(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	@Override
	protected void configDocument() {
		if (id != null) append("id", id);
		if (aluno != null) append("aluno", aluno.toDocument());
		if (aula != null) append("aula", aula.toDocument());
	}
	
	@Override
	protected void configDocumentUpdate() {
		if (aluno != null) appendUpdate("aluno", aluno.toDocument());
		if (aula != null) appendUpdate("aula", aula.toDocument());
	}

	@Override
	protected void configProperties(Entry<String, Object> entry) {
		if (entry.getKey().equals("id")) {
		    this.id = (Integer) entry.getValue();
		 }
		if (entry.getKey().equals("aluno")) {
		    try {
				this.aluno = (Aluno) Aluno.class.newInstance().toObject((Document) entry.getValue());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		 }
		if (entry.getKey().equals("aula")) {
		    try {
				this.aula = (Aula) Aula.class.newInstance().toObject((Document) entry.getValue());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		 }

	}

}
