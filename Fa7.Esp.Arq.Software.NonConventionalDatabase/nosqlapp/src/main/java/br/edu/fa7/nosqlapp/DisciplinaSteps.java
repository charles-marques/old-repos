package br.edu.fa7.nosqlapp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import br.edu.fa7.nosqlapp.modelo.Disciplina;
import br.edu.fa7.nosqlapp.modelo.Topico;
import br.edu.fa7.nosqlapp.resource.TopicoResource;

import com.thoughtworks.xstream.XStream;
/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 *
 */
public class DisciplinaSteps extends Steps {

	private TopicoResource topicoResource;
	private Topico topico;
	private Disciplina disciplina;
	
	@Given("uma disciplinaResource é criada")
	@Aliases(values = { "uma disciplinaResource é instanciada" })
	public void aAulaECriada() {
		topicoResource = new TopicoResource();
	}
	
	@When("eu busco disciplina $num")
	public void euBuscaAula(int num) {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://localhost:8080/nosqlapp");
		
		String uri = "/rest/disciplinas/" + num + "";
		String conteudo = target.path(uri).request().get(String.class);
		
		disciplina = (Disciplina) new XStream().fromXML(conteudo);
	}

	public TopicoResource getTopicoResource() {
		return topicoResource;
	}

	public void setTopicoResource(TopicoResource topicoResource) {
		this.topicoResource = topicoResource;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
}
