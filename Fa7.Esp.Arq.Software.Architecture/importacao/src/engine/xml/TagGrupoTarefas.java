package engine.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("grupoTarefas")
public class TagGrupoTarefas {
	
	@XStreamAsAttribute
	private String nome;
	
	@XStreamImplicit
	private List<TagTarefa> tarefas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TagTarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<TagTarefa> tarefas) {
		this.tarefas = tarefas;
	}
}
