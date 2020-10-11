package br.edu.fa7.nosqlapp;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.reporters.Format;
/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 *
 */
@Path("teste")
public class JBehaveTest {
	
	private static Embedder embedder;
	private static List<String> storyPaths;
	
	public JBehaveTest() {
		embedder = new Embedder();
//		storyPaths = Arrays.asList("br/edu/fa7/test/nosqlapp/bdd/aula.story");
		storyPaths = Arrays.asList("br/edu/fa7/nosqlapp/aula.story");
	}
	
	public void teste() {
		embedder.candidateSteps().add(new AulaSteps());
		embedder.candidateSteps().add(new DisciplinaSteps());
		
		embedder.configuration().storyReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.HTML, Format.XML);

		embedder.runStoriesAsPaths(storyPaths);
	}
	
	@Path("aula/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String executarTeste(@PathParam("id") int id) {
		try { 
			teste();
			return "<content>TEST SUCCESS</content>";
		} catch (Exception e) {
			e.printStackTrace();
			return "<content>TEST FAIL</content>";
		}
	}

}
