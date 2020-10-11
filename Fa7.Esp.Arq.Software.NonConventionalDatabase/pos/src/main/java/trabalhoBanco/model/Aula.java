package trabalhoBanco.model;

import java.util.Map.Entry;

import org.bson.Document;

import trabalhoBanco.mongo.persistence.MongoAppEntity;


/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 *
 */
public class Aula extends MongoAppEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3859908540833499410L;
	// ID_AULA INTEGER
	private Integer id;
	// ID_PROFESSOR INTEGER
	private Professor professor;
	// ID_CURSO INTEGER
	private Curso curso;
	// DESCRICAO VARCHAR
	private String descricao;

	public Aula() {
	}
	
	public Aula(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	protected void configDocument() {
		if (id != null) append("id", this.id);
		if (curso != null) append("curso", curso.toDocument());
		if (descricao != null) append("descricao", descricao);
		if (professor != null) append("professor", this.professor.toDocument());
	}
	
	@Override
	protected void configDocumentUpdate() {
		if (curso != null) appendUpdate("curso", curso.toDocument());
		if (descricao != null) appendUpdate("descricao", descricao);
		if (professor != null) appendUpdate("professor", this.professor.toDocument());
	}

	@Override
	protected void configProperties(Entry<String, Object> entry) {
		if (entry.getKey().equals("id")) {
			this.id = (Integer) entry.getValue();
		}
		if (entry.getKey().equals("professor")) {
			try {
				this.professor = (Professor) Professor.class.newInstance().toObject((Document) entry.getValue());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if (entry.getKey().equals("curso")) {
			try {
				this.curso = (Curso) Curso.class.newInstance().toObject((Document) entry.getValue());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

}
