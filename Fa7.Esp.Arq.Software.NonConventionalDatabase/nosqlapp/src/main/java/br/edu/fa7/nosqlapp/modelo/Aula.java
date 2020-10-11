package br.edu.fa7.nosqlapp.modelo;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 *
 */
@Entity
@Table(name = "aula", schema = "db_nosqlapp")
public class Aula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String assunto;
	
	@Enumerated(EnumType.STRING)
	private Disciplina disciplina;
	
	@ManyToMany(
	   fetch = FetchType.LAZY,	
	   targetEntity=br.edu.fa7.nosqlapp.modelo.Topico.class,
	   cascade={CascadeType.PERSIST, CascadeType.MERGE}
	)
	@JoinTable(
	   name="aula_topico",
	   joinColumns=@JoinColumn(name="id_aula"),
	   inverseJoinColumns=@JoinColumn(name="id_topico")	   
	)
	@OrderBy("titulo")
	private Set<Topico> topicos;
	
	private String objetivo;

	public Aula() {
	}

	public Aula(Long id, String assunto) {
		this();
		this.id = id;
		this.assunto = assunto;
	}

	public Aula(Long id, String assunto, Disciplina disciplina) {
		this(id, assunto);
		this.disciplina = disciplina;
	}

	public Aula(Long id, String assunto, Disciplina disciplina, String objetivo) {
		this(id, assunto, disciplina);
		this.objetivo = objetivo;
	}

	public Aula(Long id, String assunto, Disciplina disciplina,
			String objetivo, Set<Topico> topicos) {
		this(id, assunto, disciplina, objetivo);
		this.topicos = topicos;
	}

	/* Getters and Setters Methods */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Set<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(Set<Topico> topicos) {
		this.topicos = topicos;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/* Others Methods */
	public Aula adicionaTopico(Topico topico) {
		topicos.add(topico);
		return this;
	}

	public Aula para(String assunto, Disciplina disciplina) {
		this.assunto = assunto;
		this.disciplina = disciplina;
		return this;
	}
	
	public void trocaTitulo(Topico topico) {
		for (Iterator<Topico> iterator = topicos.iterator(); iterator.hasNext();) {
			Topico t = (Topico) iterator.next();
			if(t.getId() == topico.getId()) {
				t.setTitulo(topico.getTitulo());
				return;
			}
		}
	}

	public void removeTopico(Long topicoId) {
		for (Iterator<Topico> iterator = topicos.iterator(); iterator.hasNext();) {
			Topico item = (Topico) iterator.next();
			if (item.getId().equals(topicoId)) {
				iterator.remove();
			}
		}
	}

	public void troca(Topico topico) {
		removeTopico(topico.getId());
		adicionaTopico(topico);
	}
	
	public String toXML() {
		return new XStream().toXML(this);
	}

	public String toJSON() {
		return new Gson().toJson(this);
//		return this.toString();
	}
}