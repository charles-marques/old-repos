package negocio.tarefa;

import engine.ContextoProcesso;
import engine.Tarefa;
import negocio.entidade.Produto;

public class ValidarProduto implements Tarefa {

	private static final String PRODUTO_INDEQUADO = "Produto inadequado!";
	private static final String PRODUTO_INVALIDO = "Produto inválido!";

	public void executar(ContextoProcesso contexto) {
		Produto produto = contexto.getObject(ContextoImex.PRODUTO);

		if (produto.getDescricao() == null) {
			contexto.addErro(PRODUTO_INDEQUADO);
		} else if (!produto.getDescricao().trim().contains("tipo")) {
			contexto.addErro(PRODUTO_INVALIDO);
		}
	}
}
