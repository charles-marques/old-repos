package engine.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("processo")
public class TagProcesso {
	@XStreamImplicit
	private List<TagGrupoTarefas> gruposTarefas;

	public List<TagGrupoTarefas> getGruposTarefas() {
		return gruposTarefas;
	}

	public void setGruposTarefas(List<TagGrupoTarefas> gruposTarefas) {
		this.gruposTarefas = gruposTarefas;
	}

	public TagGrupoTarefas getGrupoTarefasPorNome(String nome) {
		if (nome == null) {
			return null;
		}
		return gruposTarefas.stream().filter((e) -> e.getNome().equals(nome)).findFirst().get();
	}
}
