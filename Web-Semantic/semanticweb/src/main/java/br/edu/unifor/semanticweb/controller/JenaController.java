package br.edu.unifor.semanticweb.controller;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
/**
 * @author charles.marques
 * @version 1.0
 */
public class JenaController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4817208252282111578L;
	public static Model model = ModelFactory.createDefaultModel();
	
	public static void main(String[] args) {
		URL url;
		URI uri;
		try {
			// url = new URL("http://api.tcm.ce.gov.br/sim/1_0/municipios.rdf?nome_municipio=Fort");
			// url = new URL("http://api.tcm.ce.gov.br/sim/1_0/dados_orcamentos.rdf?codigo_municipio=002&exercicio_orcamento=100");
			url = new URL("http://api.tcm.ce.gov.br/sim/1_0/dados_orcamentos.rdf?codigo_municipio=002&exercicio_orcamento=0");
			uri = url.toURI();
			
			model = FileManager.get().loadModel(uri.toString());
			
			model.write(System.out);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("Listando Propriedades: ");
		model.listObjects().toList().forEach(teste -> System.out.println(teste));
		/* Apache SparQl */
		String queryString = 
				"PREFIX orcamento: <http://api.tcm.ce.gov.br/api/1_0/dados_orcamentos.ns1#> " +
				"PREFIX cidade: <http://api.tcm.ce.gov.br/sim/1_0/municipios.ns1#> " +
				"SELECT orcamento " +
				" WHERE { " +
				"    ?cidade  cidade:codigo_municipio ?codigo_municipio ?orcamento " +
//				"}";
//				"        {" +
//				"         ?municipio cidade:nome_municipio ." +
//				"         ?municipio orcamento:exercicio_orcamento ." +
//				"         ?municipio orcamento:valor_total_fixado_orcamento . " +
//				"         ?municipio orcamento:valor_total_supl_orcamento . } " +
//				"       ?cidade orcamento:valor_total_fixado_orcamento orcamento:valor_total_supl_orcamento " +
		"}";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet resultSet = qexec.execSelect();
			List<String> list = resultSet.getResultVars();
			list.forEach(v -> System.out.println(v));
		} finally {
			try {
				qexec.close();
			}catch (Exception e) {
			}
		}
	}
	
//	public static void removeLabel(String language, String value) {
//		NodeIterator nodeIterator = model.listObjects();
//		RDFNode foundToDelete = null;
//		while (nodeIterator.hasNext()) {
//			RDFNode rdfNode = (RDFNode) nodeIterator.next();
//			boolean langsAreIdentical = rdfNode.asLiteral().getLanguage().equals(language);
//			boolean valuesAreIdentical = rdfNode.asLiteral().getLexicalForm().equals(value);
//			if (langsAreIdentical && valuesAreIdentical) {
//				foundToDelete = rdfNode;
//				break;
//			}
//		}
//		model.remove
//	}
}
