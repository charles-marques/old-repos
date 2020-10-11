package trabalhoBanco.mongo;

import java.sql.SQLException;

import trabalhoBanco.model.Aluno;
import trabalhoBanco.model.Aula;
import trabalhoBanco.model.Curso;
import trabalhoBanco.model.Matricula;
import trabalhoBanco.model.Professor;
import trabalhoBanco.mongo.persistence.PersistenceService;

public class CriacaoCollections {

	PersistenceService persistenceService = new PersistenceService();
	
	public void iniciaConexaoCriaCollection() throws SQLException {

		Long in = System.currentTimeMillis();

		CriacaoCollections create = new CriacaoCollections();

		create.criaCollectionAula();
		create.criaCollectionCursos();
		create.criaCollectionAlunos();
		create.criaCollectionMatriculas();
		create.criaCollectionProfessores();

		Long out = System.currentTimeMillis();

		System.out.println("Demora " + (out - in) + "miliseg");
		System.out.println("Demora " + ((out - in) / 1000) + " seg para iniciar conexão e criar tabelas no banco");
	}

	public void criaCollectionAula() throws SQLException {
		Aula aula = new Aula();
		persistenceService.createCollection(aula.getCollection());
	}

	public void criaCollectionCursos() throws SQLException {
		Curso curso = new Curso();
		persistenceService.createCollection(curso.getCollection());
	}

	public void criaCollectionAlunos() throws SQLException {
		Aluno aluno = new Aluno();
		persistenceService.createCollection(aluno.getCollection());
	}

	public void criaCollectionMatriculas() throws SQLException {
		Matricula matricula = new Matricula();
		persistenceService.createCollection(matricula.getCollection());
	}

	public void criaCollectionProfessores() throws SQLException {
		Professor professor = new Professor();
		persistenceService.createCollection(professor.getCollection());
	}

}