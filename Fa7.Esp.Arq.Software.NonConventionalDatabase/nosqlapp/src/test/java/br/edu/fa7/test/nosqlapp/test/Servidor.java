package br.edu.fa7.test.nosqlapp.test;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	private static HttpServer server;

	public static HttpServer iniciarServidor() throws IOException {
		ResourceConfig config = new ResourceConfig().packages("br.edu.fa7.nosqlapp.test");
		URI uri = URI.create("http://localhost:8080");
		server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.in.read();
		server.stop();
		return server;
	}
	
	public static void paraServidor() {
		server.stop();
	}
}
