package negocio.tarefa;

import engine.TipoProcesso;

public enum ProcessoImex implements TipoProcesso {
	
	EXPORTAR_ALIMENTO_BRASIL("xmls/exportar_alimento_brasil.xml");
	
	private final String caminhoDoArquivoXML;
	
	private ProcessoImex(String caminhoDoArquivoXML) {
		this.caminhoDoArquivoXML = caminhoDoArquivoXML;
	}

	public String getCaminhoDoArquivoXML() {
		return caminhoDoArquivoXML;
	}
}