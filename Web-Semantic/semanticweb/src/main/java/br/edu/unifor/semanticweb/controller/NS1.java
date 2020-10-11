package br.edu.unifor.semanticweb.controller;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class NS1 {
	public static final String uri = "http://api.tcm.ce.gov.br/api/1_0/municipios.ns#";

	public static String getURI() {
		return uri;
	}

	private static final Model m = ModelFactory.createDefaultModel();
	public static final Property CODIGO_MUNICIPIO = m.createProperty(uri, "codigo_municipio");
	public static final Property NOME_MUNICIPIO = m.createProperty(uri, "nome_municipio");
	public static final Property GEOIBGEID = m.createProperty(uri, "geoibgeid");
	public static final Property DESCRIPTION = m.createProperty(uri, "description");
}
