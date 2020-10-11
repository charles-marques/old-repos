package engine;

import java.util.List;

import engine.xml.LeitorProcessoXML;
import engine.xml.TagProcesso;
import engine.xml.TagTarefa;

public class Processo {

	private static final String CONTEXTO_NULO = "Contexto nulo.";
	private static final String TIPO_PROCESSO_NULO = "Tipo processo nulo.";

	private final TipoProcesso tipoProcesso;
	private final ContextoProcesso contexto;

	public Processo(TipoProcesso tipoProcesso) {
		this(tipoProcesso, new ContextoProcesso());
	}

	public Processo(TipoProcesso tipoProcesso, ContextoProcesso contexto) {
		this.tipoProcesso = tipoProcesso;
		this.contexto = contexto;

		if (this.tipoProcesso == null) {
			throw new IllegalArgumentException(TIPO_PROCESSO_NULO);
		}

		if (this.contexto == null) {
			throw new IllegalArgumentException(CONTEXTO_NULO);
		}
	}

	/**
	 * Esse método é responsável por executar as tarefas declaradas em uma tag
	 * <grupoTarefas>
	 */
	public void executarGrupoTarefas(String grupoTarefasNome) {
		TagProcesso processo = LeitorProcessoXML.ler(tipoProcesso.getCaminhoDoArquivoXML());
		List<TagTarefa> tarefas = processo.getGrupoTarefasPorNome(grupoTarefasNome).getTarefas();

		if (tarefas != null) {
			tarefas.stream().forEach(t -> novaInstancia(t.getClasse()).executar(contexto));
		}
	}

	private Tarefa novaInstancia(String classe) {
		try {
			return (Tarefa) Class.forName(classe).newInstance();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public TipoProcesso getTipoProcesso() {
		return tipoProcesso;
	}

	public ContextoProcesso getContexto() {
		return contexto;
	}

	public Processo adicionar(ChaveContexto key, Object value) {
		contexto.put(key, value);
		return this;
	}

	
	
	public List<String> getErros() {
		return contexto.getErros();
	}

	public boolean possuiErro() {
		return contexto.possuiErro();
	}
}
