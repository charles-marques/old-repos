package trabalhoBanco.mongo.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import trabalhoBanco.model.Aluno;
import trabalhoBanco.mongo.persistence.EntityPersistence;

import com.mongodb.client.FindIterable;
/**
 * 
 * @author charles.marques
 * @author kleben.ribeiro
 * @author milena.rodrigues
 *
 */
public class AlunoService extends EntityPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974038329903326617L;

	public AlunoService() {
		
	}
	
	public Aluno salvar(Aluno aluno) throws Exception {
		if (aluno.getId() == null) aluno.setId(getNextId(aluno));
		insert(aluno.getCollection(), aluno.toDocument());
		return (Aluno) aluno.toObject(find(aluno.getCollection(), aluno.toDocument()).first());
	}

	public Aluno getById(Aluno aluno) throws Exception {
		Document document = new Document();
		if (aluno.getId() != null) {
			document.append("id", aluno.getId());
		} else {
			return null;
		}
		return (Aluno) aluno.toObject(findById(aluno.getCollection(), document).first());
	}

	public Integer getNextId(Aluno aluno) {
		String key = "id";
		Integer index = 1;

		FindIterable<Document> iterable = findAll(aluno.getCollection());

		if (iterable != null) {
			for (Document document : iterable) {
				if (document.getInteger(key) >= index) {
					index = document.getInteger(key) + 1;
				}
			}
		}
		return index;
	}
	
	public void remover(Aluno aluno) {
		delete(aluno.getCollection(), aluno.toDocument());
	}
	
	public Aluno atualizar(Aluno alunoUpdate) throws Exception {
		Aluno alunoFilter = getById(new Aluno(alunoUpdate.getId()));
		update(alunoUpdate.getCollection(), alunoFilter.toDocument(), alunoUpdate.toDocumentUpdate());
		return getById(alunoFilter);
	}
	
	public List<Aluno> getByParameter(Aluno aluno) {
		FindIterable<Document> retorno = null;
		List<Aluno> list = new ArrayList<Aluno>();
		try {
			retorno = findById(aluno.getCollection(), aluno.toDocument());
			for (Document document : retorno) {
				list.add((Aluno) Aluno.class.newInstance().toObject(document));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
