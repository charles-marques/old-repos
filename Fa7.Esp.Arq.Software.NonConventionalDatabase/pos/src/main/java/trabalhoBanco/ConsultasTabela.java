package trabalhoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultasTabela {

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/db_nosqlapp";
	static final String USER = "postgres";
	static final String PASS = "@dmin123";

	static Connection conn = null;
	static Statement stmt = null;

	public void retornaDescricaoAlunosMatriculadosNaAula1() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
		String sql = "SELECT AL.NOME FROM MATRICULAS M "
				+ "JOIN ALUNOS AL ON AL.ID_ALUNO = M.ID_ALUNO "
				+ "JOIN AULA A ON M.ID_AULA = A.ID_AULA "
				+ "WHERE M.ID_AULA = 1";

		 stmt.executeQuery(sql);

		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar os alunos matriculados na aula com id 1");
	}

	public void retornaNomeCursosDeCadaAula() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
		String sql = "SELECT C.DESCRICAO, A.DESCRICAO FROM CURSOS C" 
				 + " JOIN AULA A ON A.ID_CURSO = C.ID_CURSO";

		 stmt.executeQuery(sql);

		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar os cursos de todas aulas");
	}

	public void retornaNomeProfessorCadaAula() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
		String sql = "SELECT P.NOME FROM AULA A "
				+ "JOIN PROFESSORES P ON P.ID_PROFESSOR = A.ID_PROFESSOR";

		 stmt.executeQuery(sql);

		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar os professores de todas aulas");
	}
	
	public void retonaNomeDeAlunosMatriculasEmAula() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
		String sql = "SELECT AL.NOME" +
				" FROM MATRICULAS M" + 
				" JOIN ALUNOS AL  ON AL.ID_ALUNO = M.ID_ALUNO" + 
				" JOIN AULA A ON A.ID_AULA = M.ID_AULA";

		 stmt.executeQuery(sql);

		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar retonaNomeDeAlunosMatriculasEmAula");
	}
	
	public void retornaQuantidadeAlunosMatriculados() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
		String sql = "SELECT COUNT(*)" +
				" FROM MATRICULAS M" + 
				" JOIN ALUNOS AL  ON AL.ID_ALUNO = M.ID_ALUNO";

		 stmt.executeQuery(sql);

		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar retornaQuantidadeAlunosMatriculados");
	}
	
	public void retornaInformacoesCursoProfessorDeCadaAula() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
		String sql = "SELECT * FROM AULA A" +
					 " JOIN CURSOS C ON C.ID_CURSO = A.ID_CURSO" +
					 " JOIN PROFESSORES P ON P.ID_PROFESSOR = A.ID_PROFESSOR";

		stmt.executeQuery(sql);

		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar retornaInformacoesCursoProfessorDeCadaAula");
	}
}
