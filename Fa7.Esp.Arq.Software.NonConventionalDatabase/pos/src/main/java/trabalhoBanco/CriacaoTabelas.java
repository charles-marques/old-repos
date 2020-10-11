package trabalhoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriacaoTabelas {

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/db_nosqlapp";
	static final String USER = "postgres";
	static final String PASS = "@dmin123";

	static Connection conn = null;
	static Statement stmt = null;

	public void iniciaConexaoCriaTabelas() throws SQLException {

		Long in = System.currentTimeMillis();

		CriacaoTabelas create = new CriacaoTabelas();

		create.criaTabelaAula();
		create.criaTabelaCursos();
		create.criaTabelaAlunos();
		create.criaTabelaMatriculas();
		create.criaTabelaProfessores();

		Long out = System.currentTimeMillis();

		System.out.println("Demora " + ((out - in) / 1000) + " seg para iniciar conexão e criar tabelas no banco");
	}

	public void criaTabelaAula() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql = "CREATE TABLE AULA" + "(ID_AULA INTEGER not NULL, " + " ID_PROFESSOR INTEGER, " + " ID_CURSO INTEGER, " + " DESCRICAO VARCHAR(255), " + " PRIMARY KEY ( ID_AULA ))";
		stmt.executeUpdate(sql);
	}

	public void criaTabelaCursos() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql = "CREATE TABLE CURSOS" + "(ID_CURSO INTEGER not NULL, " + " DESCRICAO VARCHAR(255), " + " PRECO INTEGER," + " PRIMARY KEY ( ID_CURSO ))";
		stmt.executeUpdate(sql);
	}

	public void criaTabelaAlunos() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql = "CREATE TABLE ALUNOS" + "(ID_ALUNO INTEGER not NULL, " + " NOME VARCHAR(255), " + " FONE VARCHAR(255)," + " PRIMARY KEY ( ID_ALUNO ))";
		stmt.executeUpdate(sql);
	}

	public void criaTabelaMatriculas() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql = "CREATE TABLE MATRICULAS" + "(ID_MATRICULA INTEGER not NULL, " + " ID_ALUNO INTEGER, " + " ID_AULA INTEGER," + " PRIMARY KEY ( ID_MATRICULA ))";
		stmt.executeUpdate(sql);
	}

	public void criaTabelaProfessores() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = conn.createStatement();
		String sql = "CREATE TABLE PROFESSORES" + "(ID_PROFESSOR INTEGER not NULL, " + " NOME VARCHAR(255), " + " EMAIL VARCHAR(255)," + " PRIMARY KEY ( ID_PROFESSOR ))";
		stmt.executeUpdate(sql);
	}

}