package trabalhoBanco.mongo.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import trabalhoBanco.model.Matricula;
import trabalhoBanco.mongo.persistence.EntityPersistence;

import com.mongodb.client.FindIterable;
/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 *
 */
public class MatriculaService extends EntityPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974038329903326617L;
	
	public Matricula salvar(Matricula matricula) throws Exception {
		if (matricula.getId() == null) matricula.setId(getNextId(matricula));
		insert(matricula.getCollection(), matricula.toDocument());
		return (Matricula) matricula.toObject(find(matricula.getCollection(), new Document("id", matricula.getId())).first());
	}

	public Matricula getById(Matricula matricula) throws Exception {
		Document document = new Document();
		if (matricula.getId() != null) {
			document.append("id", matricula.getId());
		} else {
			return null;
		}

		FindIterable<Document> retorno = findById(matricula.getCollection(), document);
		return (Matricula) Matricula.class.newInstance().toObject(retorno.first());
	}

	public Integer getNextId(Matricula matricula) {
		String key = "id";
		Integer index = 1;

		FindIterable<Document> iterable = findAll(matricula.getCollection());

		if (iterable != null) {
			for (Document document : iterable) {
				if (document.getInteger(key) >= index) {
					index = document.getInteger(key) + 1;
				}
			}
		}
		return index;	
	}
	
	public void remover(Matricula matricula) {
		matricula = new Matricula(matricula.getId());
		delete(matricula.getCollection(), matricula.toDocument());
	}
	
	public Matricula atualizar(Matricula matriculaUpdate) throws Exception {
		Matricula matriculaFilter = getById(new Matricula(matriculaUpdate.getId()));
		update(matriculaUpdate.getCollection(), matriculaFilter.toDocument(), matriculaUpdate.toDocumentUpdate());
		return getById(matriculaFilter);
	}
	
	public List<Matricula> getByParameter(Matricula matricula) {
		FindIterable<Document> retorno = null;
		List<Matricula> list = new ArrayList<Matricula>();
		try {
			retorno = findById(matricula.getCollection(), matricula.toDocument());
			for (Document document : retorno) {
				list.add((Matricula) Matricula.class.newInstance().toObject(document));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
