package br.edu.unifor.semanticweb.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class TesteController {
	
	protected static Model model = ModelFactory.createDefaultModel();
	protected static Model model2 = ModelFactory.createDefaultModel();
	
	public static void main(String[] args) {
		URL url;
		URI uri;
		try {
//			url = new URL("http://api.tcm.ce.gov.br/sim/1_0/dados_orcamentos.rdf?codigo_municipio=002&exercicio_orcamento=0");
//			uri = url.toURI();
//			
//			model = FileManager.get().loadModel(uri.toString());
			
			url = new URL("http://api.tcm.ce.gov.br/sim/1_0/municipios.rdf?codigo_municipio=003");
			uri = url.toURI();
			model2 = FileManager.get().loadModel(uri.toString());
			
//			model.write(System.out);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("Listando Propriedades: ");
//		model.listObjects().toList().forEach(teste -> System.out.println(teste));
		
		/* Apache SparQl ?nome_municipio ?valor_total_fixado_orcamento*/
		String queryString = 
				"PREFIX municipio: <http://api.tcm.ce.gov.br/sim/1_0/municipios.rdf#> " +
				"PREFIX orcamento: <http://api.tcm.ce.gov.br/sim/1_0/dados_orcamentos.rdf?codigo_municipio=002&exercicio_orcamento=0>" + 
				"SELECT ?cidade ?dados ?nome ?valor" +
				" WHERE {" +
				"         ?cidade municipio:codigo_municipio ?nome . " +
				"         ?dados orcamento:valor_total_fixado_orcamento ?valor ." +
				"} ";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model2);
		try {
			ResultSet resultSet = qexec.execSelect();
			List<String> list = resultSet.getResultVars();
			list.forEach(v -> System.out.println(v));
			while (resultSet.hasNext() ) {
				QuerySolution object = resultSet.next();
				System.out.println(object.varNames());
			}
		} finally {
			try {
				qexec.close();
			}catch (Exception e) {
			}
		}
	}

}
