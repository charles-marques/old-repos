package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ContextoProcesso extends HashMap<ChaveContexto, Object> {

	private static final long serialVersionUID = 1L;

	private List<String> erros = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public <T> T getObject(ChaveContexto chave) {
		return (T) get(chave);
	}
	
	public void addErro(String erro) {
		erros.add(erro);
	}
	
	public List<String> getErros() {
		return Collections.unmodifiableList(erros);
	}
	
	public boolean possuiErro() {
		return !erros.isEmpty();
	}
}
