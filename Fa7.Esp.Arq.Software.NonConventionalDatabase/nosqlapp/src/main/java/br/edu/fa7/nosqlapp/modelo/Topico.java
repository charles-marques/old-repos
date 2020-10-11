package br.edu.fa7.nosqlapp.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * 
 * @author charles.marques
 *
 */
@Entity
@Table(name = "topico", schema = "db_nosqlapp")
public class Topico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	
	@ManyToMany(
		cascade = {CascadeType.PERSIST, CascadeType.MERGE},
		mappedBy = "topicos",
		fetch = FetchType.LAZY,
		targetEntity = Aula.class
	)
	private Set<Aula> aulas;

	/* Builders Methods */
	public Topico() {
	}
	
	public Topico(Long id) {
		this.id = id;
	}
	
	public Topico(Long id, String titulo) {
		this(id);
		this.titulo = titulo;
	}
	
	public Topico(Long id, String titulo, String descricao) {
		this(id, titulo);
		this.descricao = descricao;
	}
	
	/* Getters and Setters Methods */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}