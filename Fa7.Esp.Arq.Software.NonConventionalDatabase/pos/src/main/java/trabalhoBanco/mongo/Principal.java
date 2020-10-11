package trabalhoBanco.mongo;

import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws SQLException {
		System.out.println("Inicio...");
		/*
		 * 1. Criação das tabelas
		 */
//		 CriacaoCollections criacaoCollections = new CriacaoCollections();
//		 criacaoCollections.iniciaConexaoCriaCollection();

//		/*
//		 * 2. Insert das tabelas
//		 */
//
//		 InsertCollections insertCollections = new InsertCollections();
//		 insertCollections.insereAlunos();
//		 insertCollections.insereAulas();
//		 insertCollections.insereCursos();
//		 insertCollections.insereMatricula();
//		 insertCollections.insereProfessor();
//
//		/*
//		 * 3. Retorna os alunos que estão matriculados na aula 1, realizando
//		 * JOIN em 3 tabelas
//		 */
		ConsultasCollections consultasCollections = new ConsultasCollections();
		consultasCollections.retornaDescricaoAlunosMatriculadosNaAula1();
//
//		/*
//		 * 4. Retorna todos os nomes de cursos e das aulas a que eles pertecem,
//		 * JOIN com 2 tabelas
//		 */
//		consultasCollections.retornaNomeCursosDeCadaAula();
//
//		/*
//		 * 5. Retorna todos os nomes de professores que pertecem a uma aula,
//		 * JOIN com 2 tabelas
//		 */
//		consultasCollections.retornaNomeProfessorCadaAula();
//		
//		
//		/*
//		 * 9. Update nos dados das tabelas
//		 */
//		 UpdateCollections alteracaoCollections = new UpdateCollections();
//		 alteracaoCollections.updateAlunos();
//		 alteracaoCollections.updateAulas();
//		 alteracaoCollections.updateCursos();
//		 alteracaoCollections.updateMatriculas();
//		 alteracaoCollections.updateProfessores();
//
//		/*
//		 * 10.Delete nos dados das tabelas
//		 */
//
		 DeleteCollections deletaCollections = new DeleteCollections();
//		 deletaCollections.deleteAlunos();
//		 deletaCollections.deleteAulas();
//		 deletaCollections.deleteCursos();
//		 deletaCollections.deleteMatriculas();
//		 deletaCollections.deleteProfessores();

		System.out.println("fim");
	}
}
