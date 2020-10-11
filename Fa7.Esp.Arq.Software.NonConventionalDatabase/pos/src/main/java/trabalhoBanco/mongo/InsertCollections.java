package trabalhoBanco.mongo;

import java.util.Random;

import trabalhoBanco.model.Aluno;
import trabalhoBanco.model.Aula;
import trabalhoBanco.model.Curso;
import trabalhoBanco.model.Matricula;
import trabalhoBanco.model.Professor;
import trabalhoBanco.mongo.persistence.service.AlunoService;
import trabalhoBanco.mongo.persistence.service.AulaService;
import trabalhoBanco.mongo.persistence.service.CursoService;
import trabalhoBanco.mongo.persistence.service.MatriculaService;
import trabalhoBanco.mongo.persistence.service.ProfessorService;

public class InsertCollections {

	private AulaService aulaService = new AulaService();
	private AlunoService alunoService = new AlunoService();
	private CursoService cursoService = new CursoService();
	private MatriculaService matriculaService = new MatriculaService();
	private ProfessorService professorService = new ProfessorService();
	private Aluno aluno;
	private Aula aula;
	private Curso curso;
	private Professor professor;
	private Matricula matricula;
	private Random gerador = new Random();
	
	public void insereAlunos() {
		String telefone = "34797300";
		String nome = "João Das Graças ";
		System.out.println("Inserindo Alunos=====================");
		Long in = System.currentTimeMillis();
		try {
			for (int i = 0; i < 10000; i++) {
				aluno = new Aluno();
				aluno.setFone(telefone);
				aluno.setNome(nome + i);
				alunoService.salvar(aluno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long out = System.currentTimeMillis();
		System.out.println("Relacao: '" + Aluno.class.getName() + "';");
		System.out.println("Demorou " + ((out - in) / 1000)+ " seg para inserir 100.000.000 alunos;");
	}
	
	public void insereProfessor() {
		String email = "jose@email.com";
		String nome = "Professor José ";
		System.out.println("Inserindo Professor=====================");
		Long in = System.currentTimeMillis();
		try {
			for(int i = 1; i <= 10000; i++) {
				professor = new Professor();
				professor.setEmail(email);
				professor.setNome(nome + i);
				
				professorService.salvar(professor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long out = System.currentTimeMillis();
		System.out.println("Relacao: '" + Professor.class.getName() + "';");
		System.out.println("Demorou " + ((out - in) / 1000)+ " seg para inserir 100.000 professores;");
	}
	
	public void insereCursos() {
		String descricao = "Curso ";
		System.out.println("Inserindo Cursos=====================");
		Long in = System.currentTimeMillis();
		try {
			for (int i = 1; i <= 10000; i++) {
				curso = new Curso();
				curso.setDescricao(descricao + i);
				curso.setPreco(i + 9);
				
				cursoService.salvar(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long out = System.currentTimeMillis();
		System.out.println("Relacao: '" + Curso.class.getName() + "';");
		System.out.println("Demorou " + ((out - in) / 1000)+ " seg para inserir 100.000 cursos;");
	}
	
	public void insereAulas() {
		String descricao = "Aula ";
		System.out.println("Inserindo Aulas=====================");
		Long in = System.currentTimeMillis();
		try {
			for (int i = 1; i <= 10000; i++) {
				aula = new Aula();
				
				aula.setCurso(cursoService.getById(new Curso(gerador.nextInt(20))));
				aula.setDescricao(descricao + i);
				aula.setProfessor(professorService.getById(new Professor(gerador.nextInt(30))));
				
				aulaService.salvar(aula);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long out = System.currentTimeMillis();
		System.out.println("Relacao: '" + Aula.class.getName() + "';");
		System.out.println("Demorou " + ((out - in) / 1000)+ " seg para inserir 100.000 aulas;");
	}

	public void insereMatricula() {
		
		System.out.println("Inserindo Matriculas=====================");
		Long in = System.currentTimeMillis();
		try {
			for(int i = 1; i <= 10000; i++){
				matricula = new Matricula();
				
				matricula.setAluno(alunoService.getById(new Aluno(gerador.nextInt(10))));
				matricula.setAula(aulaService.getById(new Aula(gerador.nextInt(20))));
				
				matriculaService.salvar(matricula);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long out = System.currentTimeMillis();
		System.out.println("Relacao: '" + Matricula.class.getName() + "';");
		System.out.println("Demorou " + ((out - in) / 1000)+ " seg para inserir 100.000 matriculas;");
	}

}
