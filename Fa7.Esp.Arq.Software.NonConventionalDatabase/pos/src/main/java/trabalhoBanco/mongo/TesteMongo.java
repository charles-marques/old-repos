package trabalhoBanco.mongo;

import trabalhoBanco.model.Professor;
import trabalhoBanco.mongo.persistence.service.ProfessorService;

/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 *
 */
public class TesteMongo {

	public static void main(String[] args) {
		ProfessorService professorService = new ProfessorService();
		
		Professor professor = new Professor();
		
		try {
			// Teste Salvar
//			professor.setEmail("professor@teste.com");
//			professor.setNome("afrânio");
//			professor = professorService.salvar(professor);
			
			// Teste Atualizar
//			professor.setId(1);
//			professor = professorService.getById(professor);
//			professor.setEmail("doutor.afranio@professor.edu.br");
//			professor = professorService.atualizar(professor);
			
			// Teste de Remover
			professor.setId(1);
			professor = professorService.getById(professor);
			professorService.remover(professor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Professor: " + professor.getCollection());
	}
}
