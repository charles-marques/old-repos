package negocio.importclient;

import engine.ContextoProcesso;
import engine.Tarefa;

public class Teste {

	public static void main(String[] args) {
		ContextoProcesso contexto = new ContextoProcesso();

		Tarefa tarefa1 = getTarefa("negocio.tarefa.ValidarCambio");
		tarefa1.executar(contexto);
		Tarefa tarefa2 = getTarefa("negocio.tarefa.ValidarEspecificacao");
		tarefa2.executar(contexto);
		Tarefa tarefa3 = getTarefa("negocio.tarefa.ValidarProduto");
		tarefa3.executar(contexto);

		contexto.getErros().stream().forEach(System.out::println);
	}

	public static Tarefa getTarefa(String classe) {
		try {
			return (Tarefa) Class.forName(classe).newInstance();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
