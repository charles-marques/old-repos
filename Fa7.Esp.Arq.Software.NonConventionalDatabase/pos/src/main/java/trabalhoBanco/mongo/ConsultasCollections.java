package trabalhoBanco.mongo;

import java.sql.SQLException;

import trabalhoBanco.model.Aula;
import trabalhoBanco.model.Matricula;
import trabalhoBanco.mongo.persistence.service.AlunoService;
import trabalhoBanco.mongo.persistence.service.AulaService;
import trabalhoBanco.mongo.persistence.service.CursoService;
import trabalhoBanco.mongo.persistence.service.MatriculaService;
import trabalhoBanco.mongo.persistence.service.ProfessorService;

public class ConsultasCollections {

	AlunoService alunoService = new AlunoService();
	AulaService aulaService = new AulaService();
	CursoService cursoService = new CursoService();
	MatriculaService matriculaService = new MatriculaService();
	ProfessorService professorService = new ProfessorService();
	
	public void retornaDescricaoAlunosMatriculadosNaAula1() throws SQLException {
		
		Long in = System.currentTimeMillis();
		
		Matricula matricula = new Matricula();
		matricula.setAula(new Aula(1));
		matriculaService.getByParameter(matricula);
		
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + (out - in) + " microsegundos");
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar os alunos matriculados na aula com id 1");
	}

	public void retornaNomeCursosDeCadaAula() throws SQLException {
		
		Long in = System.currentTimeMillis();
		
		Aula aula = new Aula();
		aulaService.getByParameter(aula);
		
		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000) + " seg para retornar os cursos de todas aulas");
	}

	public void retornaNomeProfessorCadaAula() throws SQLException {
		
		Long in = System.currentTimeMillis();
		
		Aula aula = new Aula();
		aulaService.getByParameter(aula);

		Long out = System.currentTimeMillis();
		System.out.println("Demorou " + ((out - in) / 1000)
				+ " seg para retornar os professores de todas aulas");
	}
}
