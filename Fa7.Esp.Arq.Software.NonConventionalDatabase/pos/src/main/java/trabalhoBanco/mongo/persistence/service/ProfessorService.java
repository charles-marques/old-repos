package trabalhoBanco.mongo.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import trabalhoBanco.model.Professor;
import trabalhoBanco.mongo.persistence.EntityPersistence;

import com.mongodb.client.FindIterable;
/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 *
 */
public class ProfessorService extends EntityPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974038329903326617L;

	public Professor salvar(Professor professor) throws Exception {
		if (professor == null) return null;
		if (professor.getId() == null) professor.setId(getNextId(professor));
		insert(professor.getCollection(), professor.toDocument());
		return (Professor) professor.toObject(find(professor.getCollection(), professor.toDocument()).first());
	}

	public Professor getById(Professor professor) throws Exception {
		Document document = new Document();
		if (professor != null && professor.getId() != null) {
			document.append("id", professor.getId());
		} else {
//			professor.setId(getNextId(professor));
//			salvar(professor);
			return null;
		}

		FindIterable<Document> retorno = findById(professor.getCollection(), document);
		return (Professor) Professor.class.newInstance().toObject(retorno.first());
	}

	public Integer getNextId(Professor professor) {
		String key = "id";
		Integer index = 1;

		FindIterable<Document> iterable = findAll(professor.getCollection());

		if (iterable != null) {
			for (Document document : iterable) {
				if (document.getInteger(key) >= index) {
					index = document.getInteger(key) + 1;
				}
			}
		}
		return index;
	}
	
	public void remover(Professor professor) {
		delete(professor.getCollection(), professor.toDocument());
	}
	
	public Professor atualizar(Professor professorUpdate) throws Exception {
		Professor professorFilter = new Professor(professorUpdate.getId());
		update(professorUpdate.getCollection(), professorFilter.toDocument(), professorUpdate.toDocument());
		return getById(professorUpdate);
	}
	
	public List<Professor> getByParameter(Professor professor) {
		FindIterable<Document> retorno = null;
		List<Professor> list = new ArrayList<Professor>();
		try {
			retorno = findById(professor.getCollection(), professor.toDocument());
			for (Document document : retorno) {
				list.add((Professor) Professor.class.newInstance().toObject(document));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
