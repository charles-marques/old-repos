package trabalhoBanco.mongo.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import trabalhoBanco.model.Aula;
import trabalhoBanco.mongo.persistence.EntityPersistence;

import com.mongodb.client.FindIterable;

/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 * 
 */
public class AulaService extends EntityPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2357213047008549361L;
	
	CursoService cursoService = new CursoService();
	ProfessorService professorService = new ProfessorService();
	
	public AulaService() {
		
	}
	
	public Aula salvar(Aula aula) throws Exception {
		try {
			if (aula.getId() == null) aula.setId(getNextId(aula));
			
			this.insert(aula.getCollection(), aula.toDocument());
			return (Aula) aula.toObject(find(aula.getCollection(), aula.toDocument()).first());
		} catch (Exception e) {
			e.printStackTrace();
			throw e; 
		}
	}
	
	public Aula getById(Aula aula) throws Exception {
		Document document = new Document();
		if (aula.getId() != null) {
			document.append("id", aula.getId());
		} else {
//			aluno.setId(getNextId(aluno));
//			salvar(aluno);
			return null;
		}

		return (Aula) aula.toObject(findById(aula.getCollection(), document).first());
	}

	public Integer getNextId(Aula aula) {
		String key = "id";
		Integer index = 1;

		FindIterable<Document> iterable = findAll(aula.getCollection());

		if (iterable != null) {
			for (Document document : iterable) {
				if (document.getInteger(key) >= index) {
					index = document.getInteger(key) + 1;
				}
			}
		}
		return index;
	}
	
	public void remover(Aula aula) {
		delete(aula.getCollection(), aula.toDocument());
	}
	
	public Aula atualizar(Aula aulaUpdate) throws Exception {
		
		Aula aulaFilter = getById(new Aula(aulaUpdate.getId()));
		if (cursoService.getById(aulaUpdate.getCurso()) != null) {
			aulaUpdate.setCurso(cursoService.atualizar(aulaUpdate.getCurso()));
		} else {
			aulaUpdate.setCurso(cursoService.salvar(aulaUpdate.getCurso()));
		}
		if (professorService.getById(aulaUpdate.getProfessor()) != null) {
			aulaUpdate.setProfessor(professorService.atualizar(aulaUpdate.getProfessor()));
		} else {
			aulaUpdate.setProfessor(professorService.salvar(aulaUpdate.getProfessor()));
		}
		update(aulaUpdate.getCollection(), aulaFilter.toDocument(), aulaUpdate.toDocumentUpdate());
		return getById(aulaFilter);
	}
	
	public List<Aula> getByParameter(Aula aula) {
		FindIterable<Document> retorno = null;
		List<Aula> list = new ArrayList<Aula>();
		try {
			retorno = findById(aula.getCollection(), aula.toDocument());
			for (Document document : retorno) {
				list.add((Aula) Aula.class.newInstance().toObject(document));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
