package trabalhoBanco;

import java.sql.SQLException;

public class Principal {

	public static void main(String[] args) throws SQLException {

		/*
		 * 1. Criação das tabelas
		 */
		CriacaoTabelas criacaoTabelas = new CriacaoTabelas();
		criacaoTabelas.iniciaConexaoCriaTabelas();

		/*
		 * 2. Insert das tabelas
		 */

		InsertTabelas insertTabelas = new InsertTabelas();
		insertTabelas.insereAlunos();
		insertTabelas.insereAulas();
		insertTabelas.insereCursos();
		insertTabelas.insereMatricula();
		insertTabelas.insereProfessor();


		ConsultasTabela consultasTabela = new ConsultasTabela();

		consultasTabela.retornaNomeProfessorCadaAula();
		consultasTabela.retornaDescricaoAlunosMatriculadosNaAula1();

		consultasTabela.retornaNomeCursosDeCadaAula();

		consultasTabela.retornaNomeProfessorCadaAula();

		consultasTabela.retornaQuantidadeAlunosMatriculados();

		consultasTabela.retornaNomeCursosDeCadaAula();
		consultasTabela.retornaNomeProfessorCadaAula();
		consultasTabela.retornaInformacoesCursoProfessorDeCadaAula();

		/*
		 * 9. Update nos dados das tabelas
		 */
		UpdatTabelas alteracaoTabelas = new UpdatTabelas();
		alteracaoTabelas.UpdateAlunos();
		alteracaoTabelas.UpdateAulas();
		alteracaoTabelas.UpdateCursos();
		alteracaoTabelas.UpdateMatriculas();
		System.out.println("iniciando");
		alteracaoTabelas.UpdateProfessores();

		/*
		 * 10.Delete nos dados das tabelas
		 */

		DeleteTabelas deletaTabelas = new DeleteTabelas();
		deletaTabelas.deleteAlunos();
		deletaTabelas.deleteAulas();
		deletaTabelas.deleteCursos();
		deletaTabelas.deleteMatriculas();
		deletaTabelas.deleteProfessores();

		System.out.println("fim");
	}
}
