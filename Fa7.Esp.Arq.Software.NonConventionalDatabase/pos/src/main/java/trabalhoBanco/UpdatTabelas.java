package trabalhoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatTabelas {
	

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/db_nosqlapp";
	static final String USER = "postgres";
	static final String PASS = "@dmin123";

	static Connection conn = null;
	static Statement stmt = null;

	public void UpdateAlunos() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
							 
		for (int i = 0; i < 100000; i++) {
			String sql = "UPDATE ALUNOS SET NOME= 'Maria das Graças"+ i +"' WHERE ID_ALUNO =" +i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 1000000 alunos");
	}
	
	public void UpdateAulas() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
							 
		for (int i = 0; i < 100000; i++) {
			String sql = "UPDATE AULA SET DESCRICAO = 'Aula 0"+ i +"' WHERE ID_AULA =" +i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 1000000 aulas");
	}
	
	public void UpdateCursos() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
							 
		for (int i = 0; i < 100000; i++) {
			String sql = "UPDATE CURSOS SET DESCRICAO = 'Curso 0"+ i +"' WHERE ID_CURSO =" +i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 1000000 cursos");
	}
	
	public void UpdateMatriculas() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
							 
		for (int i = 0; i < 100000; i++) {
			String sql = "UPDATE MATRICULAS SET ID_ALUNO = '2' WHERE ID_ALUNO = 30";
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 1000000 matriculas");
	}

	public void UpdateProfessores() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
		
		for(int i = 0; i< 100000; i++){
			String sql = "UPDATE PROFESSORES SET NOME = 'José da Silva' WHERE ID_PROFESSOR=" +i;
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 1000000 professores");
	}
}
