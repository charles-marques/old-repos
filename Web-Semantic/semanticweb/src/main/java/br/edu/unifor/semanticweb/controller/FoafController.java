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
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class FoafController {
	public static Model model = ModelFactory.createDefaultModel();

	public static void main(String[] args) {
		URL url;
		URI uri;
		try {
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
		String queryString = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
				"SELECT ?url\n" + 
				"FROM    <bloggers.rdf>\n" + 
				"WHERE  {\n" + 
				"    ?contributor foaf:name \"Jon Foobar\" .\n" + 
				"    ?contributor foaf:weblog ?url .\n" + 
				"}";
//		"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
//		"PREFIX rss:  <http://purl.org/rss/1.0/>\n" + 
//		"PREFIX dc:   <http://purl.org/dc/elements/1.1/>\n" + 
//		" \n" + 
//		"SELECT ?title ?known_name ?link\n" + 
//		" \n" + 
//		"FROM <http://planetrdf.com/index.rdf>\n" + 
//		"FROM NAMED <phil-foaf.rdf>\n" + 
//		" \n" + 
//		"WHERE {\n" + 
//		"        GRAPH <phil-foaf.rdf> {\n" + 
//		"          ?me foaf:name \"Phil McCarthy\" .\n" + 
//		"          ?me foaf:knows ?known_person .\n" + 
//		"          ?known_person foaf:name ?known_name .\n" + 
//		"        } .\n" + 
//		"            \n" + 
//		"        ?item dc:creator ?known_name . \n" + 
//		"        ?item rss:title ?title . \n" + 
//		"        ?item rss:link ?link . \n" + 
//		"        ?item dc:date ?date. \n" + 
//		"      }\n" + 
//		" \n" + 
//		"ORDER BY DESC[?date] LIMIT 10";
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet resultSet = qexec.execSelect();
			List<String> list = resultSet.getResultVars();
			list.forEach(v -> System.out.println(v));
		} finally {
			qexec.close();
		}
	}
}
