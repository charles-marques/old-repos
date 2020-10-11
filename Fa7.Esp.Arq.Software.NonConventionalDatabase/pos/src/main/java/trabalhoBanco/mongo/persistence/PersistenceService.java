package trabalhoBanco.mongo.persistence;

import org.bson.Document;

import com.mongodb.client.result.DeleteResult;

public class PersistenceService extends EntityPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1081642897777035235L;

	@Override
	public void createCollection(String collectionName) {
		// TODO Auto-generated method stub
		super.createCollection(collectionName);
	}

	@Override
	public void deleteAllCollection(String collectionName) {
		// TODO Auto-generated method stub
		super.deleteAllCollection(collectionName);
	}

	@Override
	public void droplCollection(String collectionName) {
		// TODO Auto-generated method stub
		super.droplCollection(collectionName);
	}

	@Override
	public DeleteResult delete(String collection, Document e) {
		// TODO Auto-generated method stub
		return super.delete(collection, e);
	}

	
}
