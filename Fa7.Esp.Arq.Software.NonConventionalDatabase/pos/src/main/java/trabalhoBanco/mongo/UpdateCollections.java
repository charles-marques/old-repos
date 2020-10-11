package trabalhoBanco.mongo;

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

public class UpdateCollections {
	
	AlunoService alunoService = new AlunoService();
	AulaService aulaService = new AulaService();
	CursoService cursoService = new CursoService();
	MatriculaService matriculaService = new MatriculaService();
	ProfessorService professorService = new ProfessorService();
	
	public void updateAlunos() {
		
		Long in = System.currentTimeMillis();
		Aluno aluno;
		for (int i = 0; i < 10000; i++) {
			aluno = new Aluno();
			aluno.setId(i + 1);
			aluno.setNome("Maria das Graças"+ i);
			try {
				alunoService.atualizar(aluno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 100.000 alunos");
	}
	
	public void updateAulas() {
		
		Long in = System.currentTimeMillis();
		Aula aula;
		for (int i = 0; i < 10000; i++) {
			aula = new Aula();
			aula.setId(i + 1);
			aula.setDescricao("Aula 0"+ i);
			try {
				aulaService.atualizar(aula);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 100.000 aulas");
	}
	
	public void updateCursos() {
		
		Long in = System.currentTimeMillis();
		Curso curso;
		for (int i = 0; i < 10000; i++) {
			curso = new Curso();
			curso.setId(i + 1);
			curso.setDescricao("Curso 0"+ i);
			try {
				cursoService.atualizar(curso);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 100.000 cursos");
	}
	
	public void updateMatriculas() {
		
		Long in = System.currentTimeMillis();
		Matricula matricula;
		for (int i = 0; i < 10000; i++) {
			matricula = new Matricula();
			matricula.setId(i + 1);
			matricula.setAluno(new Aluno(1));
			try {
				matriculaService.atualizar(matricula);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 100.000 matriculas");
	}

	public void updateProfessores() {
		
		Long in = System.currentTimeMillis();
		Professor professor;
		for(int i = 0; i<10000; i++){
			professor = new Professor();
			professor.setId(i + 1);
			professor.setNome("José da Silva " + i + "");
			try {
				professorService.atualizar(professor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou "+ ((out-in)/1000)+" seg para alterar 100.000 professores");
	}
}
