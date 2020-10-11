package br.edu.fa7.nosqlapp.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.fa7.nosqlapp.dao.DisciplinaDAO;
import br.edu.fa7.nosqlapp.modelo.Disciplina;

@Path("disciplinas")
public class TopicoResource {

	@Inject
	DisciplinaDAO dao;
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		try {
			Disciplina disciplina = dao.busca(id);
			return disciplina.toXML();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return Response.noContent().build().toString();
		}
	}
	
}
