package trabalhoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class InsertTabelas {

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/db_nosqlapp";
	static final String USER = "postgres";
	static final String PASS = "@dmin123";

	static Connection conn = null;
	static Statement stmt = null;

	public void insereAlunos() throws SQLException {

		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		Long in = System.currentTimeMillis();
							 
		for (int i = 0; i < 100000; i++) {
			String sql = "INSERT INTO ALUNOS " + "VALUES ('"+ i +"', 'João Das Graças " + i + "', '34797300')";
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para inserir 1000000 alunos");

		
	}
	public void insereAulas() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		
		Long in = System.currentTimeMillis();
		
		for (int i = 1; i <= 100000; i++) {
			Random gerador = new Random();
			String sql = "INSERT INTO AULA " + "VALUES ('"+ i +"', '"+ gerador.nextInt(30) +"', '"+ gerador.nextInt(20) +"', 'Aula "+ i +"')";
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para inserir 1000000 aulas");

		
	}
	
	public void insereCursos() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		
		Long in = System.currentTimeMillis();
		
		for (int i = 1; i <= 100000; i++) {
			String sql = "INSERT INTO CURSOS " + "VALUES ('"+ i +"', 'Curso "+i+"', '"+i+"0')";
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para inserir 1000000 cursos");		
	}

	public void insereMatricula() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		
		Long in = System.currentTimeMillis();
		
		for(int i = 1; i <= 100000; i++){
			Random gerador = new Random();
			String sql = "INSERT INTO MATRICULAS " + "VALUES ('"+ i +"', '"+ gerador.nextInt(10) +"', '"+ gerador.nextInt(20) +"')"; 
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para inserir 100000 matriculas");
	}
	
	public void insereProfessor() throws SQLException{
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		
		Long in = System.currentTimeMillis();
		
		for(int i = 1; i <= 100000; i++){
			String sql = "INSERT INTO PROFESSORES " + "VALUES ('"+ i +"', 'Professor José "+i+"', 'jose@email.com')"; 
			stmt.executeUpdate(sql);
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para inserir 100000 professores");
	}

}
