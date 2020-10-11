package negocio.tarefa;

import java.util.Calendar;

import engine.ContextoProcesso;
import engine.Tarefa;
import negocio.entidade.Produto;

public class ValidarEspecificacao implements Tarefa {
	
	private static final String ESPECIFICACAO_INVALIDO = "Especificação inválida!";
	private static final String VALIDADE_NULO = "Especificação nula!";

	@Override
	public void executar(ContextoProcesso contexto) {
		Produto produto = contexto.getObject(ContextoImex.PRODUTO);

		if (produto.getValidade() == null) {
			contexto.addErro(VALIDADE_NULO);
		} else if (produto.getValidade().before(Calendar.getInstance().getTime())) {
			contexto.addErro(ESPECIFICACAO_INVALIDO);
		}
	}
}
