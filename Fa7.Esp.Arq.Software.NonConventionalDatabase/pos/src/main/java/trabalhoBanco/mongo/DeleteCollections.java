package trabalhoBanco.mongo;

import java.sql.SQLException;

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

public class DeleteCollections {
	
	AlunoService alunoService = new AlunoService();
	AulaService aulaService = new AulaService();
	CursoService cursoService = new CursoService();
	MatriculaService matriculaService = new MatriculaService();
	ProfessorService professorService = new ProfessorService();
	
	public void deleteAlunos() throws SQLException {
		
		Long in = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			alunoService.remover(new Aluno(i + 1));
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 100.000 alunos");
	}

	public void deleteAulas() throws SQLException {
		
		Long in = System.currentTimeMillis();
		
		for (int i = 0; i < 100000; i++) {
			aulaService.remover(new Aula(i + 1));
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 100.000 aulas");
	}

	public void deleteCursos() throws SQLException {
		
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			cursoService.remover(new Curso(i + 1));
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 100.000 cursos");
	}

	public void deleteMatriculas() throws SQLException {
		
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			matriculaService.remover(new Matricula(i + 1));
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 100.000 matriculas");
	}

	public void deleteProfessores() throws SQLException {
		
		Long in = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			professorService.remover(new Professor(i + 1));
		}
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para deletar 100.000 professores");
	}

}
