package br.edu.fa7.myschool.resource;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.fa7.myschool.dao.AulaDAO;
import br.edu.fa7.myschool.modelo.Aula;
import br.edu.fa7.myschool.modelo.Topico;

import com.thoughtworks.xstream.XStream;

@Path("aulas")
public class AulaResource {

	@Inject
	AulaDAO dao;
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		try {
			Aula aula = dao.busca(id);
			return aula.toXML();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "<content>AULA NOT FOUND</content>";
		}
	}
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adicionarXML(String conteudo) {
		Aula aula = (Aula) new XStream().fromXML(conteudo);
		
		dao.adiciona(aula);
		
		URI uri = URI.create("/aulas/" + aula.getId());
		return Response.created(uri).build();
	}
	
	@Path("{id}/topicos/{topicoId}")
	@DELETE
	public Response removerProduto(@PathParam("id") long id, @PathParam("topicoId") long topicoId) {
		Aula aula = dao.busca(id);
		aula.removeTopico(topicoId);
		return Response.ok().build();
	}
	
	@Path("{id}/topicos/{topicoId}")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response atualizarProduto(@PathParam("id") long id, @PathParam("topicoId") long topicoId, String conteudo) {
		Aula aula = dao.busca(id);
		Topico topico = (Topico) new XStream().fromXML(conteudo);
		aula.troca(topico);
		return Response.ok().build();
	}
	
	@Path("{id}/topicos/{topicoId}/titulo")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response atualizarProdutoQuantidade(@PathParam("id") long id, @PathParam("topicoId") long topicoId, String conteudo) {
		Aula aula = dao.busca(id);
		Topico topico = (Topico) new XStream().fromXML(conteudo);
		aula.trocaTitulo(topico);
		return Response.ok().build();
	}
	
}