package negocio.entidade;

import java.util.Date;

public class Produto {

	private Date validade;
	private String descricao;

	public Produto() {
	}

	public Produto(Date validade, String descricao) {
		super();
		this.validade = validade;
		this.descricao = descricao;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
