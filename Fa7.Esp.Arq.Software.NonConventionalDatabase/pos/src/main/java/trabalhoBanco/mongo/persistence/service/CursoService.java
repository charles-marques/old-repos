package trabalhoBanco.mongo.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import trabalhoBanco.model.Curso;
import trabalhoBanco.mongo.persistence.EntityPersistence;

import com.mongodb.client.FindIterable;
/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 *
 */
public class CursoService extends EntityPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974038329903326617L;
	
	public Curso salvar(Curso curso) throws Exception {
		if (curso == null) return null;
		if (curso.getId() == null) curso.setId(getNextId(curso));
		insert(curso.getCollection(), curso.toDocument());
		return (Curso) curso.toObject(find(curso.getCollection(), new Document("id", curso.getId())).first());
	}

	public Curso getById(Curso curso) throws Exception {
		Document document = new Document();
		if (curso != null && curso.getId() != null) {
			document.append("id", curso.getId());
		} else {
			return null;
		}

		FindIterable<Document> retorno = findById(curso.getCollection(), document);
		return (Curso) Curso.class.newInstance().toObject(retorno.first());
	}

	public Integer getNextId(Curso curso) {
		String key = "id";
		Integer index = 1;

		FindIterable<Document> iterable = findAll(curso.getCollection());

		if (iterable != null) {
			for (Document document : iterable) {
				if (document.getInteger(key) >= index) {
					index = document.getInteger(key) + 1;
				}
			}
		}
		return index;
	}
	
	public void remover(Curso curso) {
		delete(curso.getCollection(), curso.toDocument());
	}
	
	public Curso atualizar(Curso cursoUpdate) throws Exception {
		Curso cursoFilter = getById(new Curso(cursoUpdate.getId()));
		update(cursoUpdate.getCollection(), cursoFilter.toDocument(), cursoUpdate.toDocumentUpdate());
		return getById(cursoFilter);
	}
	
	public List<Curso> getByParameter(Curso curso) {
		FindIterable<Document> retorno = null;
		List<Curso> list = new ArrayList<Curso>();
		try {
			retorno = findById(curso.getCollection(), curso.toDocument());
			for (Document document : retorno) {
				list.add((Curso) Curso.class.newInstance().toObject(document));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
