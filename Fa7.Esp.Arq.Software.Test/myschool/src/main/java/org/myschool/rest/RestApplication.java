package org.myschool.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestApplication extends Application
{
//	@Path("{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public String busca(@PathParam("id") long id) {
//		Carrinho carrinho = new CarrinhoDAO().busca(id);
//		return carrinho.toXML();
//	}

}