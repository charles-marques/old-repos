package br.edu.fa7.nosqlapp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import br.edu.fa7.nosqlapp.modelo.Aula;
import br.edu.fa7.nosqlapp.resource.AulaResource;

import com.thoughtworks.xstream.XStream;
/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 *
 */
public class AulaSteps extends Steps {

	AulaResource aulaResource;
	Aula aula;

	@Given("uma aulaResource é criada")
	@Aliases(values = { "uma aula é instanciada" })
	public void aAulaECriada() {
		aulaResource = new AulaResource();
	}

	@When("eu busco aula $num")
	public void euBuscaAula(int num) {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://localhost:8080/nosqlapp");
//		http://localhost:8080/nosqlapp/rest/aulas/1

//		System.out.println("uri: " + target.getUri());
		
		String uri = "/rest/aulas/" + num + "";
		String conteudo = target.path(uri).request().get(String.class);
		
//		System.out.println("ur: " + conteudo);
		
		aula = (Aula) new XStream().fromXML(conteudo);
//		aula = (Aula) new XStream().fromXML(aulaResource.busca(i));
	}

	@Then("o resultado deve ter id $id")
	public void oResultadoDeveSer(int id) {
		if (aula.getId().intValue() == id) {
			System.out.println("É igual!!!");
		} else {
			System.out.println("Não é igual!!!");
		}
	}
	
}