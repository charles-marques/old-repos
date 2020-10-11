package negocio.tarefa;

import negocio.entidade.Cambio;
import engine.ContextoProcesso;
import engine.Tarefa;

public class ValidarCambio implements Tarefa {

	private static final String CAMBIO_ALTO = "Câmbio Alto!";

	@Override
	public void executar(ContextoProcesso contexto) {
		Cambio cambio = contexto.getObject(ContextoImex.CAMBIO);

		if (cambio.getTaxa() < 1.0) {
			contexto.addErro(CAMBIO_ALTO);
		}
	}

}
