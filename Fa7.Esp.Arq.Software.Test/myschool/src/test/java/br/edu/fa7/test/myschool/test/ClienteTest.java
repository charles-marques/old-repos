package br.edu.fa7.test.myschool.test;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.fa7.myschool.modelo.Aula;

import com.thoughtworks.xstream.XStream;

@SuppressWarnings("deprecation")
public class ClienteTest {
	HttpServer server;

	@Before
	public void startaServidor() {
		try {
			this.server = Servidor.iniciarServidor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void pararServidor() {
		server.stop();
	}
	
	@Test
	public void testaQueAConexaoComOServidorFuniona() {
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080");
		
		String uri = "/aulas/1";
		String conteudo = target.path(uri).request().get(String.class);
		
		Aula aula = (Aula) new XStream().fromXML(conteudo);
		System.out.println(conteudo);
		String xml = aula.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		Aula response = target.path("/aulas").request().post(entity, Aula.class);
		System.out.println("Aula: ");
		System.out.println(response.toXML());
		
		Assert.assertEquals(201, response.toXML());
	}
}
