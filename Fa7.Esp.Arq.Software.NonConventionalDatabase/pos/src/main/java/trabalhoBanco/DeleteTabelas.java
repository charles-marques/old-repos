package trabalhoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTabelas {

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/db_nosqlapp";
	static final String USER = "postgres";
	static final String PASS = "@dmin123";

	static Connection conn = null;
	static Statement stmt = null;

	public void deleteAlunos() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			String sql = "DELETE FROM  ALUNOS WHERE ID_ALUNO =" + i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 1000000 alunos");
	}

	public void deleteAulas() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			String sql = "DELETE FROM  AULA WHERE ID_AULA =" + i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 1000000 aulas");
	}

	public void deleteCursos() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			String sql = "DELETE FROM CURSOS  ALUNOS WHERE ID_CURSO =" + i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 1000000 cursos");
	}

	public void deleteMatriculas() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			String sql = "DELETE FROM MATRICULAS WHERE ID_MATRICULA =" + i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 1000000 matriculas");
	}

	public void deleteProfessores() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			String sql = "DELETE FROM PROFESSORES WHERE ID_PROFESSOR =" + i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 1000000 professores");
	}

}
